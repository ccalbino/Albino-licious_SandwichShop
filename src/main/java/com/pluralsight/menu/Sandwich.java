package com.pluralsight.menu;

import com.pluralsight.Buyable;

import java.util.ArrayList;
import java.util.List;

public abstract class Sandwich implements Buyable {
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

    @Override
    public double getPrice() {
        //price logic

    }
    // for (Topping t : toppings) rewrite
}
