package app.users;

import app.errors.InvalidAge;

public class User {
  private enum UserType {
    S, P
  }
  private UserType type;
  private String dni;
  private String name;
  private String surname;
  public int age;

  public User(String type, String dni, String name, String surname, int age) throws InvalidAge {
    if(age <= 18) throw new InvalidAge("You must be 18 or older!!");
    this.type = UserType.valueOf(type);
    this.dni = dni;
    this.name = name;
    this.surname = surname;
    this.age = age;
  }

  public String getfullName() {
    return this.name + " " + this.surname;
  }

  public String getId() {
    return this.type + "-" + this.dni;
  }
}
