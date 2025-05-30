package com.pluralsight.menu;

import java.util.ArrayList;
import java.util.List;

// Sandwich class implements Buyable, meaning it must define methods like getPrice and getDescription
public class Sandwich implements Buyable {
    protected int size;
    protected String breadType;
    protected boolean toasted;
    // List of toppings added to the sandwich
    protected List<Topping> toppings = new ArrayList<>();


    public Sandwich(int size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
    }

    // Method to add a topping to the sandwich
    public void addTopping(Topping toppings) {
        this.toppings.add(toppings);
    }

    // Optional getter if needed in the future
    // public List<Topping> getToppings() {
    //     return toppings;
    // }

    // Helper method to get base price based on sandwich size
    private double getBasePrice(int size) {
        if (size == 4) return 5.5;
        if (size == 8) return 7.0;
        if (size == 12) return 8.5;
        return 0; // Invalid size fallback
    }

    // Calculate the total price: base + toppings (including extras)
    @Override
    public double getPrice() {
        double base = getBasePrice(size);

        // Add price of each topping
        for (Topping t : toppings) {
            base += t.getPrice(size); // Topping price may vary with size
        }

        // Round to 2 decimal places
        return Math.round(base * 100.0) / 100.0;
    }

    // Returns a user-friendly description of the sandwich
    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType);

        if (toasted) sb.append(" (toasted)");
        sb.append(" Sandwich\n");

        // List toppings if any
        if (!toppings.isEmpty()) {
            sb.append("Toppings:\n");
            for (Topping topping : toppings) {
                sb.append("  - ").append(topping.getName());
                if (topping.isExtra()) sb.append(" (extra)");
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    // Placeholder for subclass overrides (e.g., SignatureSandwich can override this)
    @Override
    public String getName() {
        return "";
    }

    // Formatted string representation of the sandwich with name, description, and price
    @Override
    public String toString() {
        return getName() + ":\n" + getDescription() + "Price: $" + String.format("%.2f", getPrice());
    }
}
