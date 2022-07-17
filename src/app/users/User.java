package app.users;

import app.db.*;
import app.errors.InvalidAge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User extends DBString {
  private enum UserType {
    S, P
  }
  private final String name;
  private final String surname;
  private final int age;
  private List<String> ticketDebts = new ArrayList<>();

  public User(String type, String dni, String name, String surname, int age) throws InvalidAge {
    super(UserType.valueOf(type) + "-" + dni);
    if(age <= 18) throw new InvalidAge("You must be 18 or older!!");
    this.name = name;
    this.surname = surname;
    this.age = age;
    DB.registerUser(this);
  }

  public User(String code, String fullName, int age) {
    super(code);
    String[] fName = fullName.split(" ");
    this.name = fName[0];
    this.surname = fName[1];
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  public String getfullName() {
    return this.name + " " + this.surname;
  }

  public void addDebt(String ticketCode) {
    ticketDebts.add(ticketCode);
    DB.updateObjDBStatus(this, DB.urlUsers);
  }

  public void loadDebts(String[] debts) {
    ticketDebts.addAll(Arrays.asList(debts));
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

  public static String askForUserType() {
    Scanner sc = new Scanner(System.in);
    String question = String.format(
      """
            What are you??
      (%s)tudent or (%s)rofossor
      """,
      UserType.S, UserType.P
    );
    String answer;
    boolean askAgain;

    do {
      System.out.print(question);
      answer = sc.nextLine().toUpperCase();
      try {
        UserType.valueOf(answer);
        askAgain = false;
      } catch (Exception err) {
        System.err.println("Invalid user type");
        askAgain = true;
      }
    } while (askAgain);
    return answer;
  }
}
