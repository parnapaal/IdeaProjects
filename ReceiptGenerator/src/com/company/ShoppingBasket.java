package com.company;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShoppingBasket {
    /*this map contains our entire basket as well as the number of
    our entries and the cost of the item (kept in List<Double>)
     */
    private Map<Item, Integer> basket;

    //instantiate our basket
    public ShoppingBasket(String[] entries) {
        basket = new HashMap<>();
        for(String entry : entries) {
            parse(entry);
        }

    }

    public Map<Item, Integer> getBasket(){
        return basket;
    }


    //parse each line into the necessary parts to store within our Map
    private void parse(String entry){

        //number of the type of item in the cart is the first integer in the entry (needs to be cast)
        Integer numberOfItems =  Integer.parseInt(entry.split(" ")[0]);

        //remove this integer
        entry = entry.split(" ",2)[1];

        //price of the item is the last item (double) in the entry
        double price = Double.parseDouble(entry.substring(entry.lastIndexOf(" ")+1));
        entry = entry.replaceFirst(entry.substring(entry.lastIndexOf(" ")+1), "");

        //remove unecessary wording
        entry = entry.replaceFirst(" at ", "").trim();
        //debug: .strip()

        //add our entry to the basket
        basket.put(createNewItem(entry,price),numberOfItems);

    }

    //create a new instance of an item to add to our basket
    private Item createNewItem(String name, double cost) {
        Item newItem = new Item(name, cost);
        return newItem;

    }


    public Item getCartEntry(){

        return null;
    }


    //print out my basket
    public void printBasket() {
        double totalTax = 0.0;
        double totalCost = 0.0;
        String format = "%-40s%s%n";

        //iterate through the basket
        for(Map.Entry<Item,Integer> e: basket.entrySet()){

            //get the item
            Item item = e.getKey();

            //get how many of the item
            Integer howMany = e.getValue();

            double subtotal = howMany * (item.getTax() + item.getCost());

            totalTax += howMany * (item.getTax());
            totalCost += subtotal;


            //TODO: these aren't printing out in the correct order -- forgot that HashMaps are unordered
            System.out.println("    " + howMany + " " + item.getName() + ": " + subtotal);
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.println("    Sales Tax: " + totalTax);
        System.out.println( "    Total: " +  formatter.format(totalCost));
    }




}
