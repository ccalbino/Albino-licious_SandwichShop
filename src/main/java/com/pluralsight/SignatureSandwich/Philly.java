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
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType);
        if (toasted) sb.append(" (toasted)");
        sb.append(" sandwich with: ");
        for (Topping topping : toppings) {
            sb.append(topping.getName()).append(", ");
        }
        if (!toppings.isEmpty()) sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @Override
    public String getName() {
        return "Philly Cheese Steak";
    }

    @Override
    public String toString() {
        return getName() + ": " + getDescription() + " - $" + String.format("%.2f", getPrice());
    }
}
