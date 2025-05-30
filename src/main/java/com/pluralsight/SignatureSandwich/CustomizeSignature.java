package com.pluralsight.SignatureSandwich;
import com.pluralsight.console.Console;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.menu.Topping;


public class CustomizeSignature {
    private static final Console console = new Console();

    // Entry method for customizing a signature sandwich
    public static void customizeSandwich(Sandwich sandwich) {
        System.out.println("\nCustomizing signature sandwich...");
        promptForExtras(sandwich); // Proceed to prompt for additional toppings
    }

    // Method to ask the user if they want to add extras to the sandwich
    public static void promptForExtras(Sandwich sandwich) {
        boolean wantsExtras = console.getBoolean("Would you like to add any extras?");
        if (!wantsExtras) {
            System.out.println("No extras added.\n");
            return;
        }

        // Prompt the user to add different types of toppings
        addToppingsFromList(sandwich, new String[]{"steak", "ham", "salami", "roast beef", "chicken", "bacon"}, "MEAT", true);
        addToppingsFromList(sandwich, new String[]{"american", "provolone", "cheddar", "swiss"}, "CHEESE", true);
        addToppingsFromList(sandwich, new String[]{"lettuce", "peppers", "onions", "tomatoes", "jalapeños", "cucumbers", "pickles", "guacamole", "mushrooms"}, "REGULAR", false);
        addToppingsFromList(sandwich, new String[]{"mayo", "mustard", "ketchup", "ranch", "thousand islands", "vinaigrette"}, "SAUCE", false);
    }

    // Reusable method to handle topping selection from a list
    private static void addToppingsFromList(Sandwich sandwich, String[] options, String type, boolean askExtra) {
        while (true) {
            // Display topping options to the user
            System.out.println("\nSelect a " + type + " topping to add (0 to finish):");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.println("0) Done"); // Option to stop adding from this category

            int choice;
            try {
                choice = console.promptForInt("Choose one: ");
                if (choice == 0) break; // Exit loop if user is done

                if (choice < 0 || choice > options.length) {
                    System.out.println("Invalid selection. Please choose a number between 0 and " + options.length + ".");
                    continue;
                }

                // Get selected topping
                String selected = options[choice - 1];

                // Ask if extra is desired (only for meat and cheese)
                boolean extra = askExtra && console.getBoolean("Add extra " + selected + "?");

                // Add topping to sandwich
                sandwich.addTopping(new Topping(selected, type, extra));
                System.out.println(selected + " added");

            } catch (Exception e) {
                // Handles non-integer input
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}