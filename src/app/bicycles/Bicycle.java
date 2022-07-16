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

  public String toDBString() {
    return String.format(
      "%s;%s;%s;%s;%n",
      this.code,
      this.type,
      this.color,
      this.isAvailable
    );
  }
}
