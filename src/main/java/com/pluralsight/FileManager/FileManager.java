package com.pluralsight.FileManager;

import com.pluralsight.Buyable;
import com.pluralsight.FinalOrder.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {


        private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        private final LocalDateTime fileTime = LocalDateTime.now();
        private final String formattedDate = fileTime.format(dateTimeFormatter) + ".txt";

        public void print(Order order) {
            if (order == null) return;

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("receipts/" + formattedDate, true));

                // Header
                bw.write("--------------------------------------------\n");
                bw.write("Bino-licous SANDWICH SHOP\n");
                bw.write("--------------------------------------------\n");
                bw.write("Cashier: DELI-AUTO-SYSTEM\n");

                // Order items
                for (Buyable item : order.getItems()) {
                    bw.write("\n" + item.getDescription() + " - $" + item.getPrice());
                }

                // Total and footer
                bw.write(String.format("\n\nTOTAL: $%.2f\n", order.getTotal()));

                if (order.getCustomerName() != null && order.getTotal() > 0) {
                    bw.write(String.format("\nOrdered by: %s", order.getCustomerName()));
                    bw.write("\nThank you for choosing DELI-cious!\n");
                }

                bw.close();
                System.out.println("Receipt saved to: receipts/" + formattedDate);

            } catch (IOException e) {
                System.out.println("Failed to write receipt: " + e.getMessage());
            }
        }
}

