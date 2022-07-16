package app.users;

import app.errors.InvalidAge;

public class User {
  private enum UserType {
    S, P
  }
  public String id;
  public String name;
  public String surname;
  public int age;

  public User(String type, String dni, String name, String surname, int age) throws InvalidAge {
    if(age <= 18) throw new InvalidAge("You must be 18 or older!!");
    this.id = generateId(type, dni);
    this.name = name;
    this.surname = surname;
    this.age = age;
  }

  public static String generateId(String type, String dni) {
    try {
      UserType userType = UserType.valueOf(type);
      return  userType + "-" + dni;
    } catch (Exception err) {
      System.err.println("The type provide is neither (S)tudent nor (P)rofessor");
    }
    return null;
  }
}
