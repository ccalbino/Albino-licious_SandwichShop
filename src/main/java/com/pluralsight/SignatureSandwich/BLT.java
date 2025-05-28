package com.pluralsight.SignatureSandwich;

import com.pluralsight.menu.Sandwich;

public class BLT extends Sandwich {

    public BLT() {
        super(8, "white", true);
        //add toppings
    }

    @Override
    public double getPrice() {
        return 0;
        //ask about this. I want to use a toString below
    }

    @Override
    public String toString() {
        return getDescription() + " - $" + getPrice();
    }

    @Override
    public String getDescription() {
        return "";
    }
}
