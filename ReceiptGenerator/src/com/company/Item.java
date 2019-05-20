package com.company;

import java.util.HashMap;
import java.util.Map;

public class Item {

    private static Map<String, Integer> exempt;
    private String name;
    private double cost;
    private double tax;
    private double totalTax;




    public Item(String name, double cost) {

        buildDict();
        this.name = name;
        this.cost = cost;
        this.tax = setTax();


    }

    private double setTax(){
        tax = 0.0;

        //set the necessary tax based on keywords and the name of the item.
        if(!checkBaseTaxForExempt()){
            tax += calculateBaseTax();
        }

        if(checkImportTax()){
            tax += calculateImportTax();
        }



        return tax;
    }

    private double calculateBaseTax(){
        double baseTax = this.cost * (10.0/100.0);
        baseTax = round(baseTax);
        return baseTax;
    }

    private double calculateImportTax(){
        double importTax = this.cost * (5.0/100.0);
        importTax = round(importTax);
        return importTax;
    }

    public double getTax(){

        //return the tax of the item
        return this.tax;
    }

    public String getName (){
        return this.name;
    }

    public double getCost () {
        return this.cost;
    }

    private boolean checkBaseTaxForExempt(){
        //check to see if our item is exempt from the 10 percent tax
        //We only need the last word of the item
        String partOfName = this.name;
        partOfName = partOfName.substring(partOfName.lastIndexOf(" ")+1);

        //if the key is in our dictionary, the tax is 0%
        if(exempt.containsKey(partOfName)){
            return true;
        }

        return false;
    }

    private boolean checkImportTax() {

        //check to see if this item is imported
        /* Instead of checking the first word for "imported" we're going to
        check the string. This way even if "imported isn't listed first,
        we can still find it. Unlike looking for tags for the exemptions,
        there's less of a likelihood that just because the word is found,
        the word is being used as a descriptor for an item [ex. coffee
        flavoring or popcorn jelly beans]. The only exception I can
        think of is "pier one imports" which we can exclude by only using
        "imported." This still isn't completely fool-proof, but it will do
        for now
         */

        if(this.name.contains("imported")) {
            return true;
        }

        return false;

    }

    //this is currently not correct. It needs to always round up
    /*TODO : add in Math.ceiling
    */
    private double round(double price){
         return (Math.round(price * 20) / 20.0);

    }

    private void buildDict(){
        exempt = new HashMap<>();
        exempt.put("Coffee",0);
        exempt.put("Candy",0);
        exempt.put("Popcorn",0);
        exempt.put("Snickers",0);
        exempt.put("Skittles",0);
    }

}
