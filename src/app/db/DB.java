package app.db;

import app.bicycles.Bicycle;
import app.tickets.Ticket;
import app.users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public final class DB {
  public static final String urlBicycles = "/home/daviddeadly/Dev/Sofka/BiciU/src/app/db/bicycles.txt";
  public static final String urlTickets = "/home/daviddeadly/Dev/Sofka/BiciU/src/app/db/tickets.txt";
  public static final String urlUsers = "/home/daviddeadly/Dev/Sofka/BiciU/src/app/db/users.txt";

  static Random rand = new Random();
  public static List<Bicycle> getBicycles() {
    List<Bicycle> bicycles = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(
      new FileReader(urlBicycles)
    )) {
      String bici;
      while ((bici = br.readLine()) != null) {
        List<String> biciData = new ArrayList<>(Arrays.asList(bici.split(";")));
        String code = biciData.get(0);
        String type = biciData.get(1);
        String color = biciData.get(2);
        boolean isAvailable = Boolean.parseBoolean(biciData.get(3));
        bicycles.add(new Bicycle(code, type, color, isAvailable));
      }
      return bicycles;
    } catch (IOException err) {
      err.printStackTrace();
    }
    return Collections.emptyList();
  }

  public static Bicycle getRandomAvailableBicycle (String type) {
    List<Bicycle> bicycles = getBicycles();
    Bicycle.BiciType biciType = Bicycle.BiciType.valueOf(type);
    bicycles.removeIf(bicycle -> !bicycle.isAvailable);
    System.out.println(bicycles.size());
    bicycles.removeIf(bicycle -> bicycle.type != biciType);
    int quantity;
    if((quantity = bicycles.size()) != 0) {
      return bicycles.get(rand.nextInt(quantity));
    }
    return null;
  }

  public static void writeTicket(Ticket ticket) {
    try(BufferedWriter bw = new BufferedWriter(
      new FileWriter(urlTickets, true)
    )) {
      bw.write(ticket.toDBString());
    } catch (Exception err) {
      System.err.println(err.getMessage());
    }
  }
  public static void updateObjDBStatus(DBString obj, String url) {
    List<String> updatedFile = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(
        new FileReader(url)
      );
      String objLine;

      while((objLine = br.readLine()) != null) {
        if(objLine.contains(obj.code)) objLine = obj.toDBString();
        updatedFile.add(objLine);
      }
      br.close();
      BufferedWriter bw = new BufferedWriter(
        new FileWriter(url)
      );

      for(String line : updatedFile) {
        bw.write(line);
      }
      bw.close();
    } catch (Exception err) {
      err.printStackTrace();
//      System.err.println(err.getMessage());
    }
  }

  public static int nextTicketNumberCode() {
    int codeNumber = 0;
    try(BufferedReader br = new BufferedReader(
        new FileReader(urlTickets)
    )) {
      String ticketLine;
      while((ticketLine = br.readLine()) != null) {
        codeNumber = Integer.parseInt(ticketLine.split(";")[0].split("-")[1]);
      }
      return ++codeNumber;
    } catch (Exception err) {
      System.err.println(err.getMessage());
//      System.err.println("NO TICKET DATABASE, CREATING ONE....");
    }
    return ++codeNumber;
  }

  public static Ticket getTicket(String code) {
    try(BufferedReader br = new BufferedReader(
      new FileReader(urlTickets)
    )) {
      Stream<String> stringStream = br.lines().filter(l -> l.contains(code));
      ArrayList<String> ticketData = new ArrayList<>(Arrays.asList(stringStream.toList().get(0).split(";")));
//      T-001;BIC-17;S-1000293315;David Rueda;2022-07-16;13:15;null;true;true;OK;0
      String bicycle = ticketData.get(1);
      String user = ticketData.get(2);
      String name = ticketData.get(3);
      String date = ticketData.get(4);
      String startTime = ticketData.get(5);
      String endTime = ticketData.get(6);
      boolean haveHelmet = Boolean.parseBoolean(ticketData.get(7));
      boolean goodCondition = Boolean.parseBoolean(ticketData.get(8));
      Ticket.Status status = Ticket.Status.valueOf(ticketData.get(9));
      int amout = Integer.parseInt(ticketData.get(10));
      return new Ticket(code, bicycle, user, name, date, startTime, endTime, haveHelmet, goodCondition, status, amout);
    } catch(Exception err) {
      System.err.println(err.getMessage());
    }
    return null;
  }

  public static Bicycle getBicycle(String code) {
    try(BufferedReader br = new BufferedReader(
            new FileReader(urlBicycles)
    )) {
      Stream<String> stringStream = br.lines().filter(l -> l.contains(code));
      ArrayList<String> bicycleData = new ArrayList<>(Arrays.asList(stringStream.toList().get(0).split(";")));
      String type = bicycleData.get(1);
      String color = bicycleData.get(2);
      boolean isAvailable = Boolean.parseBoolean(bicycleData.get(3));
      return new Bicycle(code, type, color, isAvailable);
    } catch(Exception err) {
      System.err.println(err.getMessage());
    }
    return null;
  }

  public static void registerUser(User user) {
    try(BufferedWriter bw = new BufferedWriter(
      new FileWriter(urlUsers, true)
    )) {
      bw.write(user.toDBString());
    } catch(Exception err) {
      System.err.println(err.getMessage());
    }
  }

  public static User getUser(String code) {
    try(BufferedReader br = new BufferedReader(
        new FileReader(urlUsers)
    )) {
      List<String> strings = br.lines().filter(l -> l.contains(code)).toList();
      if(!strings.isEmpty()) {
        ArrayList<String> userData = new ArrayList<>(Arrays.asList(strings.get(0).split(";")));
        String type = String.valueOf(userData.get(0).charAt(0));
        String dni = userData.get(0).substring(2);
        String[] fullName = userData.get(1).split(" ");
        String name = fullName[0];
        String surname = fullName[1];
        int age = Integer.parseInt(userData.get(2));
        return new User(type, dni, name, surname, age);
      }
    } catch(Exception err) {
      System.err.println(err.getMessage());
    }
    return null;
  }
}
