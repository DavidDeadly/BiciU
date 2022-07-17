package app.bicycles;

import app.db.*;
import app.users.User;
import java.util.Objects;

import java.util.Scanner;

public class Bicycle extends DB {
  public enum BiciType {
    Mountain,
    Road,
  }
  public BiciType type;
  public String color;
  public boolean isAvailable;

  public Bicycle(String code, String type, String color, boolean isAvailable) {
    super(code);
    this.type = BiciType.valueOf(type);
    this.color = color;
    this.isAvailable = isAvailable;
  }

  public void setAvailable(boolean available) {
    this.isAvailable = available;
    DB.updateObjDBStatus(this, DB.urlBicycles);
  }

  public String toDBString() {
    return String.format(
      "%s;%s;%s;%s%n",
      this.code,
      this.type,
      this.color,
      this.isAvailable
    );
  }

  public static String askForBikeType() {
    Scanner sc = new Scanner(System.in);
    String question = String.format(
            "Choose a bike type %s-(M) or %s-(R): ",
            BiciType.Mountain, BiciType.Road
    );
    String answer;

    do {
      System.out.print(question);
      answer = sc.nextLine().toUpperCase();
      if("M".equals(answer) || "R".equals(answer)) {
        String mont = BiciType.Mountain.name();
        String road = BiciType.Road.name();
        answer = Objects.equals(answer, String.valueOf(mont.charAt(0))) ?
        mont : road;
        break;
      }
    } while (true);
    return answer;
  }
}
