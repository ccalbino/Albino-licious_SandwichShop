package com.pluralsight.FinalOrder;

import java.util.HashSet;
import java.util.Set;

public class Coupon {
    private final String code;
    private final double discountPercentage;
    private static final Set<String> usedCoupons = new HashSet<>();
    private static final Set<String> validCoupons = Set.of("10OFF", "5OFF");

    public static boolean isCouponValid(String code) {
        if (code == null) {
            return false;
        }
        String upperCode = code.toUpperCase();
        return validCoupons.contains(upperCode) && !usedCoupons.contains(upperCode);
    }

    public Coupon(String code) {
        if (code == null) throw new IllegalArgumentException("Coupon code cannot be null.");
        this.code = code.toUpperCase();

        if (!validCoupons.contains(this.code)) {
            throw new IllegalArgumentException("Invalid coupon code.");
        }
        if (usedCoupons.contains(this.code)) {
            throw new IllegalStateException("This coupon code has already been used.");
        }
        if (this.code.equals("10OFF")) {
            this.discountPercentage = 0.10;
        } else {
            this.discountPercentage = 0.05;
        }
        usedCoupons.add(this.code);
    }


    public double applyDiscount(double total) {
        return total - (total * discountPercentage);
    }

    public String getCode() {
        return this.code;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }


}
