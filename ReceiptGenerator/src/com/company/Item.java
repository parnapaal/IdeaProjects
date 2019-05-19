package com.company;

public class Item {

    private String name;
    private double cost;
    private double tax;

    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;

    }

    public void setTax(String[] dict){

        //set the necessary tax based on keywords and the name of the item.
    }

    public double getTax(){

        //return the tax of the item
        return this.tax;
    }

    private boolean checkBaseTax(String[] dict){
        //check to see if our item is exempt from the 10 percent tax

        return false;
    }

    private boolean checkImportTax() {
        //check to see if this item is imported

        return false;
    }

    private void round(){
         Math.round(price * 20.0) / 20.0;

    }
}
