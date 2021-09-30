package Controller;

import Model.Food;
import Validation.MyValidation;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class FoodList extends ArrayList<Food> {
  static final String file = "food.txt";

  public boolean addFood(Food food) {
    try {
      this.add(food);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Food search(String ID) {
    try {
      Iterator<Food> iterator = this.iterator();
      while (iterator.hasNext()) {
        Food nFood = iterator.next();
        if (MyValidation.checkEqualString(ID, nFood.getID())) return nFood;
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  public LinkedList<Food> searchByName(Date date) {
    LinkedList<Food> rList = new LinkedList<>();
    try {
      Iterator<Food> iterator = this.iterator();
      while (iterator.hasNext()) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Food nFood = iterator.next();
        String dateFood = df.format(nFood.getDate());
        if (nFood.getDate().before(date)) rList.add(nFood);
      }
      return rList;
    } catch (Exception e) {
      return null;
    }
  }

  public boolean delete(Food food) {
    try {
      this.remove(food);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public void saveAccounts() {
    String write = "";
    PrintWriter w = null;
    for (Food food : this) write += food.toString() + "\n";
    try {
      w = new PrintWriter(new File(file));
      w.write(write);
      w.flush();
      if (w != null) w.close();
    } catch (Exception e) {
      if (w != null) w.close();
    }
  }
}
