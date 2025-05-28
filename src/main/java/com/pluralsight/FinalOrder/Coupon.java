package com.pluralsight.FinalOrder;

public class Coupon {
        private final String code;
        private final double discountPercentage;

        public Coupon(String code, double discountPercentage) {
            this.code = code;
            this.discountPercentage = discountPercentage;
        }

        public double applyDiscount(double total) {
            return total - (total * discountPercentage / 100);
        }

        public String getCode() {
            return code;
        }

        public double getDiscountPercentage() {
            return discountPercentage;
        }
}

