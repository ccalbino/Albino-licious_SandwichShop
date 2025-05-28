package com.pluralsight.UserInterface;
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
   // private FileManager fileManager;


    public void init() {
       // fileManager = new FileManager();

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
                \nSandwich Menu\s
                Sizes: 4" ($5.50), 8" ($7.00), 12" ($8.50)\s
                Breads: White, Wheat, Rye, Wrap\s
                Meats: steak, ham, salami, roast beef, chicken, and bacon\s
                Cheeses: american, provolone, cheddar, and swiss\s
                Veggies: lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms\s
                Sauces: mayo, mustard, ketchup, ranch, thousand islands, vinaigrette\s
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
                    order.addItem(buildCustomSandwich());
                    break;
                case 2:
                    Sandwich signature = chooseSignatureSandwich();
                    CustomizeSignature.customizeSandwich(signature);
                    order.addItem(signature);
                    break;
                case 3:
                    order.addItem(new Drink(
                            console.promptForString("Drink size: "),
                            console.promptForString("Flavor: ")));
                    break;
                case 4:
                    order.addItem(new Chips(
                            console.promptForString("Type of Chip: ")));
                    break;
//                case 5:
//                    String cuponCode = console.promptForString("Coupon code: ");
//                    double percent = console.promptForInt("% discount: " );
//                    order.setCoupon(new Coupon(code, percent));
//                    break;
//                case 6:
//                    System.out.println("\nORDER SUMMARY\n" + order);
//                    if (console.getBoolean("Confirm order?")){
//                        ReceiptPrinter.print(order);
//                        history.addOrder(order);
//                        option = 0; //exits loop after successful checkout
//                    }
//                    break;
                case 0:
                    System.out.println("Order Cancelled!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again \n");
            }
        } while (option != 0);
    }

    private Sandwich buildCustomSandwich() {
        int size = console.promptForInt("Size (4\", 8\", 12\"): ");

        // Bread selection menu
        String[] breads = {"White", "Wheat", "Rye", "Wrap"};
        System.out.println("\nChoose your bread:");
        for (int i = 0; i < breads.length; i++) {
            System.out.println((i + 1) + ") " + breads[i]);
        }
        int breadChoice = console.promptForInt("Select bread (1–" + breads.length + ")\n") ;
        String bread = breads[breadChoice - 1];

        boolean toasted = console.getBoolean("Toasted?");
        Sandwich sandwich = new Sandwich(size, bread, toasted);

        // Numbered menu for toppings
        CustomizeSignature.promptForExtras(sandwich);

        return sandwich;
    }

    private void addToppings(Sandwich sandwich, String[] names, String type, boolean askExtra) {
        for (String name : names) {
            if (console.getBoolean("Add " + name + "?")) {
                boolean extra = askExtra && console.getBoolean("Extra " + name + "?");
                sandwich.addTopping(new Topping(name, type, extra));
            }
        }
    }

    private Sandwich chooseSignatureSandwich() {
        System.out.println("\n Signature Sandwich Options\n 1) BLT\n 2) Philly Cheese Steak");
        int option = console.promptForInt("Select:  ");

        if (option == 1) return new BLT();
        return new Philly(); // fallback for option 2
    }

}
