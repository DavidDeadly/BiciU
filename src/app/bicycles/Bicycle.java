package app.bicycles;

import app.db.*;

public class Bicycle extends DBString {
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
      "%s;%s;%s;%s",
      this.code,
      this.type,
      this.color,
      this.isAvailable
    );
  }
}
