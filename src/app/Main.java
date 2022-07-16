package app;

import app.tickets.Ticket;
import app.users.User;

public class Main {
  public static void main(String[] args) {
    try {
      User user = new User("S", "1000293315", "David", "Rueda", 19);
      Ticket ticket = new Ticket(user, "Mountain");
      System.out.println(ticket.code);
      System.out.println(ticket.bicycle);
      System.out.println(ticket.user);
      System.out.println(ticket.name);
      System.out.println(ticket.date);
      System.out.println(ticket.startTime);
      System.out.println(ticket.endTime);
      System.out.println(ticket.goodCondition);
      System.out.println(ticket.haveHelmet);
      System.out.println(ticket.status);
      System.out.println(ticket.amount);
    } catch (Exception err) {
      System.err.println(err.getMessage());
    }
  }
}
