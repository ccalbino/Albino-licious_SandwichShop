package com.pluralsight.FileManager;

import com.pluralsight.menu.Buyable;
import com.pluralsight.FinalOrder.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// The FileManager class handles saving an order receipt to a text file.
public class FileManager {

    // Writes the order receipt to a file in the "receipts" directory
    public void print(Order order, double tip) {
        // Ensure the receipts folder exists
        File folder = new File("receipts");
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Formatters for file name and order timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"); // For file name
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // For display
        LocalDateTime now = LocalDateTime.now(); // Current system time
        String formattedTime = order.getOrderTime().format(timeFormatter); // Time of order
        String formattedDateTime = now.format(formatter); // Unique filename timestamp
        String fileName = "receipts/" + formattedDateTime + ".txt"; // File path

        // Try-catch block to ensure writer is closed properly
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            System.out.println("Printing receipt to: " + fileName);

            if (order != null) {
                bw.write("--------------------------------------------\n");
                bw.write("Bino-licious SANDWICH SHOP\n");
                bw.write("123 Sesame St\n");
                bw.write("Brooklyn, NY\n");
                bw.write("--------------------------------------------\n");
                bw.write("Cashier: AUTO-BOT\n");

                // List all items in the order
                for (Buyable item : order.getItems()) {
                    bw.write("\n" + item.getDescription() + " - $" + String.format("%.2f", item.getPrice()));
                    bw.write("\n");
                }

                // Calculate totals (with and without coupon)
                double originalTotal = order.getTotal();
                double discountedTotal = originalTotal;
                if (order.getCoupon() != null) {
                    discountedTotal = order.getCoupon().applyDiscount(originalTotal);
                }

                // Show totals
                bw.write(String.format("\n\nOriginal Total: $%.2f\n", originalTotal));
                if (order.getCoupon() != null) {
                    bw.write(String.format("Discounted Total: $%.2f\n", discountedTotal));
                    bw.write("Coupon applied: " + order.getCoupon().getCode() + "\n");
                }

                // Print tip if any
                if (tip > 0) {
                    bw.write(String.format("Tip: $%.2f\n", tip));
                }

                // Print final total (discounted + tip)
                double finalTotal = (order.getCoupon() != null ? discountedTotal : originalTotal) + tip;
                bw.write(String.format("Final Total: $%.2f\n", finalTotal));

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





