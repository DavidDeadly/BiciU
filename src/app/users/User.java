package app.users;

import app.db.*;
import app.errors.InvalidAge;

import java.util.ArrayList;
import java.util.List;

public class User extends DBString {
  private enum UserType {
    S, P
  }
  private UserType type;
  private String dni;
  private String name;
  private String surname;
  public int age;
  public List<String> ticketDebts = new ArrayList<>();

  public User(String type, String dni, String name, String surname, int age) throws InvalidAge {
    super(type + "-" + dni);
    if(age <= 18) throw new InvalidAge("You must be 18 or older!!");
    this.type = UserType.valueOf(type);
    this.dni = dni;
    this.name = name;
    this.surname = surname;
    this.age = age;
    DB.registerUser(this);
  }

  public String getfullName() {
    return this.name + " " + this.surname;
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
