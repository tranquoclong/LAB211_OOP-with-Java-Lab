package Controller;

import Model.Food;
import Validation.MyValidation;

import java.text.SimpleDateFormat;
import java.util.*;

public class FoodController {
  public static boolean cont = true;
  public static FoodList foodList = new FoodList();
  public static Scanner sc = new Scanner(System.in);
  public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

  public static void addFood() {
    String ID = "", name = "", weight = "", type = "", place = "";
    Date date;
    cont = true;
    do {
      try {
        System.out.print("\tEnter ID: ");
        ID = sc.nextLine();
        if (!MyValidation.isID(ID)) throw new Exception("ID must be from 3 to 40 characters!");
        if (foodList.search(ID) != null)
          throw new Exception("ID existed! Please try another ID...");
        cont = false;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        cont = true;
      }
    } while (cont);
    do {
      try {
        System.out.print("\tEnter name: ");
        name = sc.nextLine();
        if (!MyValidation.isName(name))
          throw new Exception("name must be from 2 to 35 characters!");
        cont = false;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        cont = true;
      }
    } while (cont);
    do {
      try {
        System.out.print("\tEnter weight (g): ");
        weight = sc.nextLine();
        if (!MyValidation.isWeight(weight)
            || Integer.parseInt(weight) <= 0
            || Integer.parseInt(weight) > 10000)
          throw new Exception("weight must be 1-10000 (g)! Please try again...");
        cont = false;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        cont = true;
      }
    } while (cont);
    do {
      try {
        System.out.print("\tEnter type: ");
        type = sc.nextLine();
        if (!MyValidation.isName(type)) throw new Exception("Invalid type! Please try again...");
        cont = false;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        cont = true;
      }
    } while (cont);
    do {
      System.out.print("\tEnter place (Freezer / Freezing Shelf or f / s are accepted): ");
      try {
        place = sc.nextLine();
        place = MyValidation.convertPlace(place);
        if (place.isEmpty())
          throw new Exception("Please enter Freezer or f or Freezing Shelf or s!");
        if (place.equalsIgnoreCase("Freezer")) place = "Freezer";
        else if (place.equalsIgnoreCase("Freezing Shelf")) place = "Freezing Shelf";
        cont = false;
      } catch (Exception e) {
        System.out.println(e.getMessage());
        cont = true;
      }
    } while (cont);
    Calendar cal = Calendar.getInstance();
    Date t = cal.getTime();
    do {
      date = MyValidation.getDate();
      if (date.before(t)) {
        System.out.println("Please enter a valid expired date, (dd/mm/yyyy; Ex: 13/10/2021)");
      }
    } while (date.before(t) == true);
    try {
      foodList.addFood(new Food(ID, name, weight, type, place, date));
      foodList.saveAccounts();
      System.out.println("Created new food successfully!");
    } catch (Exception e) {
      System.out.println("Failed to create this food! Please try again!");
    }
  }

  public static void searchFood() {
    if (foodList.isEmpty()) System.out.println("The food is empty! No food is found...");
    else {
      cont = true;
      do {
        try {
          System.out.print("\tEnter date Food: ");
          //          String sName = sc.nextLine();
          Date date = format.parse(sc.nextLine());
          //          if (MyValidation.isEmptyString(sName)) throw new Exception("Name cannot be
          // empty!");
          LinkedList<Food> sList = foodList.searchByName(date);
          if (sList == null || sList.isEmpty()) System.out.println("No such a food to be found!");
          else {
            System.out.format(
                "%15s | %15s | %15s | %15s | %15s | %15s\n",
                "ID", "Name", "Weight", "Type", "Place", "Date");
            sList.forEach(
                (Food food) -> {
                  System.out.format("%15s | ", food.getID());
                  System.out.format("%15s | ", food.getName());
                  System.out.format("%15s | ", food.getWeight());
                  System.out.format("%15s | ", food.getType());
                  System.out.format("%15s | ", food.getPlace());
                  System.out.format("%15s%n", format.format(food.getDate()));
                });
          }
          cont = false;
        } catch (Exception e) {
          System.out.println(e.getMessage());
          cont = true;
        }
      } while (cont);
    }
  }

  public static void remoteFood() {
    if (foodList.isEmpty()) System.out.println("The food is empty! No food is found...");
    else {
      cont = true;
      do {
        try {
          System.out.print("\tEnter ID Food: ");
          String sID = sc.nextLine();
          if (MyValidation.isEmptyString(sID))
            throw new Exception("ID cannot be empty! Please try again...");
          if (foodList.search(sID) == null || foodList.isEmpty())
            System.out.println("No such a food to be found!");
          else {
            switch (MyValidation.isContinue("Do you really want to delete this food (y/n) ?")) {
              case 1:
                if (foodList.delete(foodList.search(sID))) System.out.println("SUCCESFULLY!");
                else System.out.println("FAILED! Please try again...");
                foodList.saveAccounts();
                break;
              case 0:
                break;
            }
          }
          cont = false;
        } catch (Exception e) {
          System.out.println(e.getMessage());
          cont = true;
        }
      } while (cont);
    }
  }

  public static void showFood() {
    if (foodList.isEmpty()) System.out.println("The Food is empty! No food is found...");
    else {
      Collections.sort(foodList);
      System.out.format(
          "%15s | %15s | %15s | %15s | %15s | %15s\n",
          "ID", "Name", "Weight", "Type", "Place", "Date");
      foodList.forEach(
          (Food food) -> {
            System.out.format("%15s | ", food.getID());
            System.out.format("%15s | ", food.getName());
            System.out.format("%15s | ", food.getWeight());
            System.out.format("%15s | ", food.getType());
            System.out.format("%15s | ", food.getPlace());
            System.out.format("%15s%n", format.format(food.getDate()));
          });
    }
  }
}
