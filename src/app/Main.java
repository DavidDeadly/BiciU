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
//      DB.writeBicycle(bicy);
      new Ticket(user, bicy);
    } catch (Exception err) {
      System.err.println(err.getMessage());
    }
  }
}
