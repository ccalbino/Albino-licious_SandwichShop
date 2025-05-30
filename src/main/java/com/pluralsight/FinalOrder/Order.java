package com.pluralsight.FinalOrder;

import com.pluralsight.menu.Buyable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Order represents a customer's order, containing Buyable items like sandwiches, drinks, chips, etc.
public class Order {
    private final String customerName;
    private final LocalDateTime orderTime;
    private final List<Buyable> items = new ArrayList<>();
    private Coupon coupon;
    private String note;

    public Order(String customerName, LocalDateTime orderTime) {
        this.customerName = customerName;
        this.orderTime = orderTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    // Adds an item (that implements Buyable) to the order
    public void addItem(Buyable item){
        items.add(item);
    }

    // Sets a coupon to be applied to the order
    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    // Returns the coupon (could be null)
    public Coupon getCoupon() {
        return this.coupon;
    }

    // Returns the list of items in the order
    public List<Buyable> getItems() {
        return items;
    }

    // Calculates the total price of all items before discounts
    public double getTotal() {
        double total = 0;
        for (Buyable item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // Returns the timestamp of the order
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void  setNote(String note){
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    // Returns a formatted summary of the order, including items, coupon (if any), and total cost
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Customer: " + customerName + "\nTime: " + orderTime + "\n\n");

        // Append all item descriptions
        for (Buyable item : items) {
            sb.append(item).append("\n\n");
        }

        // Append coupon details if present
        if (coupon != null) {
            sb.append("Coupon: ")
                    .append(coupon.getCode())
                    .append(" (")
                    .append(String.format("%.0f", coupon.getDiscountPercentage() * 100))
                    .append("% off)\n");
        }

        // Append total cost, with discount if applicable
        if (coupon != null) {
            sb.append(String.format("Total: $%.2f", coupon.applyDiscount(getTotal())));
        } else {
            sb.append(String.format("Total: $%.2f", getTotal()));
        }

        return sb.toString();
    }
}
