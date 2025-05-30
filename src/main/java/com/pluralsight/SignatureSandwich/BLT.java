package com.pluralsight.SignatureSandwich;

import com.pluralsight.menu.Sandwich;
import com.pluralsight.menu.Topping;

public class BLT extends Sandwich {

    public BLT() {
        super(8, "white", true);

        addTopping(new Topping("Bacon", "MEAT", false));
        addTopping(new Topping("Cheddar", "CHEESE", false));
        addTopping(new Topping("Lettuce", "REGULAR", false));
        addTopping(new Topping("Tomato", "REGULAR", false));
        addTopping(new Topping("Ranch", "SAUCE", false));
    }

@Override
public String getName() {
    return "BLT";
}

}


