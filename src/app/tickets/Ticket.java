package app.tickets;

import app.bicycles.Bicycle;
import app.db.*;
import app.users.User;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Ticket extends DBString {
  enum Status {
    Active, Pending, OK
  }
  private static int numTicket = DB.nextTicketNumberCode();
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
    super(String.format("T-%03d", numTicket));
    this.startTime = setTime();
    this.date = LocalDate.now().toString();
    this.name = user.getfullName();
    this.user = user.code;
    this.bicycle = bicy.code;
    bicy.setAvailable(false);
    DB.writeTicket(this);
    numTicket++;
  }

  public Ticket(String code, String bicycle, String user, String name, String date, String startTime) {
    super(code);
    this.bicycle = bicycle;
    this.user = user;
    this.name = name;
    this.date = date;
    this.startTime = startTime;
  }

  public static String setTime() {
    return String.format(
      "%02d:%2d",
      LocalTime.now().getHour(),
      LocalTime.now().getMinute()
    );
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

  public void returnBicycle(boolean haveHelmet, boolean goodCondition) {
    Bicycle bicy = DB.getBicycle(this.bicycle);
    assert bicy != null;
    bicy.setAvailable(true);
    this.endTime = setTime();
    this.haveHelmet = haveHelmet;
    this.goodCondition = goodCondition;
    this.amount = getTotalDebt();
    if(this.amount > 0) {
      this.status = Status.Pending;
    } else this.status = Status.OK;
    DB.updateObjDBStatus(this, DB.urlTickets);
  }

  private int getTotalDebt() {
    int totalDebt = 0;
    if(!this.haveHelmet) totalDebt += 5;
    if(!this.goodCondition) totalDebt += 5;
    long thirtyMinutesComplete = getTimeDiffer() / 30;
    if(thirtyMinutesComplete != 0) {
      thirtyMinutesComplete--;
      totalDebt += thirtyMinutesComplete * 3;
    }
    return totalDebt;
  }

  private long getTimeDiffer() {
    Duration timeDiffer = Duration.between(
      LocalTime.parse(this.startTime),
      LocalTime.parse(this.endTime)
    );
    return timeDiffer.toMinutes();
  }
}
