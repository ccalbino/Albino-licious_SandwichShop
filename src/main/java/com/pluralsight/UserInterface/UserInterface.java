package com.pluralsight.UserInterface;
import com.pluralsight.FileManager.FileManager;
import com.pluralsight.FinalOrder.Coupon;
import com.pluralsight.FinalOrder.Order;
import com.pluralsight.SignatureSandwich.BLT;
import com.pluralsight.SignatureSandwich.CustomizeSignature;
import com.pluralsight.SignatureSandwich.Philly;
import com.pluralsight.console.ColorCodes;
import com.pluralsight.console.Console;
import com.pluralsight.menu.Chips;
import com.pluralsight.menu.Drink;
import com.pluralsight.menu.Sandwich;
import com.pluralsight.menu.Topping;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserInterface {
    private final Console console = new Console();
    private FileManager fileManager;


    public void init() {
       fileManager = new FileManager();

        userInterface();


    }

    public void userInterface() {

        String homeScreenPrompt =
                """
                        \n== BINO-licious ===\s
                        1) View Menu\s
                        2) Start New Order\s
                        0) Exit\s
                        (1, 2, 0):\s
                        
                        """;

        int option;

        do {
            option = console.promptForInt(homeScreenPrompt);
            switch (option) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    userOrder();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again \n");
            }
        } while (option != 0);
    }

    private static void viewMenu() {
        System.out.println(ColorCodes.CYAN + "\n=== SANDWICH MENU ===" + ColorCodes.RESET);

        System.out.println(ColorCodes.YELLOW + String.format("%-12s %s", "Sizes:", "4\" ($5.50), 8\" ($7.00), 12\" ($8.50)") + ColorCodes.RESET);
        System.out.println(ColorCodes.YELLOW + String.format("%-12s %s", "Breads:", "White, Wheat, Rye, Wrap") + ColorCodes.RESET);
        System.out.println(ColorCodes.YELLOW + String.format("%-12s %s", "Meats:", "Steak, Ham, Salami, Roast Beef, Chicken, Bacon") + ColorCodes.RESET);
        System.out.println(ColorCodes.YELLOW + String.format("%-12s %s", "Cheeses:", "American, Provolone, Cheddar, Swiss") + ColorCodes.RESET);
        System.out.println(ColorCodes.YELLOW + String.format("%-12s %s", "Veggies:", "Lettuce, Peppers, Onions, Tomatoes, Jalapeños, Cucumbers, Pickles, Guacamole, Mushrooms") + ColorCodes.RESET);
        System.out.println(ColorCodes.YELLOW + String.format("%-12s %s", "Sauces:", "Mayo, Mustard, Ketchup, Ranch, Thousand Islands, Vinaigrette") + ColorCodes.RESET);

        System.out.println(ColorCodes.GREEN + "\n--------------- All non-meat and cheese toppings are FREE! ---------------" + ColorCodes.RESET);

        System.out.println(ColorCodes.PURPLE + "\nBase Meat Prices:" + ColorCodes.RESET);
        System.out.println(String.format("  4\" --> $1.00 (Extra +$0.50)\n  8\" --> $2.00 (Extra +$1.00)\n  12\" --> $3.00 (Extra +$2.25)"));

        System.out.println(ColorCodes.PURPLE + "\nBase Cheese Prices:" + ColorCodes.RESET);
        System.out.println(String.format("  4\" --> $0.75 (Extra +$0.50)\n  8\" --> $1.50 (Extra +$0.60)\n  12\" --> $2.25 (Extra +$0.90)"));

        System.out.println(ColorCodes.BLUE + "\nDrink Prices:" + ColorCodes.RESET);
        System.out.println("  Small --> $2.00\n  Medium --> $2.50\n  Large --> $3.00");

        System.out.println(ColorCodes.RED + "\nChips Price:" + ColorCodes.RESET);
        System.out.println("  All chips are $1.50");
    }

    public void userOrder() {
        String name = console.promptForString("Enter customer name: ");
        Order order = new Order(name, LocalDateTime.now());

        String orderPrompt =
                """
                        \n -- Order Options --
                        1) Custom Sandwich
                        2) Signature Sandwich
                        3) Add Drink
                        4) Add Chips
                        5) Apply Coupon
                        6) Checkout
                        0) Cancel Order
                        \n Choose between options 1 - 6\s
                       \s""";

        int option;

        do {
            option = console.promptForInt(orderPrompt);
            switch (option) {
                case 1:
                    addCustomSandwich(order);
                    break;
                case 2:
                    addSignatureSandwich(order);
                    break;
                case 3:
                    addDrink(order);
                    break;
                case 4:
                    addChips(order);
                    break;
                case 5:
                    applyCoupon(order);
                    break;
                case 6:
                    checkout(order);
                    break;
                case 0:
                    cancelOrder();
                    break;
                default:
                    System.out.println(ColorCodes.RED + "Invalid choice. Please try again \n" + ColorCodes.RESET);
            }
        } while (option != 0);
    }

    private Sandwich buildCustomSandwich() {
        int size = 0;
        while (true) {
            try {
                size = console.promptForInt("Size (4\", 8\", 12\"): ");
                if (size == 4 || size == 8 || size == 12) {
                    break;
                } else {
                    System.out.println(ColorCodes.RED + "Please enter a valid size: 4, 8, or 12." + ColorCodes.RESET);
                }
            } catch (Exception e) {
                System.out.println(ColorCodes.RED + "Invalid input. Please enter a number." + ColorCodes.RESET);
            }
        }

        // Bread selection menu
        String[] breads = {"White", "Wheat", "Rye", "Wrap"};
        int breadChoice = 0;
        while (true) {
            System.out.println("\nChoose your bread:");
            for (int i = 0; i < breads.length; i++) {
                System.out.println((i + 1) + ") " + breads[i]);
            }

            try {
                breadChoice = console.promptForInt("Select bread (1–" + breads.length + "): ");
                if (breadChoice >= 1 && breadChoice <= breads.length) {
                    break;
                } else {
                    System.out.println("Please select a number between 1 and " + breads.length + ".");
                }
            } catch (Exception e) {
                System.out.println(ColorCodes.RED + "Invalid input. Please enter a number." + ColorCodes.RESET);
            }
        }

        String bread = breads[breadChoice - 1];

        boolean toasted = console.getBoolean("Toasted?");
        Sandwich sandwich = new Sandwich(size, bread, toasted);

        // Numbered menu for toppings
        CustomizeSignature.promptForExtras(sandwich);

        return sandwich;
    }

    private void addCustomSandwich(Order order) {
        Sandwich sandwich = buildCustomSandwich();
        order.addItem(sandwich);
        System.out.println(ColorCodes.GREEN + "Custom sandwich added to order.\n" + ColorCodes.RESET);
    }

    private void addSignatureSandwich(Order order) {
        Sandwich signature = chooseSignatureSandwich();
        CustomizeSignature.customizeSandwich(signature);
        order.addItem(signature);
        System.out.println(ColorCodes.GREEN + "Signature sandwich added to order.\n" + ColorCodes.RESET);
    }

    public void addDrink(Order order) {
        String[] drinkSizes = {"Small", "Medium", "Large"};
        String[] drinkFlavors = {"Cola", "Root Beer", "Sprite", "Welch", "Lemonade", "Orange Soda", "Water"};

        String size = "";
        while (true) {
            System.out.println("\nChoose drink size:");
            for (int i = 0; i < drinkSizes.length; i++) {
                System.out.println((i + 1) + ") " + drinkSizes[i]);
            }

            try {
                int sizeChoice = console.promptForInt("Select size (1–" + drinkSizes.length + "): ");
                if (sizeChoice >= 1 && sizeChoice <= drinkSizes.length) {
                    size = drinkSizes[sizeChoice - 1];
                    break;
                } else {
                    System.out.println(ColorCodes.RED + "Invalid choice. Please choose a number between 1 and " + drinkSizes.length + "." + ColorCodes.RESET);
                }
            } catch (Exception e) {
                System.out.println(ColorCodes.RED + "Invalid input. Please enter a number." + ColorCodes.RESET);
            }
        }

        String flavor = "";
        while (true) {
            System.out.println("\nChoose drink flavor:");
            for (int i = 0; i < drinkFlavors.length; i++) {
                System.out.println((i + 1) + ") " + drinkFlavors[i]);
            }

            try {
                int flavorChoice = console.promptForInt("Select flavor (1–" + drinkFlavors.length + "): ");
                if (flavorChoice >= 1 && flavorChoice <= drinkFlavors.length) {
                    flavor = drinkFlavors[flavorChoice - 1];
                    break;
                } else {
                    System.out.println(ColorCodes.RED + "Invalid choice. Please choose a number between 1 and " + drinkFlavors.length + "." + ColorCodes.RESET);
                }
            } catch (Exception e) {
                System.out.println(ColorCodes.RED + "Invalid input. Please enter a number." + ColorCodes.RESET);
            }
        }

        order.addItem(new Drink(size, flavor));
        System.out.println("\nAdded: " + size + " " + flavor + " drink to order.\n");
    }

    public void addChips(Order order) {
        String[] chipOptions = {"BBQ", "Salt & Vinegar", "Sour Cream & Onion", "Original"};

        while (true) {
            System.out.println("\nChoose your chips:");
            for (int i = 0; i < chipOptions.length; i++) {
                System.out.println((i + 1) + ") " + chipOptions[i]);
            }

            try {
                int chipChoice = console.promptForInt("Select chips (1–" + chipOptions.length + "): ");
                if (chipChoice >= 1 && chipChoice <= chipOptions.length) {
                    String chips = chipOptions[chipChoice - 1];
                    order.addItem(new Chips(chips));
                    System.out.println("\nAdded: " + chips + " chips to order.\n");
                    break;
                } else {
                    System.out.println(ColorCodes.RED + "Invalid choice. Please choose a number between 1 and " + chipOptions.length + "." + ColorCodes.RESET);
                }
            } catch (Exception e) {
                System.out.println(ColorCodes.RED + "Invalid input. Please enter a number." + ColorCodes.RESET);
            }
        }
    }

    private void applyCoupon(Order order) {
        while (true) {
            String code = console.promptForString("Enter coupon code (or press Enter to skip): ");
            if (code.isBlank()) {
                System.out.println("No coupon applied.\n");
                break;
            }

            if (Coupon.isCouponValid(code)) {
                try {
                    Coupon coupon = new Coupon(code);
                    order.setCoupon(coupon);
                    System.out.printf("Coupon '%s' applied (%.0f%% off).\n\n", code.toUpperCase(), coupon.getDiscountPercentage() * 100);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else {
                System.out.println(ColorCodes.RED + "Invalid or already-used coupon code. Try again.\n" + ColorCodes.RESET);
            }
        }
    }

    private int checkout(Order order) {
        System.out.println("\nORDER SUMMARY\n" + order);

        if (console.getBoolean("Confirm order?")) {
            new FileManager().print(order); // Save only to file
            System.out.println(ColorCodes.GREEN + "Order confirmed and receipt saved.\n" + ColorCodes.RESET);
            return 0;
        } else {
            System.out.println(ColorCodes.RED + "Order NOT confirmed\n" + ColorCodes.RESET);
            return -1;
        }
    }

    private Sandwich chooseSignatureSandwich() {
        int option = 0;
        while (true) {
            System.out.println("\nSignature Sandwich Options");
            System.out.println("1) BLT");
            System.out.println("2) Philly Cheese Steak");

            try {
                option = console.promptForInt("Select (1 or 2): ");
                if (option == 1) return new BLT();
                if (option == 2) return new Philly();
                System.out.println(ColorCodes.RED + "Invalid choice. Please select 1 or 2." + ColorCodes.RESET);
            } catch (Exception e) {
                System.out.println(ColorCodes.RED + "Invalid input. Please enter a number." + ColorCodes.RESET);
            }
        }
    }

    private void cancelOrder() {
        System.out.println("\nOrder has been canceled.");
    }




}
