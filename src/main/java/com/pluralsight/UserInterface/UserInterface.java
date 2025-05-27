package com.pluralsight.UserInterface;
import com.pluralsight.console.Console;

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
                    //startNewOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again \n");
            }
        } while (option != 0);
    }

    private static void viewMenu(){

         String menu = """
                \nSandwich Menu\s
                Sizes: 4" ($5.50), 8" ($7.00), 12" ($8.50)\s
                Breads: White, Wheat, Rye, Wrap\s
                Meats: steak, ham, salami, roast beef, chicken, and bacon\s
                Cheeses: american, provolone, cheddar, and swiss\s
                Veggies: lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms\s
                Sauces: mayo, mustard, ketchup, ranch, thousand islands, vinaigrette\s
                """;

          console.promptForString(menu);

    }
}
