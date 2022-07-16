package app;

import app.errors.InvalidAge;
import app.tickets.Ticket;
import app.users.User;

import java.time.LocalTime;

public class Main {
  public static void main(String[] args) {
    try {

    User user = new User("S", "1000293351", "David", "Rueda", 19);
    Ticket ticket = new Ticket(user);
    System.out.println("code: " + ticket.code);
    System.out.println("bicycle: " + ticket.bicycle);
    System.out.println("user: " + ticket.user);
    System.out.println("name: " + ticket.name);
    System.out.println("date: " + ticket.date);
    System.out.println("startTime: " + ticket.startTime);
    System.out.println("endTime: " + ticket.endTime);
    System.out.println("haveHelmet: " + ticket.haveHelmet);
    System.out.println("goodCondition: " + ticket.goodCondition);
    System.out.println("amount: " + ticket.amount);
    Ticket ticket2 = new Ticket(user);
    System.out.println("code: " + ticket2.code);
    System.out.println("bicycle: " + ticket2.bicycle);
    System.out.println("user: " + ticket2.user);
    System.out.println("name: " + ticket2.name);
    System.out.println("date: " + ticket2.date);
    System.out.println("startTime: " + ticket2.startTime);
    System.out.println("endTime: " + ticket2.endTime);
    System.out.println("haveHelmet: " + ticket2.haveHelmet);
    System.out.println("goodCondition: " + ticket2.goodCondition);
    System.out.println("amount: " + ticket2.amount);
    } catch (InvalidAge err) {
      System.out.println(err.getMessage());;
    }
  }
}
