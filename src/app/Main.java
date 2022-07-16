package app;

import app.bicycles.Bicycle;
import app.db.DB;
import app.tickets.Ticket;
import app.users.User;

public class Main {
  public static void main(String[] args) {
    try {
      User user = new User("S", "1000293315", "David", "Rueda", 19);
      Bicycle bicy = DB.getRandomAvailableBicycle("Mountain");
      assert bicy != null;
      System.out.println("Searching code: " + bicy.code);
      new Ticket(user, bicy);
      Bicycle bicy2 = DB.getRandomAvailableBicycle("Mountain");
      assert bicy2 != null;
      System.out.println("Searching code: " + bicy2.code);
      new Ticket(user, bicy2);
    } catch (Exception err) {
      System.err.println(err.getMessage());
    }
  }
}
