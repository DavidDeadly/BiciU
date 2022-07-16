package app.db;

import app.bicycles.Bicycle;
import app.tickets.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public final class DB {
  static Random rand = new Random();
  public static List<Bicycle> getBicycles() {
    List<Bicycle> bicycles = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(
      new FileReader(
    "/home/daviddeadly/Dev/Sofka/BiciU/src/app/db/bicycles.txt"
      )
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
    bicycles.removeIf(bicycle -> bicycle.type != biciType);
    int quantity;
    if((quantity = bicycles.size()) != 0) {
      return bicycles.get(rand.nextInt(quantity));
    }
    return null;
  }

  public static void writeTicket(Ticket ticket) {
    try(BufferedWriter bw = new BufferedWriter(
            new FileWriter("/home/daviddeadly/Dev/Sofka/BiciU/src/app/db/tickets.txt", true)
    )) {
      System.out.println(ticket.toString());
      bw.write(ticket.toDBString());
    } catch (Exception err) {
      System.err.println(err.getMessage());
    }
  }
}
