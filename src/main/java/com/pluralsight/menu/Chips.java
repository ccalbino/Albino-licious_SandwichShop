package com.pluralsight.menu;

public class Chips implements Buyable {
    private String flavor;

    public Chips(String flavor){
        this.flavor = flavor;

    }

    @Override
    public double getPrice() {
        return 1.5; //all chips are 1.5
    }

    @Override
    public String getDescription() {
        return flavor + " chips";
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
