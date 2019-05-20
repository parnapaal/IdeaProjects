package com.company;

import java.math.BigDecimal;

public class test {
    public static void main(String[] args) {
        double test = 5.56;
        double base = round(test);
        System.out.println(base);







    }

    private static double round(double price){

        int doubleToInt = (int)Math.round(price* 100);
        doubleToInt = (doubleToInt + 4) / 5 * 5;
        //System.out.println(doubleToInt);

        double intToDouble = doubleToInt / 100.00;
        return intToDouble;

        //return (Math.round(((price)) * 20) / 20.0);

    }

    public static int roundUp(int n) {
        return (n + 4) / 5 * 5;
    }
}
