package app;

import app.bicycles.Bicycle;
import app.db.DB;
import app.tickets.Ticket;
import app.users.User;


public class Main {
  public static void main(String[] args) {
//    try {
//      User user = new User("S", "1000293315", "David", "Rueda", 19);
//      Bicycle bicy = DB.getRandomAvailableBicycle("Road");
//      assert bicy != null;
//      System.out.println("Searching code: " + bicy.code);
//      Ticket ticket1 = new Ticket(user, bicy);
        Ticket ticket2 = DB.getTicket("T-001");
        assert ticket2 != null;
        ticket2.payTicket();
//        ticket2.returnBicycle(true, false);
//    } catch (Exception err) {
//      System.err.println(err.getMessage());
//    }
  }
}
