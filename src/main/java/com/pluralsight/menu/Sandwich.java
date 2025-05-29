package com.pluralsight.menu;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Buyable {
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
      this.toppings.add(toppings);


    }

//    public List<Topping> getToppings() {
//        return toppings;
//    }

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
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType);
        if (toasted) sb.append(" (toasted)");
        sb.append(" Sandwich\n");

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

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String toString() {
        return getName() + ":\n" + getDescription() + "Price: $" + String.format("%.2f", getPrice());
    }


}
