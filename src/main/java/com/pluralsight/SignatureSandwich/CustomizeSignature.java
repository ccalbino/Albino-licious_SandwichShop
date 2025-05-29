package com.pluralsight.SignatureSandwich;

import com.pluralsight.console.Console;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.menu.Topping;

import java.util.Iterator;

public class CustomizeSignature {
    private static final Console console = new Console();

    public static void customizeSandwich(Sandwich sandwich) {
        System.out.println("\nCustomizing signature sandwich...");


        promptForExtras(sandwich);
    }


    public static void promptForExtras(Sandwich sandwich) {
        boolean wantsExtras = console.getBoolean("Would you like to add any extras?");
        if (!wantsExtras) {
            System.out.println("No extras added.\n");
            return;
        }

        addToppingsFromList(sandwich, new String[]{"steak", "ham", "salami", "roast beef", "chicken", "bacon"}, "MEAT", true);
        addToppingsFromList(sandwich, new String[]{"american", "provolone", "cheddar", "swiss"}, "CHEESE", true);
        addToppingsFromList(sandwich, new String[]{"lettuce", "peppers", "onions", "tomatoes", "jalapeños", "cucumbers", "pickles", "guacamole", "mushrooms"}, "REGULAR", false);
        addToppingsFromList(sandwich, new String[]{"mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette"}, "SAUCE", false);
    }

    private static void addToppingsFromList(Sandwich sandwich, String[] options, String type, boolean askExtra) {
        while (true) {
            System.out.println("\nSelect a " + type + " topping to add (0 to finish):");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.println("0) Done");

            int choice;
            try {
                choice = console.promptForInt("Choose one: ");
                if (choice == 0) break;

                if (choice < 0 || choice > options.length) {
                    System.out.println("Invalid selection. Please choose a number between 0 and " + options.length + ".");
                    continue;
                }

                String selected = options[choice - 1];
                boolean extra = askExtra && console.getBoolean("Add extra " + selected + "?");
                sandwich.addTopping(new Topping(selected, type, extra));
                System.out.println(selected + " added");

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}