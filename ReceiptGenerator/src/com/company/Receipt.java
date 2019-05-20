package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;


public class Receipt {
    public static void main(String[] args) throws Exception{


        //read in the entire file as one big string
        String content = openFile();


        //parse our big string into our shopping carts
        String[] entries = parseEntryForBaskets(content);

        //for each shopping cart, parse out the separate entries into a string array and create
        //an instance of ShoppingBasket
        for(int i = 0; i < entries.length; i++) {
            String[] oneBasket = parseBasketforItems(entries[i]);
            ShoppingBasket myBasket = new ShoppingBasket(oneBasket);

            System.out.println("Output " + (i+1) + ": ");
            myBasket.printBasket();
            System.out.println();

        }

    }


    //split out our super string into basket entry
    public static String[] parseEntryForBaskets(String entry){

        //split our string by basket
        String[] oneBasket = entry.split("Shopping Basket");

        //ignore oneBasket[0] as it holds nothing of interest
        String[] oneBasketCleaned = Arrays.copyOfRange(oneBasket, 1, oneBasket.length);
        oneBasketCleaned = clean(oneBasketCleaned);
        return oneBasketCleaned;
    }

    //split our basket by item
    public static String[] parseBasketforItems(String basket){

        //use the newline symbol to indicate a new item
        String[] itemEntries = basket.split("\n");

        //ignore itemEntries[0] as there is nothing there of interest
        String[] itemEntriesCleaned = Arrays.copyOfRange(itemEntries, 1, itemEntries.length);
        itemEntriesCleaned = clean(itemEntriesCleaned);

        return itemEntriesCleaned;

    }

    //open up our file
    public static String openFile() throws Exception{
        String userDir = System.getProperty("user.dir");
        String fileName = userDir + "/input.txt";


        String content = new String ( Files.readAllBytes(Paths.get(fileName) ) );

        return content;

    }

    //clean out extraneous info
    private static String[] clean(String[] name){
        for(int i=0; i<name.length; i++){
            name[i] = name[i].trim();
            name[i] = name[i].replace(",","");
        }

        return name;
    }


    }

