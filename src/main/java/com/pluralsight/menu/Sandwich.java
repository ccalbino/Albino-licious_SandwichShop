package com.pluralsight.menu;

import com.pluralsight.Buyable;

import java.util.ArrayList;
import java.util.List;

public  class Sandwich implements Buyable {
    protected int size;
    protected String breadType;
    protected boolean toasted;
    protected List<Topping> toppings = new ArrayList<>();

    public Sandwich(int size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
    }

    public void addTopping(Topping toppings) {


    }

    public List<Topping> getToppings() {
        return toppings;
    }

    private double getBasePrice(int size) {
        if (size == 4) return 5.5;
        if (size == 8) return 7.0;
        if (size == 12) return 8.5;
        return 0;
    }

    @Override
    public double getPrice() {
        double base = getBasePrice(size);
        for (Topping t : toppings) {
            base += t.getPrice(size);
        }
        return Math.round(base * 100.0) / 100.0;
    }

    @Override
    public String getDescription() {
        return String.format("%d\" %s%s", size, breadType, toasted ? " (toasted)" : "");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getDescription());
        for (Topping t : toppings) {
            sb.append("\n - ").append(t);
        }
        sb.append("\nPrice: $").append(getPrice());
        return sb.toString();
    }


}
