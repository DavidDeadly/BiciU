package app.menu;

import app.users.User;

import java.sql.SQLOutput;
import java.util.Scanner;

public final class Menu {

  public static String principal() {
    System.out.println(
      """
      1. Register User
      2. Borrow Bicycle
      3. Return Bicycle
      4. Pay tickets
      5. Tickets history
      6. Exit
      """
    );
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  public static void registerUser() {
    clearConsole();
    try {
      System.out.println("Please fill these fields...");
      Scanner sc = new Scanner(System.in);
      String type = User.askForUserType();
      System.out.println("Insert your dni:");
      String dni = sc.nextLine();
      System.out.println("What's your name:");
      String name = sc.nextLine();
      System.out.println("What's your surname:");
      String surname = sc.nextLine();
      System.out.println("How old are you??");
      int age = sc.nextInt();

      User user = new User(type, dni, name, surname, age);

      System.out.println(
        String.format(
          """
            ¡Register Completed!
            
            ID: %s
            Name: %s
            Age: %d
          """, user.code, user.getfullName(), user.getAge()
        )
      );

    } catch (Exception err) {
      System.err.println("There was an error creating the user!! Try again");
      System.err.println("Error: " + err.getMessage());
    }

  }

  public static boolean askYesNo(String question) {
    Scanner input = new Scanner(System.in);
    String answer;
    boolean askAgain;

    do {
      System.out.print(question);
      answer = input.nextLine().trim().toUpperCase();
      askAgain = !"Y".equals(answer) && !"N".equals(answer);
    } while (askAgain);

    return "Y".equals(answer);
  }

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
