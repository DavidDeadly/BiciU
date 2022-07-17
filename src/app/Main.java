package app;

import app.menu.Menu;

import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    boolean seeAgain;
    do {
      String userOption = Menu.principal();
      if(Objects.equals(userOption, "6")) break;
      String message = appFlow(userOption);
      if(message != null ) System.out.println(message);
      seeAgain = askYesNo("Do you want to see the menu again?? [Y]/[N]");
      Menu.clearConsole();
    } while(seeAgain);

    System.out.println("Exiting...");
    System.exit(0);
  }

  private static String appFlow(String userOption) {
    String msg;
    switch (userOption) {
      case "1" -> msg = Menu.registerUser();
      case "2" -> msg = Menu.borrowBicycle();
      case "3" -> msg = Menu.returnBicycle();
      case "4" -> msg = Menu.payTicket();
      case "5" -> msg = Menu.ticketsHistory();
      default -> {
        System.err.println("INVALID OPTION!!");
        msg = "Please select a valid option!!";
      }
    }
    return msg;
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
