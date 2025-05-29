package com.pluralsight.menu;

public class Drink implements Buyable {
    private String size;
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        if (size.equalsIgnoreCase("Small")) {
            return 2.00;
        } else if (size.equalsIgnoreCase("Medium")) {
            return 2.50;
        } else if (size.equalsIgnoreCase("Large")) {
            return 3.00;
        }

        return 0.00;
    }
    @Override
    public String getDescription() {
        return size + " " + flavor + " drink";
    }

    @Override
    public String getName() {
        return flavor;
    }

    @Override
    public String toString() {
        return getDescription() + String.format(" - $%.2f", getPrice());
    }
}

