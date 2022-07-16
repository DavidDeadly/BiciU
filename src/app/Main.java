package app;

import app.errors.InvalidAge;
import app.users.User;

public class Main {
  public static void main(String[] args) {
    try {
      User david = new User("Studen", "1000293315", "David", "Rueda", 19);
      System.out.println(david.id);
      System.out.println(david.name + " " + david.surname);
      System.out.println(david.age);
    } catch (InvalidAge err) {
      System.err.println(err.getMessage());
    }
  }
}
