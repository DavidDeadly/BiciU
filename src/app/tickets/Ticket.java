package app.tickets;

import app.bicycles.Bicycle;
import app.db.DB;
import app.users.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Ticket {
  enum Status {
    Active, Pending, OK
  }
  private static int numTicket = DB.nextTicketNumberCode();
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

  public Ticket(User user, Bicycle bicy){
    this.startTime = setTime();
    this.date = LocalDate.now().toString();
    this.name = user.getfullName();
    this.user = user.getId();
    this.code = String.format("T-%03d", numTicket);
    this.bicycle = bicy.code;
    bicy.setAvailable(false);
    DB.writeTicket(this);
    numTicket++;
  }

  public static String setTime() {
    return "" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
  }

  public String toDBString() {
    return String.format(
      "%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s%n",
      this.code,
      this.bicycle,
      this.user,
      this.name,
      this.date,
      this.startTime,
      this.endTime,
      this.goodCondition,
      this.haveHelmet,
      this.status,
      this.amount
    );
  }
}
