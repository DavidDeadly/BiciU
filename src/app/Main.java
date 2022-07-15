package app;

import app.bicycles.Bicycle;

public class Main {
  public static void main(String[] args) {
    Bicycle.readBicycles();
    for(Bicycle bici : Bicycle.bicycles) {
      System.out.println("____________________________");
      System.out.println("code: " + bici.code);
      System.out.println("type: " + bici.type);
      System.out.println("color: " + bici.color);
      System.out.println("isAvailable: " + bici.isAvailable);
      System.out.println("----------------------------");
    }
  }
}
