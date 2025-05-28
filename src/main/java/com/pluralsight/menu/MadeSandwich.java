package com.pluralsight.menu;

public abstract class MadeSandwich extends Sandwich {

    public MadeSandwich(int size, String breadType, boolean toasted) {
        super(size, breadType, toasted);
    }


    @Override
    public String getDescription() {
        return "" + breadType + toppings + toasted;
    }
    @Override
    public String toString() {
        return "Order contains: " + getDescription() + ". Totaling to $ " + getPrice();
    }

}
