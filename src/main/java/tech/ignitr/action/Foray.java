package tech.ignitr.action;


import tech.ignitr.App;

import java.util.Scanner;

/**
 * @version 2.0
 * @author Prof. Dr. Verena Majuntke and Lennard Zündorf
 * Description: The class Foray is the class that managed the menu and user input of the Foray Action
 */

public class Foray {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Methode main
     * Description:
     */
    public static void run() {
        do {
            showMenu();
            int choice = readUserInput();
            handle(choice);
            System.out.println("\n\n====================");
        } while (true);
    }

    /**
     * Methode: showMenu
     *
     */
    private static void showMenu() {
        String[] menuItems = { "", "(1)\t Discover ","(2)\t Danke","(3)\t End Foray"};
        System.out.println("\nForay through the night.\n");
        for (int i = 1; i < menuItems.length; i++) {
            System.out.println(menuItems[i]);
        }
    }

    /**
     * Methode handle
     * Description: Handle method, which executes different commands depending on the user input (1-3)
     * @param choice is the user input, which is provided by the readUserInput method
     */
    private static void handle(int choice) {
        switch (choice) {
            case 1 -> {//Discover
                DiscoverAction.run();
                System.out.println("\n\n====================");
            }
            case 2 -> {//Dance
                int zufall = (int) (Math.random() * 5) + 1;
                if (zufall == 1) {
                    System.out.println("The entire group dances");
                    App.alpha.dance();
                } else if (zufall == 2 && App.alpha.ZombieGroup[0] != null) {
                    App.alpha.ZombieGroup[0].dance();
                } else if (zufall == 3 && App.alpha.ZombieGroup[1] != null) {
                    App.alpha.ZombieGroup[1].dance();
                } else if (zufall == 4 && App.alpha.ZombieGroup[2] != null) {
                    App.alpha.ZombieGroup[2].dance();
                } else if (zufall == 5 && App.alpha != null) {
                    App.alpha.dance();
                } else {//Case, if no Zombie is available
                    assert App.alpha != null;
                    App.alpha.dance();
                }
            }
            case 3 -> {//End Foray
                System.out.println("\n\n====================");
                App.run();
            }
            default -> {//Default case, if the user input is not valid
                System.out.println("\nInvalid input, please try again.");
                showMenu();
            }
        }
    }

    /**
     * Methode: readUserInput
     * Description: The method reads the user input and returns it
     * @return choiceInternal is the user input
     */
    private static int readUserInput() {
        System.out.print("\nPlease input your decision via a number below:\t");
        return scanner.nextInt();
    }
}


