package app;

import app.menu.Menu;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    boolean seeAgain;
    do {
      String userOption = Menu.principal();
      String message = appFlow(userOption);
      if(message != null ) System.out.println(message);
      seeAgain = askYesNo("Do you want tu see the menu again?? [Y]/[N]");
      Menu.clearConsole();
    } while(seeAgain);
  }

  private static String appFlow(String userOption) {
    switch (userOption) {
      case "1":
        return Menu.registerUser();
      case "2":
        return Menu.borrowBicycle();
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
        break;
      default:
        System.err.println("INVALID OPTION!!");
        return "Please select a valid option!!";
    }
    return null;
  }

  public static boolean askYesNo(String question) {
    Scanner sc = new Scanner(System.in);
    String answer;
    boolean askAgain;

    do {
      System.out.print(question);
      answer = sc.nextLine().trim().toUpperCase();
      askAgain = !"Y".equals(answer) && !"N".equals(answer);
    } while (askAgain);

    return "Y".equals(answer);
  }
}
