package com.pluralsight.menu;

public class Topping {
    private String name;
    private String type;
    private boolean isExtra;

    public Topping(String name, String type, boolean isExtra) {
        this.name = name;
        this.type = type;
        this.isExtra = isExtra;
    }

    public String getName() {
        return name;
    }

    public double getPrice(int size) {
        // Free for REGULAR and SAUCE
        if (type.equalsIgnoreCase("REGULAR") || type.equalsIgnoreCase("SAUCE")) {
            return 0.0;
        }

        // MEAT prices
        if (type.equalsIgnoreCase("MEAT")) {
            if (!isExtra && size == 4) {
                return 1.00;
            } else if (!isExtra && size == 8) {
                return 2.00;
            } else if (!isExtra && size == 12) {
                return 3.00;
            } else if (isExtra && size == 4) {
                return 1.00 + 0.50;
            } else if (isExtra && size == 8) {
                return 2.00 + 1.00;
            } else if (isExtra && size == 12) {
                return 3.00 + 1.50;
            }
        }

        // CHEESE prices
        if (type.equalsIgnoreCase("CHEESE")) {
            if (!isExtra && size == 4) {
                return 0.75;
            } else if (!isExtra && size == 8) {
                return 1.50;
            } else if (!isExtra && size == 12) {
                return 2.25;
            } else if (isExtra && size == 4) {
                return 0.75 + 0.30;
            } else if (isExtra && size == 8) {
                return 1.50 + 0.60;
            } else if (isExtra && size == 12) {
                return 2.25 + 0.90;
            }
        }

        return 0.0; // Default if unknown
    }

    public boolean isExtra() {
        return isExtra;
    }






    public String toString() {
        return name + (isExtra ? " (extra)" : "") + " - " + type; //if extra
    }
}
