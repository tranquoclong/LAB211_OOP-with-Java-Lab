package View;

import Core.Menu;

public class Main {
  public static void main(String[] args) {
    Menu menu = new Menu();
    menu.add("Show injection information");
    menu.add("Add an injection");
    menu.add("Update injection information");
    menu.add("Delete injection");
    menu.add("Search injection by studentID");
    menu.add("Exit");
    int choice = 0;
    do {
      choice = menu.getFoodChoice();
      switch (choice) {
        case 1:
          Management.showInjectionInformation();
          break;
        case 2:
          Management.addInjection();
          break;
        case 3:
          Management.updateInjection();
          break;
        case 4:
          Management.deleteInjection();
          break;
        case 5:
          Management.searchByStudentID();
          break;
        case 6:
          break;
      }
    } while (choice != 6);
  }
}
