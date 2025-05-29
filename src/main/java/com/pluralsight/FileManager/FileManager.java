package com.pluralsight.FileManager;

import com.pluralsight.menu.Buyable;
import com.pluralsight.FinalOrder.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
    public void print(Order order) {
        File folder = new File("receipts");
        if (!folder.exists()) {
            folder.mkdir();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = order.getOrderTime().format(timeFormatter);
        String formattedDateTime = now.format(formatter);
        String fileName = "receipts/" + formattedDateTime + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Printing receipt to: " + fileName);
            if (order != null) {
                // Header
                bw.write("--------------------------------------------\n");
                bw.write("Bino-licious SANDWICH SHOP\n");
                bw.write("123 Sesame St\n");
                bw.write("Brooklyn, NY\n");
                bw.write("--------------------------------------------\n");
                bw.write("Cashier: AUTO-BOT\n");

                // Order items
                for (Buyable item : order.getItems()) {
                    bw.write("\n" + item.getDescription() + " - $" + String.format("%.2f", item.getPrice()));
                    bw.write("\n");
                }

                double originalTotal = order.getTotal();
                double discountedTotal = originalTotal;
                if (order.getCoupon() != null) {
                    discountedTotal = order.getCoupon().applyDiscount(originalTotal);
                }

                // Print original and discounted totals
                bw.write(String.format("\n\nOriginal Total: $%.2f\n", originalTotal));
                if (order.getCoupon() != null) {
                    bw.write(String.format("Discounted Total: $%.2f\n", discountedTotal));
                    bw.write("Coupon applied: " + order.getCoupon().getCode() + "\n");
                }

                if (order.getCustomerName() != null && originalTotal > 0) {
                    bw.write(String.format("\nOrdered by: %s at %s", order.getCustomerName(), formattedTime));
                    bw.write("\nThank you for choosing Bino-licious!\n");
                }

                System.out.println("Receipt successfully saved.");
            }
        } catch (IOException e) {
            System.out.println("Failed to write receipt: " + e.getMessage());
            e.printStackTrace();
        }
    }
}





