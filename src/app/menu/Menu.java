package app.menu;

import app.Main;
import app.bicycles.Bicycle;
import app.db.DB;
import app.tickets.Ticket;
import app.users.User;

import java.util.List;
import java.util.Objects;
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
      String name = sc.nextLine().trim();
      System.out.println("What's your surname:");
      String surname = sc.nextLine().trim();
      System.out.println("How old are you??");
      int age = sc.nextInt();

      User user = new User(type, dni, name, surname, age);
      return String.format(
              """
                ┬íRegister Completed!
                
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
    System.out.print("Enter User ID: ");
    String id = sc.nextLine().toUpperCase();
    User user = DB.getUser(id);
    String checkMsg = userChecks(user);

    if(checkMsg != null) return checkMsg;

    return generateTicket(user);
  }

  public static String returnBicycle() {
    Ticket ticket = getTicket();
    String checkMsg = returnChecks(ticket);

    if(checkMsg != null) return checkMsg;

    boolean haveHelmet = Main.askYesNo("The bicycle has its helmet?? [Y]/[N]");
    boolean goodCondition = Main.askYesNo("The bicycle & the helmet are in good conditions?? [Y]/[N]");
    assert ticket != null;
    ticket.returnBicycle(haveHelmet, goodCondition);
    ticket.consolePresentation("\nTicket updated!");
    return null;
  }

  public static String payTicket() {
    Ticket ticket = getTicket();
    String checkMsg = payChecks(ticket);
    if(checkMsg !=  null)  return checkMsg;
    assert ticket != null;
    ticket.consolePresentation("Ticket to pay!!");
    boolean wantsPay = Main.askYesNo("Proceed paymanet?? [Y]/[N]");
    if(!wantsPay) return null;
    ticket.payTicket();
    ticket.consolePresentation("\nTicket updated!!");
    return null;
  }

  public static String ticketsHistory() {
    clearConsole();
    List<Ticket> tickets = DB.getTickets();
    Scanner sc = new Scanner(System.in);
    System.out.println(
      """
      1 Show all tickets
      2 Search by code
      3 Search by status
      """
    );
    String userInput = sc.nextLine();
    switch (userInput) {
      case "1" -> showTickets(tickets);
      case "2" -> {
        System.out.print("Enter code: ");
        String code = sc.nextLine().toUpperCase();
        tickets.removeIf(t -> !Objects.equals(t.code, code));
        showTickets(tickets);
      }
      case "3" -> {
        String status = askStatus();
        if(status == null) {
          System.err.println("That status is not consideration!");
          return "Try another one!";
        }
        tickets.removeIf(t -> t.status != Ticket.Status.valueOf(status));
        showTickets(tickets);
      }
      default -> System.out.println("That kind of sort doesn't exist!");
    }

    return null;
  }

  private static String askStatus(){
    Scanner sc = new Scanner(System.in);
    System.out.println(
      """
        1. Active   2. Pending   3. OK
      """
    );
    String stOpt = sc.nextLine().toUpperCase();
    return stOpt.equals("1")
      ? Ticket.Status.Active.name()
      : stOpt.equals("2")
      ? Ticket.Status.Pending.name()
      : stOpt.equals("3")
      ? Ticket.Status.OK.name()
      : null;
  }

  private static void showTickets(List<Ticket> tickets) {
    System.out.println("Code  |  UserID      |  Name        | Amount($) |  Status");
    tickets.forEach(t -> System.out.printf(
      "%s  %s   %s         %d         %s%n",
      t.code, t.user, t.name, t.amount, t.status
    ));
  }

  private static Ticket getTicket() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter ticket ID: ");
    String id = sc.nextLine().toUpperCase();
    if(id.length() == 0) return null;
    return DB.getTicket(id);
  }

  private static String userChecks(User user) {
    if(user == null) {
      System.err.println("Apparently that user doesn't exist!");
      return "Try another one!";
    }

    if(user.isInDebt()) {
      System.err.println("You can't borrow bicycles");
      return "The user " + user.code + " has debts. Please pay them to try again";
    }
    return null;
  }

  private static String returnChecks(Ticket ticket) {
    if(ticket == null) {
      System.err.println("Apparently that ticket doesn't exist!");
      return "Try another one!";
    }

    if(ticket.alreadyReturn()) return "This ticket was already returned. Look for it at the history!";

    return null;
  }

  private static String payChecks(Ticket ticket) {
    if(ticket == null) {
      System.err.println("Apparently that ticket doesn't exist!");
      return "Try another one!";
    }

    if(ticket.alreadyOK()) return "This ticket is already OK!";

    if(!ticket.alreadyReturn()) return "This ticket is still active. Return the bike first";

    return null;
  }

  private static String generateTicket(User user) {
    String type = Bicycle.askForBikeType();
    Bicycle bike = DB.getRandomAvailableBicycle(type);

    if(bike == null) return "There's no " + type + " bicycles Available. Try another one.";

    System.out.printf(
      """
      Bicycle chosen!
      Code: %s
      Type: %s
      Color: %s
      """, bike.code, bike.type, bike.color
    );

    Ticket ticket = new Ticket(user, bike);
    ticket.consolePresentation("A ticket was generated");
    return null;
  }

  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
