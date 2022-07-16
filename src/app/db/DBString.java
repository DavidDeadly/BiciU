package app.db;

public abstract class DBString {
  public String code;
  public abstract String toDBString();

  protected DBString(String code) {
    this.code = code;
  }
}
