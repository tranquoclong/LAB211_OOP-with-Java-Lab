package Controller;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.util.Scanner;
import Model.Injection;
import Validation.MyValidation;

public class InjectionController extends ArrayList<Injection> implements FileConnection<Injection> {
  private final String FILENAME = "injection.txt";
  private Scanner scanner = null;
  private FileWriter fileWriter = null;
  private BufferedWriter bufferedWriter = null;
  private PrintWriter printWriter = null;
  private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  public InjectionController() {
    getAll();
  }

  public Injection get(String injectionID) {
    for (Injection injection : this) if (injection.getInjectionID().equals(injectionID)) return injection;
    return null;
  }
  public Injection getStudent(String studentID) {
    for (Injection injection : this) if (injection.getStudentID().equals(studentID)) return injection;
    return null;
  }

  public void getAll() {
    boolean readMode = true;
    open(readMode);
    while (scanner.hasNextLine()) {
      String data = scanner.nextLine();
      Injection injection = create(data);
      this.add(injection);
    }
    close();
  }

  @Override
  public void open(boolean readMode) {
    if (readMode) {
      try {
        scanner = new Scanner(Paths.get(FILENAME), "UTF-8");
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try {
        fileWriter = new FileWriter(FILENAME, false);
        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void close() {
    if (scanner != null) scanner.close();
    if (printWriter != null && bufferedWriter != null && fileWriter != null)
      try {
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

  @Override
  public Injection create(String data) {
    String split[] = data.split("\\;");
    return new Injection(split[0], split[1], split[2], split[3], MyValidation.isValidParseDate(split[4]), split[5],
            split[6] == "null" ? null : MyValidation.isValidParseDate(split[6]));
  }

  public Injection saveToFile() {
    String write = "";
    PrintWriter w = null;
    for (Injection injection : this) write += injection.toString() + "\n";
    try {
      w = new PrintWriter(new File(FILENAME));
      w.write(write);
      w.flush();
      if (w != null) w.close();
    } catch (Exception e) {
      if (w != null) w.close();
    }
    return null;
  }

  public void printInjection(Injection injection) {
    System.out.format("%15s|%15s|%15s|%15s|%15s|%15s|%15s\n",
        injection.getInjectionID(),
        injection.getStudentID(),
        injection.getVaccineID(),
        injection.getFirstPlace(),
        dateFormat.format(injection.getFirstDate()),
        injection.getSecondPlace(),
        injection.getSecondDate() == null ? "null" : dateFormat.format(injection.getSecondDate()));
  }

  public void printInjectionList() {
    System.out.format("%15s|%15s|%15s|%15s|%15s|%15s|%15s\n",
            "InjectionID","StudentID","VaccineID","First Place", "First Date", "Second Place", "Second Date");
    this.forEach(injection -> {printInjection(injection);});}

  public ArrayList<Injection> getInjectionByStudentID(String studentID) {
    if (this.isEmpty()) return null;
    else {
      ArrayList<Injection> result = new ArrayList<Injection>();
      for (Injection injection : this) if (injection.getStudentID().equals(studentID)) result.add(injection);
      return result;
    }
  }
}
