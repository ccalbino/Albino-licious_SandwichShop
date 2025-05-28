package com.pluralsight.SignatureSandwich;

import com.pluralsight.menu.Sandwich;

public class Philly extends Sandwich {

    public Philly() {
        super(8, "white", true);
        //add toppings
    }

    @Override
    public String getDescription() {
        return "";
    }
}
