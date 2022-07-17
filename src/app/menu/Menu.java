package app.menu;

import app.db.DB;
import app.users.User;

import java.util.Scanner;

public final class Menu {
  public static String principal() {
    Scanner sc = new Scanner(System.in);
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
    return sc.nextLine();
  }

  public static String registerUser() {
    clearConsole();
    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("Please fill these fields...");
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
      return String.format(
              """
                ¡Register Completed!
                
                ID: %s
                Name: %s
                Age: %d
              """, user.code, user.getfullName(), user.getAge()
      );

    } catch (Exception err) {
      System.err.println("There was an error creating the user!! Try again");
      System.err.println("Error: " + err.getMessage());
    }
    return null;
  }

  public static String borrowBicycle() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter ID: ");
    String id = sc.nextLine().toUpperCase();
    User user = DB.getUser(id);
    if(user == null) {
      System.err.println("Apparently that user doesn't exist!");
      return "Try another one!";
    }

    System.out.println(user.toDBString());

    return null;
  }

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
