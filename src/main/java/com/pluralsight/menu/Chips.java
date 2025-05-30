package com.pluralsight.menu;

// Chips class implements the Buyable interface, so it must define getPrice, getDescription, and getName
public class Chips implements Buyable {
    private String flavor;

    public Chips(String flavor) {
        this.flavor = flavor;
    }

    // All chips have a fixed price of $1.50
    @Override
    public double getPrice() {
        return 1.5;
    }

    // Returns a description like "BBQ chips"
    @Override
    public String getDescription() {
        return flavor + " chips";
    }

    // Returns the name of the chips (used for display or identification)
    @Override
    public String getName() {
        return flavor;
    }

    // Returns a formatted string with the description and price, e.g., "BBQ chips - $1.50"
    @Override
    public String toString() {
        return getDescription() + String.format(" - $%.2f", getPrice());
    }
}
