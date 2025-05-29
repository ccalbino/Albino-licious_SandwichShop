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
        //defines fileName and creates folder if it doesn't exist
        File folder = new File("receipts");
        if (!folder.exists()) {
            folder.mkdir();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        LocalDateTime now = LocalDateTime.now();
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
                }

                // Total and footer
                bw.write(String.format("\n\nTOTAL: $%.2f\n", order.getTotal()));

                if (order.getCustomerName() != null && order.getTotal() > 0) {
                    bw.write(String.format("\nOrdered by: %s", order.getCustomerName()));
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





