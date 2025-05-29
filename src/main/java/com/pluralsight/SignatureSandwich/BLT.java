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
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType);
        if (toasted) sb.append(" (toasted)");
        sb.append(" sandwich with: ");
        for (Topping topping : toppings) {
            sb.append(topping.getName()).append(", ");
        }
        //removes space and comma
        if (!toppings.isEmpty()) sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @Override
    public String getName() {
        return "BLT";
    }

    @Override
    public String toString() {
        return getName() + ": " + getDescription() + " - $" + String.format("%.2f", getPrice());
    }
}
