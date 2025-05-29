package com.pluralsight.FinalOrder;

public class Coupon2 {
        private final String code;
        private final double discountPercentage;

        public static boolean isCouponValid(String code){
            return(code.equalsIgnoreCase("10OFF") || code.equalsIgnoreCase("5OFF"));
        }

        public Coupon2(String code) {
            if(code.equalsIgnoreCase("10OFF")){
                this.discountPercentage = 0.10;
            }
            else if (code.equalsIgnoreCase("5OFF")){
                this.discountPercentage = 0.05;
            }
        }

        public double applyDiscount(double total) {
            return total - (total * discountPercentage / 100);
        }

        public String getCode() {
            return this.code;
        }

        public double getDiscountPercentage() {
            return discountPercentage;
        }
}

