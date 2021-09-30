package View;

import Controller.FoodController;
import Model.Menu;

public class Main {

  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.add("Add a new food");
    menu.add("Search a food by name");
    menu.add("Remove the food by ID");
    menu.add("Print the food list in the descending order of expired date");
    menu.add("Exit");
    int choice = 0;
    do {
      choice = menu.getFoodChoice();
      switch (choice) {
        case 1:
          FoodController.addFood();
          break;
        case 2:
          FoodController.searchFood();
          break;
        case 3:
          FoodController.remoteFood();
          break;
        case 4:
          FoodController.showFood();
          break;
        case 5:
          break;
      }
    } while (choice != 5);
  }
}
