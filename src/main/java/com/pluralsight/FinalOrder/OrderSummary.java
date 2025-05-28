package com.pluralsight.FinalOrder;

import com.pluralsight.Buyable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderSummary {
    private String customerName;
    private LocalDateTime orderTime;
    private List<Buyable> items = new ArrayList<>();

    public OrderSummary(String customerName, LocalDateTime orderTime) {
        this.customerName = customerName;
        this.orderTime = orderTime;
    }

    public void addItem(Buyable item){
        items.add(item);
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotal() {
        double total = 0;
        for (Buyable item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        //add logic


    }
}
