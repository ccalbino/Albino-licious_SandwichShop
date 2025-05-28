package com.pluralsight.menu;

import com.pluralsight.Buyable;

public class Chips implements Buyable {
    private String type;

    public Chips(String type){
        this.type = type;

    }

    @Override
    public double getPrice() {
        return 1.5; //all chips are 1.5
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String toString() {
        return getDescription() + getPrice(); //rewrite
    }
}
