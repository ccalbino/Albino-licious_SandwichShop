package com.pluralsight.menu;

import com.pluralsight.Buyable;

public class Drink implements Buyable {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {

        //rewrite Logic


        return 0;
    }

    @Override
    public String getDescription() { //rewrite
        return "";
    }

    @Override
    public String toString() {
        return getDescription() + getPrice(); //rewrite
    }
}

