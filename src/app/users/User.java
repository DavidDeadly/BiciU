package app.users;

import app.db.*;
import app.errors.InvalidAge;

import java.util.ArrayList;
import java.util.List;

public class User extends DBString {
  private enum UserType {
    S, P
  }
  private String name;
  private String surname;
  private int age;
  private List<String> ticketDebts = new ArrayList<>();

  public User(String type, String dni, String name, String surname, int age) throws InvalidAge {
    super(UserType.valueOf(type) + "-" + dni);
    if(age <= 18) throw new InvalidAge("You must be 18 or older!!");
    this.name = name;
    this.surname = surname;
    this.age = age;
    DB.registerUser(this);
  }

  public String getfullName() {
    return this.name + " " + this.surname;
  }

  public void addDebt(String ticketCode) {
    ticketDebts.add(ticketCode);
    DB.updateObjDBStatus(this, DB.urlUsers);
  }

  public void removeDebt(String ticketCode) {
    ticketDebts.remove(ticketCode);
    DB.updateObjDBStatus(this, DB.urlUsers);
  }

  public boolean isInDebt() {
    return !this.ticketDebts.isEmpty();
  }

  public String toDBString() {
    return String.format(
      "%s;%s;%s;%s%n",
      this.code,
      this.getfullName(),
      this.age,
      String.join("/", this.ticketDebts)
    );
  }
}
