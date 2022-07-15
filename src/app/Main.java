package app;

import app.bicycles.Bicycle;
import app.bicycles.BiciType;

public class Main {
    public static void main(String[] args) {
        Bicycle bc = new Bicycle("BIC-01", BiciType.valueOf("Mountain"), "red", true);
        System.out.println(bc.code);
        System.out.println(bc.type);
        System.out.println(bc.color);
        System.out.println(bc.isAvailable);
    }
}
