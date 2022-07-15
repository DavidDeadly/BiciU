package app.bicycles;

public class Bicycle {
  public String code;
  public BiciType type;
  public String color;
  public boolean isAvailable;

  public Bicycle(String code, BiciType type, String color, boolean isAvailable) {
    this.code = code;
    this.type = type;
    this.color = color;
    this.isAvailable = isAvailable;
  }
}
