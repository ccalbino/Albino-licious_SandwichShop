package com.pluralsight.menu;

// Drink class that implements the Buyable interface (requires getPrice, getDescription, getName)
public class Drink implements Buyable {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    // Returns the price based on the drink size
    @Override
    public double getPrice() {
        if (size.equalsIgnoreCase("Small")) {
            return 2.00;
        } else if (size.equalsIgnoreCase("Medium")) {
            return 2.50;
        } else if (size.equalsIgnoreCase("Large")) {
            return 3.00;
        }

        // Default price if size is invalid
        return 0.00;
    }

    // Returns a readable description of the drink
    @Override
    public String getDescription() {
        return size + " " + flavor + " drink";
    }

    // Returns the name (flavor) of the drink
    @Override
    public String getName() {
        return flavor;
    }

    // Returns a formatted string with description and price
    @Override
    public String toString() {
        return getDescription() + String.format(" - $%.2f", getPrice());
    }
}

