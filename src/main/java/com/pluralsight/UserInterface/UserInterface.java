package com.pluralsight.UserInterface;
import com.pluralsight.FileManager.FileManager;
import com.pluralsight.FinalOrder.Coupon;
import com.pluralsight.FinalOrder.Order;
import com.pluralsight.SignatureSandwich.BLT;
import com.pluralsight.SignatureSandwich.CustomizeSignature;
import com.pluralsight.SignatureSandwich.Philly;
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

    private static void viewMenu(){

        System.out.println("""
                                                   \n Sandwich Menu\s
                Sizes: 4" ($5.50), 8" ($7.00), 12" ($8.50)\s
                Breads: White, Wheat, Rye, Wrap\s
                Meats: steak, ham, salami, roast beef, chicken, and bacon\s
                Cheeses: american, provolone, cheddar, and swiss\s
                Veggies: lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms\s
                Sauces: mayo, mustard, ketchup, ranch, thousand islands, vinaigrette\s
                              --------------- All non-meat and cheese toppings are FREE! ---------------\s
                Base Meats Price : 4" --> $1.00 (Extra = +$.50)    8" --> $2.00 (Extra = +$1.00)       12" --> $3.00 (Extra = +$2.25)\s
                Base Cheese Price : 4" --> $.75 (Extra = +$.50)    8" --> $1.50 (Extra = +$.60)        12" --> $2.25 (Extra = +$.90)\s
                                                         Drinks Price\s
                                    Small --> $2.00     Medium --> $2.50        Large --> $3.00 \s
                                                           Chips Price\s
                                                       ALL CHIPS ARE $1.50\s
               
                """);
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
                    System.out.println("Order Cancelled!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again \n");
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
                    System.out.println("Please enter a valid size: 4, 8, or 12.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
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
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        String bread = breads[breadChoice - 1];

        boolean toasted = console.getBoolean("Toasted?");
        Sandwich sandwich = new Sandwich(size, bread, toasted);

        // Numbered menu for toppings
        CustomizeSignature.promptForExtras(sandwich);

        return sandwich;
    }

//    private void addToppings(Sandwich sandwich, String[] names, String type, boolean askExtra) {
//        for (String name : names) {
//            if (console.getBoolean("Add " + name + "?")) {
//                boolean extra = askExtra && console.getBoolean("Extra " + name + "?");
//                sandwich.addTopping(new Topping(name, type, extra));
//            }
//        }
//    }
    private void addCustomSandwich(Order order) {
        Sandwich sandwich = buildCustomSandwich();
        order.addItem(sandwich);
        System.out.println("Custom sandwich added to order.\n");
    }

    private void addSignatureSandwich(Order order) {
        Sandwich signature = chooseSignatureSandwich();
        CustomizeSignature.customizeSandwich(signature);
        order.addItem(signature);
        System.out.println("Signature sandwich added to order.\n");
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
                    System.out.println("Invalid choice. Please choose a number between 1 and " + drinkSizes.length + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
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
                    System.out.println("Invalid choice. Please choose a number between 1 and " + drinkFlavors.length + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
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
                    System.out.println("Invalid choice. Please choose a number between 1 and " + chipOptions.length + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void applyCoupon(Order order) {
        String code = console.promptForString("Coupon code: ");
        double percent = console.promptForInt("% discount: ");
        order.setCoupon(new Coupon(code, percent));
        System.out.println("Coupon '" + code + "' applied (" + percent + "% off).\n");
    }

    private int checkout(Order order) {
        System.out.println("\nORDER SUMMARY\n" + order);

        if (console.getBoolean("Confirm order?")) {
            new FileManager().print(order); // Save only to file
            System.out.println("Order confirmed and receipt saved.\n");
            return 0;
        } else {
            System.out.println("Order not confirmed.\n");
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
                System.out.println("Invalid choice. Please select 1 or 2.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }




}
