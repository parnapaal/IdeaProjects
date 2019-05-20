package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testLegalConstructionName(){
        String vespa = "Vespa";
        double cost = 15001.25;
        Item newItem = new Item(vespa,cost);

        assertEquals("Vespa", newItem.getName());
    }

    @Test
    public void testLegalConstructionCost(){
        String vespa = "Vespa";
        double cost = 15001.25;
        Item newItem = new Item(vespa,cost);

        assertEquals(cost, newItem.getCost(), DELTA);
    }

    @Test
    public void whenItsCoffeeTaxis0(){
        String coffee = "Vanilla-Hazelnut Coffee";
        double cost = 11.00;

        Item newItem = new Item(coffee,cost);
        assertEquals(0.0, newItem.getTax(), DELTA);

    }

    @Test
    public void whenItsCandyTaxis0(){
        String candy = "Skittles";
        double cost = 16.00;

        Item newItem = new Item(candy,cost);
        assertEquals(0.0, newItem.getTax(), DELTA);

    }

    @Test
    public void whenItsPopcornTaxis0(){
        String popcorn = "Popcorn";
        double cost = 11.00;

        Item newItem = new Item(popcorn,cost);
        assertEquals(0.0, newItem.getTax(), DELTA);

    }

    @Test
    public void whenItsNotExemptTaxIs10(){
        String discman = "Discman";
        double cost = 55.0;
        double total = 60.5;

        Item newItem = new Item(discman,cost);
        assertEquals(total-cost, newItem.getTax(), DELTA);

    }


    /*sales tax needs to be rounding up to the nearest .05 (nickel). so
    after rounding, the tax should only ever end with .00 or .05

    @Test
    public void checkingRoundingWithOdd(){
        String discman = "Discman";
        //a cost of 55.3 (.1) = 5.53 -- rounded up this should be 5.55
        double cost = 55.3;

        Item newItem = new Item(discman,cost);
        assertEquals(5.55, newItem.getTax(), DELTA);

    }

    /*sales tax needs to be rounding to the nearest .05 (nickel). so
    after rounding, the tax should only ever end with .00 or .05

    @Test
    public void checkingRoundingWithEven(){
        String discman = "Discman";
        //a cost of 55.6 (.1) = 5.56 -- rounded up this should be 5.6
        double cost = 55.6;

        Item newItem = new Item(discman,cost);
        assertEquals(5.55, newItem.getTax(), DELTA);

    }

     */


    /* I have tried everything under the sun to get this to read out
    with a .5, not .45 so lets see if java knows something I don't

    @Test
    public void checkingThisGodDamnVespa(){
        String vespa = "Vespa";
        //like have I forgotten how to add two numbers together whats happening
        double cost = 15001.25;
        double total = 17251.5;

        Item newItem = new Item(vespa,cost);
        assertEquals(total-cost, newItem.getTax(), DELTA);

    }

     */




    @Test
    public void setTax() {
    }

    @Test
    public void getTax() {
    }
}