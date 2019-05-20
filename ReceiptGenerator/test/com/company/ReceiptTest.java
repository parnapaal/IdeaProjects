package com.company;

import org.junit.Test;

import java.io.File;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ReceiptTest {

    /*
    @Test
    public void main() {
    }
     */

    /*
    @Test
    public static void testFileOpeningCorrectly() throws Exception {
        Receipt receipt = new Receipt();

        int worked ok = 0;

        //assertEquals(verify, content);

    }

     */

    //test that the file input.txt is being opened and all its information is being read in into a
    //string
    @Test
    public void testStringReadingInCorrectly() throws Exception {

        String userDir = System.getProperty("user.dir");
        String fileName = userDir + "/input.txt";


        String content = new String ( Files.readAllBytes(Paths.get(fileName) ) );

        String verify = "Input\n" +
                "Shopping Basket 1:\n" +
                "1 16lb bag of Skittles at 16.00\n" +
                "1 Walkman at 99.99\n" +
                "1 bag of microwave Popcorn at 0.99\n" +
                "\n" +
                "Shopping Basket 2:\n" +
                "1 imported bag of Vanilla-Hazelnut Coffee at 11.00\n" +
                "1 Imported Vespa at 15,001.25\n" +
                "\n" +
                "Shopping Basket 3:\n" +
                "1 imported crate of Almond Snickers at 75.99\n" +
                "1 Discman at 55.00\n" +
                "1 Imported Bottle of Wine at 10.00\n" +
                "1 300# bag of Fair-Trade Coffee at 997.99\n";


        assertEquals(verify,content);
    }

    //test that parsing our superstring of all our baskets is creating a string array of baskets
    @Test
    public void testEntryParsingbyShoppingBasket() throws Exception{


        String basket1 = "1:\n" +
                "1 16lb bag of Skittles at 16.00\n" +
                "1 Walkman at 99.99\n" +
                "1 bag of microwave Popcorn at 0.99";

        String basket2 = "2:\n" +
                "1 imported bag of Vanilla-Hazelnut Coffee at 11.00\n" +
                "1 Imported Vespa at 15001.25";


        String basket3 = "3:\n" +
                "1 imported crate of Almond Snickers at 75.99\n" +
                "1 Discman at 55.00\n" +
                "1 Imported Bottle of Wine at 10.00\n" +
                "1 300# bag of Fair-Trade Coffee at 997.99";

        String[] myBaskets = {basket1, basket2, basket3};

        Receipt receipt = new Receipt();
        String entry = receipt.openFile();

        String[] entries = receipt.parseEntryForBaskets(entry);

        assertArrayEquals(myBaskets, entries);
    }


    //test that what is being passed to ShoppingBasket.class is showing up with now extra information
    //just the array of items, prices, and quantity that will then be parsed further
    @Test
    public void testEntryParsingbyShoppingBasketItems() throws Exception{

        String[] expectedEntries =  {"1 16lb bag of Skittles at 16.00",
                "1 Walkman at 99.99",  "1 bag of microwave Popcorn at 0.99"};

        String parseTheseItems = "1:\n" +
                "1 16lb bag of Skittles at 16.00\n" +
                "1 Walkman at 99.99\n" +
                "1 bag of microwave Popcorn at 0.99";

        Receipt receipt = new Receipt();
        String[] parsedItems = receipt.parseBasketforItems(parseTheseItems);



        assertArrayEquals(expectedEntries, parsedItems);
    }


}