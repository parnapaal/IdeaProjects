package com.company;

import java.util.List;
import java.util.Map;

public class

public class ShoppingBasket {

    private Map<String, List<Integer>> itemAndTax;

    private String[] exemptItems;

    static {

    }


    public ShoppingBasket(String[] basket) {
        //instantiate myBasket when called
        String test = "test";
    }

    private void fillPrices(String[] basket){
        //fill up the Map
    }


    public void printBasket() {
        //print out my basket
    }

    private double round(double price) {
        //round to the nearest .05
        return Math.round(price * 20.0) / 20.0;
    }



}
