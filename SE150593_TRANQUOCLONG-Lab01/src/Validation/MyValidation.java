package Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyValidation {
  private static Scanner sc = new Scanner(System.in);

  public static boolean checkString(String s, String pattern) {
    return s.matches(pattern);
  }

  public static boolean checkEqualString(String s, String x) {
    return s.equals(x);
  }

  public static boolean isEmptyString(String s) {
    return s.trim().isEmpty();
  }

  public static boolean isPlace(String place) {
    return place.toLowerCase().matches("f|s|Freezer|Freezing Shelf");
  }

  public static boolean isWeight(String weight) {
    return checkString(weight, "(([0-9]){1,5})");
  }

  public static boolean isName(String name) {
    return checkString(name, "[a-zA-Z ]{2,35}");
  }

  public static String convertPlace(String place) {
    if (isPlace(place))
      if (place.equalsIgnoreCase("Freezer") || place.equalsIgnoreCase("f")) return "Freezer";
      else if (place.equalsIgnoreCase("Freezing Shelf") || place.equalsIgnoreCase("s"))
        return "Freezing Shelf";
    return "";
  }

  public static Date getDate() {
    Date date;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    format.setLenient(false);
    while (true) {
      try {
        System.out.print("\tEnter expired date (dd/mm/yyyy; Ex: 13/10/2021): ");
        date = format.parse(sc.nextLine());
        format.format(date);
        return date;
      } catch (ParseException ex) {
        System.out.println("Please enter a valid expired date, (dd/mm/yyyy; Ex: 13/10/2021)");
      }
    }
  }

  public static int isContinue(String message) {
    Scanner sc = new Scanner(System.in);
    boolean cont = true;
    int result = -1;
    do {
      System.out.print(message);
      String answer = sc.nextLine();
      if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
        result = 1;
        cont = false;
      } else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
        result = 0;
        cont = false;
      } else cont = true;
    } while (cont);
    return result;
  }

  public static boolean isID(String ID) {
    return checkString(ID, "([a-zA-Z0-9_.]){3,40}");
  }
}
