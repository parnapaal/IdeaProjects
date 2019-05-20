package com.company;

import java.util.*;
import java.io.*;


public class Receipt {
    public static int ArrayLength;
    public static void main(String[] args) throws Exception{

        //TODO: change this later
        String filename = "test";
        String wordOfInterest = "Shopping Basket";


        String content = new Scanner(new File("filename")).useDelimiter("\\Z").next();
        System.out.println(content);

        String[] entries = parseEntry(content);

        ShoppingBasket myBasket = new ShoppingBasket(entries);
        myBasket.printBasket();


    }

    public static String[] parseEntry(String entry){

        //output still needs to be cleaned up
        //TODO: remove extraneous data
        ArrayLength = Arrays.toString(entry.split("Shopping Basket").length;
        return Arrays.toString(entry.split("Shopping Basket");

    }


    }

}
