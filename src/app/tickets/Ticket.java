package app.tickets;

import app.users.User;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket {
  enum Status {
    Active, Pending, OK
  }
  private static int numTicket = 0;
  public String code;
  public String date;
  public String startTime;
  public String user;
  public String name;
  public String bicycle;
  public String endTime = null;
  public boolean haveHelmet = true;
  public boolean goodCondition = true;
  public Status status = Status.Active;
  public int amount = 0;


  public Ticket(User user){
    this.startTime = setTime();
    this.date = LocalDate.now().toString();
    this.name = user.getfullName();
    this.user = user.getId();
    this.code = String.format("T-%03d", numTicket);
    numTicket++;
  }

  public static String setTime() {
    return "" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
  }
}
