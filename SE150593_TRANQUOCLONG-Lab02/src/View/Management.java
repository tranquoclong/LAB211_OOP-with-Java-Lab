package View;

import Controller.InjectionController;
import Controller.ProvinceController;
import Controller.StudentController;
import Controller.VaccineController;
import Model.Injection;
import Validation.MyValidation;
import java.util.*;

public class Management {

  public static InjectionController controller = new InjectionController();
  public static StudentController studentController = new StudentController();
  public static VaccineController vaccineController = new VaccineController();
  public static ProvinceController provinceController = new ProvinceController();
  public static Scanner sc = new Scanner(System.in);
  public static Injection injection = null;
  public static Calendar cal = Calendar.getInstance();
  public static Date t = cal.getTime();
  public static void showInjectionInformation() {
    if (controller.isEmpty()) System.out.println("The Injection is empty!");
    else controller.printInjectionList();
  }

  public static void addInjection() {
    String injectionID = "", studentID = "", vaccineID = "", firstPlace = "", firstDateInput = "", secondPlace = "", secondDateInput = "";
    Date firstDate = null;
    Date secondDate = null;
    do {
      System.out.print("\tEnter injection ID: ");
      injectionID = sc.nextLine();
      if (controller.get(injectionID) != null) System.out.println("ID existed!");
    } while (injectionID.isEmpty() || controller.get(injectionID) != null);
    studentController.printStudentList();
    do {
      System.out.print("\tEnter student ID: ");
      studentID = sc.nextLine();
      if (studentController.get(studentID) == null || controller.getStudent(studentID) != null) System.out.println("student existed!");
    } while (studentID.isEmpty() || studentController.get(studentID) == null || controller.getStudent(studentID) != null);
    vaccineController.printVaccineList();
    do {
      System.out.print("\tEnter vaccine ID: ");
      vaccineID = sc.nextLine();
      if (vaccineController.get(vaccineID) == null) System.out.println("vaccine existed!");
    } while (vaccineID.isEmpty() || vaccineController.get(vaccineID) == null);
    do {
      System.out.print("\tEnter first place: ");
      firstPlace = sc.nextLine().toLowerCase().trim();
      if (provinceController.get(firstPlace) == null) System.out.println("province existed!");
    } while (firstPlace.isEmpty() || provinceController.get(firstPlace) == null);
    do {
      firstDate = MyValidation.getDate();
      if (firstDate.after(t)) System.out.println("Please enter a valid expired date, (dd/mm/yyyy; Ex: 13/9/2021)");
    } while (firstDate.after(t) == true);
    switch (MyValidation.isContinue("Do you really want to delete this injection (y/n) ?")) {
      case 1:
        do {
          System.out.print("\tEnter second place: ");
          secondPlace = sc.nextLine().toLowerCase().trim();
          if (provinceController.get(secondPlace) == null) System.out.println("province existed!");
        } while (secondPlace.isEmpty() || provinceController.get(secondPlace) == null);
        do {
          secondDate = MyValidation.getDate();
          if (secondDate.after(t)) System.out.println("Please enter a valid expired date, (dd/mm/yyyy; Ex: 13/9/2021)");
        } while (secondDate.after(t) == true || !MyValidation.isValidSecondDate(firstDate, secondDate));
        injection = new Injection(injectionID, studentID, vaccineID, firstPlace, firstDate, secondPlace, secondDate);
        break;
      case 0:
        injection = new Injection(injectionID, studentID, vaccineID, firstPlace, firstDate);
        break;
    }
    controller.add(injection);
    controller.saveToFile();
    System.out.println("Successfully");
  }

  public static void updateInjection() {
    if (controller.isEmpty()) System.out.println("The Injection is empty!");
    else {
      Date secondDate = null;
      String injectionID = null, secondPlace;
      do {
        System.out.print("\tEnter injection ID: ");
        injectionID = sc.nextLine();
        injection = controller.get(injectionID);
        if (injection == null) System.out.println("This injection existed!");
      } while (injectionID.isEmpty() || injection == null);
      int index = controller.indexOf(injection);
      if (injection.getSecondPlace().equals("null")) {
        do {
          System.out.print("\tEnter second place: ");
          secondPlace = sc.nextLine().toLowerCase().trim();
          if (provinceController.get(secondPlace) == null) System.out.println("province existed!");
        } while (secondPlace.isEmpty() || provinceController.get(secondPlace) == null);
        injection.setSecondPlace(secondPlace);
        do {
          secondDate = MyValidation.getDate();
          if (secondDate.after(t)) System.out.println("Please enter a valid expired date, (dd/mm/yyyy; Ex: 13/9/2021)");
        } while (secondDate.after(t) == true || !MyValidation.isValidSecondDate(injection.getFirstDate(), secondDate));
        injection.setSecondDate(secondDate);
        controller.set(index, injection);
      } else System.out.println("This student has already 2 injections");
      controller.saveToFile();
    }
  }

  public static void deleteInjection() {
    if (controller.isEmpty()) System.out.println("This injection existed!");
    else {
      String injectionID = null;
      do {
        System.out.print("\tEnter injection ID: ");
        injectionID = sc.nextLine();
        injection = controller.get(injectionID);
        if (injection == null) System.out.println("This injection existed!");
      } while (injectionID.isEmpty() || injection == null);
      switch (MyValidation.isContinue("Do you really want to delete this injection (y/n) ?")) {
        case 1:
          controller.remove(injection);
          System.out.println("successfully");
          break;
        case 0:
          System.out.println("fail");
          break;
      }
      controller.saveToFile();
    }
  }

  public static void searchByStudentID() {
    if (controller.isEmpty()) System.out.println("This injection existed!");
    else {
      ArrayList<Injection> result = new ArrayList<Injection>();
      String studentID;
      studentController.printStudentList();
      do {
        System.out.print("\tEnter student ID: ");
        studentID = sc.nextLine();
        if (studentController.get(studentID) == null) System.out.println("This student existed!");
      } while (studentID.isEmpty() || studentController.get(studentID) == null);
      result = controller.getInjectionByStudentID(studentID);
      if (result == null || result.size() == 0) System.out.println("This student has not injection");
      else {
        System.out.format("%15s|%15s|%15s|%15s|%15s|%15s|%15s\n",
              "InjectionID","StudentID","VaccineID","First Place", "First Date", "Second Place", "Second Date");
        result.forEach(injection -> controller.printInjection(injection));
      }
    }
  }
}
