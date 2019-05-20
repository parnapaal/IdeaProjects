package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    private static final double DELTA = 1e-15;

    //testing that the name is being updated into our item
    @Test
    public void testLegalConstructionName(){
        String vespa = "Vespa";
        double cost = 15001.25;
        Item newItem = new Item(vespa,cost);

        assertEquals("Vespa", newItem.getName());
    }

    //testing that the cost is being updated into our item
    @Test
    public void testLegalConstructionCost(){
        String vespa = "Vespa";
        double cost = 15001.25;
        Item newItem = new Item(vespa,cost);

        assertEquals(cost, newItem.getCost(), DELTA);
    }

    /*the following three tests ensure that if the words coffee, popcorn, or two types of candy
    are mentioned within our name string, the base tax is 0%. I would like to come back to these methods
    and implement the API that allows us to search the name via the internet to see if we can find
    the tag "candy" or "confection" instead of relying on a dict which is less airtight of a solution
     */
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

    //if there are no overlap within our dictionary and our name, the base tax is set at 10%
    @Test
    public void whenItsNotExemptTaxIs10(){
        String discman = "Discman";
        double cost = 55.0;
        double total = 60.5;

        Item newItem = new Item(discman,cost);
        assertEquals(total-cost, newItem.getTax(), DELTA);

    }


    /*sales tax needs to be rounding up to the nearest .05 (nickel). so
    after rounding, the tax should only ever end with .00 or .05 and it should never round down
    */

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
    */

    @Test
    public void checkingRoundingWithEven(){
        String discman = "Discman";

        //a cost of 55.6 (.1) = 5.56 -- rounded up this should be 5.6
        double cost = 55.6;

        Item newItem = new Item(discman,cost);
        assertEquals(5.6, newItem.getTax(), DELTA);

    }

    /* When the word "imported" is mentioned, we should add another 5% to the tax
     */
    @Test
    public void checkingImportTax(){

        String coffee = "imported bag of Vanilla-Hazelnut Coffee";

        double cost = 11.0;

        Item newItem = new Item(coffee,cost);
        assertEquals(0.55, newItem.getTax(), DELTA);


    }


    //make sure we don't care about the case of the word "import"
    @Test
    public void checkingImportTaxCaseShouldntMatter(){

        String coffee = "Imported bag of Vanilla-Hazelnut Coffee";

        double cost = 11.0;

        Item newItem = new Item(coffee,cost);
        assertEquals(0.55, newItem.getTax(), DELTA);


    }

    //check to ensure that the base tax and import tax will both be added to an item
    @Test
    public void checkingThatBaseAndImportTaxCombineCorrectly(){
        String vespa = "Imported Vespa";
        double cost = 15001.25;
        double total = 17251.5;

        Item newItem = new Item(vespa,cost);
        assertEquals(total-cost, newItem.getTax(), DELTA);

    }

}