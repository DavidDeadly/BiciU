package app;

import app.menu.Menu;

public class Main {
  public static void main(String[] args) {
    boolean seeAgain;
    do {
      String userOption = Menu.principal();
      appFlow(userOption);
      seeAgain = Menu.askYesNo("Do you want tu see the menu again?? [Y]/[N]");
      Menu.clearConsole();
    } while(seeAgain);
  }

  private static void appFlow(String userOption) {
    switch (userOption) {
      case "1":
        Menu.registerUser();
        break;
      case "2":
        System.out.println("Borrowing bicycle...");
        break;
      case "3":
        System.out.println("Returning bicycle...");
        break;
      case "4":
        System.out.println("Paying tickets...");
        break;
      case "5":
        System.out.println("Tickets history...");
        break;
      case "6":
        System.out.println("Exiting...");
        return;
      default:
        System.err.println("INVALID OPTION!!");
        System.out.println("Please select a valid option!!");
    }
  }
}
