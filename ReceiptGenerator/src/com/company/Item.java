package com.company;

import java.util.HashMap;
import java.util.Map;

/*
This class contains our shopping items. Within this class we keep
item name, its cost, and its total tax
 */
public class Item {

    private static Map<String, Integer> exempt;
    private String name;
    private double cost;
    private double tax;
    private double totalTax;


//instantiate as shopping item
    public Item(String name, double cost) {

        buildDict();
        this.name = name;
        this.cost = cost;
        this.tax = setTax();


    }

    /*set tax based off of a boolean determining if it is exempt
    (so its candy, popcorn, or coffee), or if it's imported
     */
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

    //if our item isn't exempt, calculate a 10% tax
    private double calculateBaseTax(){
        double baseTax = this.cost * (10.0/100.0);
        baseTax = round(baseTax);
        return baseTax;
    }

    //if our item is imported, calculate a 5% tax
    private double calculateImportTax(){
        double importTax = this.cost * (5.0/100.0);
        importTax = round(importTax);
        return importTax;
    }

    //public method to get the tax amount
    public double getTax(){

        //return the tax of the item
        return this.tax;
    }

    //public method to get our string name
    public String getName (){
        return this.name;
    }

    //public method to get the initial cost
    public double getCost () {
        return this.cost;
    }

    /*checks to see if our item is exempt from taxes. Right now this is being
    done with a dictionary filled with a few keywords ... gets the job done
    but its not air tight
    TODO: implement the wiki API to allow items to be searchable
     */
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
    private boolean checkImportTax() {

        //check to see if this item is imported


        if(this.name.toLowerCase().contains("imported")) {
            return true;
        }

        return false;

    }

    private static double round(double price){

        int doubleToInt = (int)Math.round(price* 100);
        doubleToInt = (doubleToInt + 4) / 5 * 5;

        double intToDouble = doubleToInt / 100.00;
        return intToDouble;

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
