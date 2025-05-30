package com.pluralsight.SignatureSandwich;

import com.pluralsight.menu.Sandwich;
import com.pluralsight.menu.Topping;

public class Philly extends Sandwich {

    public Philly() {
        super(8, "white", true);

        addTopping(new Topping("Steak", "MEAT", false));
        addTopping(new Topping("American", "CHEESE", false));
        addTopping(new Topping("Peppers", "REGULAR", false));
        addTopping(new Topping("Mayo", "SAUCE", false));
    }

    @Override
    public String getName() {
        return "Philly Cheese Steak";
    }
}

