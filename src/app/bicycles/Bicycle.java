package app.bicycles;

public class Bicycle {
  public enum BiciType {
    Mountain,
    Road,
  }
  public String code;
  public BiciType type;
  public String color;
  public boolean isAvailable;

  public Bicycle(String code, String type, String color, boolean isAvailable) {
    this.code = code;
    this.type = BiciType.valueOf(type);
    this.color = color;
    this.isAvailable = isAvailable;
  }
}
