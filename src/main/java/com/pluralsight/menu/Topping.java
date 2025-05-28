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

    public double getPrice(int size){

        if (!isExtra && size == 4) {
            return 1.00;
        } else if (!isExtra && size == 8) {
            return 2.00;
        } else if (!isExtra && size == 12) {
            return 3.00;
        }else if (isExtra && size == 4) {
            return 1.00 + .50;
        } else if (isExtra && size == 8) {
            return 2.00 + 1.00;
        } else if (isExtra && size == 12) {
            return 3.00 + 1.50;
        }

        return 0.0;

    }

    @Override
    public String toString() {
        return name + "ordered these toppings : " + type ; //plus extra toppings. rewrite

    }
}
