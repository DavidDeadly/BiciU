package app.bicycles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bicycle {
  private static final List<Bicycle> bicycles = new ArrayList<>();
  public String code;
  public BiciType type;
  public String color;
  public boolean isAvailable;

  private Bicycle(String code, BiciType type, String color, boolean isAvailable) {
    this.code = code;
    this.type = type;
    this.color = color;
    this.isAvailable = isAvailable;
    bicycles.add(this);
  }

  private static void readBicycles() {
    try {
      BufferedReader br = new BufferedReader(
        new FileReader(
"/home/daviddeadly/Dev/Sofka/BiciU/src/db/bicycles.txt"
        )
      );

      String bici;
      while((bici = br.readLine()) != null) {
        List<String> biciData = new ArrayList<>(Arrays.asList(bici.split(";")));
        String code = biciData.get(0);
        BiciType type = BiciType.valueOf(biciData.get(1));
        String color = biciData.get(2);
        Boolean isAvailable = Boolean.parseBoolean(biciData.get(3));
        new Bicycle(code, type, color, isAvailable);
      }
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  public static List<Bicycle> getUpdatedBicycles() {
    bicycles.clear();
    readBicycles();
    return bicycles;
  }
}
