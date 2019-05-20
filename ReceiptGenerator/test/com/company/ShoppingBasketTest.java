package com.company;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ShoppingBasketTest {

    private static final double DELTA = 1e-15;

    @Test
    public void testCorrectParsing() {

        String[] entries = {"1 16lb bag of Skittles at 16.00",
                "1 Walkman at 99.99",
                "1 bag of microwave Popcorn at 0.99"};

        String toTest = "16lb bag of Skittles Walkman bag of microwave Popcorn";



        ShoppingBasket newBasket = new ShoppingBasket(entries);
        Map<Item, Integer> allItems =  newBasket.getBasket();

        int foundIt = 0;
        for (Map.Entry<Item,Integer> e :allItems.entrySet()){
            Item tryToFind = e.getKey();
            String itemName = tryToFind.getName();

            if(toTest.contains(itemName)){
                foundIt++;
            }

        }


        assertEquals(3,foundIt);


    }

    @Test
    public void testCorrectTotals() {
        String[] entries = {"1 16lb bag of Skittles at 16.00",
                "1 Walkman at 99.99",
                "1 bag of microwave Popcorn at 0.99"};

        double entryTotals = 16.00 + 99.99 + .99;

        ShoppingBasket newBasket = new ShoppingBasket(entries);
        Map<Item, Integer> allItems =  newBasket.getBasket();

        double foundAllCosts = 0.0;
        for (Map.Entry<Item,Integer> e :allItems.entrySet()){
            Item tryToFind = e.getKey();
            foundAllCosts += tryToFind.getCost();

        }

        assertEquals(entryTotals,foundAllCosts, DELTA);





    }

    @Test
    public void testCorrectTaxes() {
        String[] entries = {"1 16lb bag of Skittles at 16.00",
                "1 Walkman at 99.99",
                "1 bag of microwave Popcorn at 0.99"};


    }

    @Test
    public void testCorrectPrintout() {
        String[] entries = {"1 16lb bag of Skittles at 16.00",
                "1 Walkman at 99.99",
                "1 bag of microwave Popcorn at 0.99"};


    }

    /*

    @Test
    public void testBasketReturn() {
        String[] entries = {"1 16lb bag of Skittles at 16.00",
                "1 Walkman at 99.99",
                "1 bag of microwave Popcorn at 0.99"};

     */


    }



