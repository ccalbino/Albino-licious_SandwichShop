package com.pluralsight.FinalOrder;

import com.pluralsight.menu.Buyable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String customerName;
    private final LocalDateTime orderTime;
    private final List<Buyable> items = new ArrayList<>();
    private Coupon coupon;

    public Order(String customerName, LocalDateTime orderTime) {
        this.customerName = customerName;
        this.orderTime = orderTime;
    }
    public String getCustomerName() {
        return customerName;
    }

//    public LocalDateTime getOrderTime() {
//        return orderTime;
//    }

    public void addItem(Buyable item){
        items.add(item);
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public List<Buyable> getItems() {
        return items;
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
        StringBuilder sb = new StringBuilder("Customer: " + customerName + "\nTime: " + orderTime + "\n\n");
        for (Buyable item : items) {
            sb.append(item).append("\n\n");
        }
        if (coupon != null) {
            sb.append("Coupon: ").append(coupon.getCode())
                    .append(" (").append(String.format("%.0f", coupon.getDiscountPercentage())).append("% off)\n");
        }
        if (coupon != null) {
            sb.append(String.format("Total: $%.2f", coupon.applyDiscount(getTotal())));
            return sb.toString();
        }else {
            sb.append(String.format("Total: $%.2f",getTotal()));
            return sb.toString();
        }

    }
}
