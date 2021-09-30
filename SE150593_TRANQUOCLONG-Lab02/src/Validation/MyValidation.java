package Validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MyValidation {
  private static Scanner sc = new Scanner(System.in);
  private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  public static Date isValidParseDate(String inputDate) {
    try {
      Date date =  dateFormat.parse(inputDate);
      return date;
    } catch (ParseException pe) {
      return null;
    }
  }
  public static boolean isValidSecondDate(Date firstDate, Date secondDate) {
    Calendar calendar = Calendar.getInstance();
    Date head = null;
    Date tail = null;
    String firstDateString = dateFormat.format(firstDate);
    try {
      calendar.setTime(dateFormat.parse(firstDateString));
    } catch (ParseException e) {
      System.out.println("Something went wrong parsing");
    }
    calendar.add(Calendar.DATE, 28);
    head = MyValidation.isValidParseDate(dateFormat.format(calendar.getTime()));
    calendar.add(Calendar.DATE, 84);
    tail = MyValidation.isValidParseDate(dateFormat.format(calendar.getTime()));
    if (secondDate.before(head) || secondDate.after(tail)) {
      System.out.println("The second injection must be given 4 to 12 weeks after the first injection!");
      return false;
    } else return true;
  }

  public static Date getDate() {
    Date date;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    format.setLenient(false);
    while (true) {
      try {
        System.out.print("\tEnter expired date (dd/mm/yyyy; Ex: 13/9/2021): ");
        date = format.parse(sc.nextLine());
        format.format(date);
        return date;
      } catch (ParseException ex) {
        System.out.println("Please enter a valid expired date, (dd/mm/yyyy; Ex: 13/9/2021)");
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
}
