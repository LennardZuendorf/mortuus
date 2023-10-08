package tech.ignitr;

import tech.ignitr.action.Foray;
import tech.ignitr.model.SuperZombie;
import tech.ignitr.model.Zombie;

import java.util.Objects;
import java.util.Scanner;

/**
 * @version 2.0
 * @author Prof. Dr. Verena Majuntke and Lennard Zündorf
 * Description: The class App is the main class of the application and displays the main menu on the console.
 */

public class App {
    public static SuperZombie alpha = new SuperZombie("1");
    private static final Scanner scanner = new Scanner(System.in);
    public static int zombieCounter=0;

    /**
     * run method, which is called from Main.java to start the application and display the menu
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
     * show Menu method, which displays the menu options on the console
     */
    private static void showMenu() {
        String[] menuItems = { "", "(1)\t Create SuperZombie",
                "(2)\t Generate three Zombie-Followers",
                "(3)\t Return Data of SuperZombie",
                "(4)\t Return Data of all Zombie-Followers",
                "(5)\t Foray through the Night",
                "(6)\t Exit" };
        System.out.println("\nZombies Version 2.0\n");
        for (int i = 1; i < menuItems.length; i++) {
            System.out.println(menuItems[i]);
        }
    }

    /**
     * handle method, which executes different commands depending on the user input (1-6)
     * @param choice is the user input, which is provided by the readUserInput method
     */
    private static void handle(int choice) {
        switch (choice) {
            case 1: //Creation of a new SuperZombie
                if (Objects.equals(alpha.getName(), "1")) {
                    alpha = new SuperZombie("Cannibal");
                    System.out.println("A new SuperZombie with the name "+alpha.getName()+" has been created.");
                } else {
                    System.out.println("\n You have already created a SuperZombie, you can't create a second one.");
                }
                break;
            case 2: //Creation of three new Zombie-Followers
                if (zombieCounter==0&& !Objects.equals(alpha.getName(), "1")) {
                    System.out.println("\nThree new Zombie-Followers are being created.");
                    Zombie Scuttler = new Zombie ("Scuttler");
                    alpha.addToGroup(Scuttler);
                    System.out.println("A Zombie Follower with the name "+Scuttler.getName()+" has been created.");
                    Zombie Snapper = new Zombie ("Snapper");
                    alpha.addToGroup(Snapper);
                    System.out.println("A Zombie Follower with the name "+Snapper.getName()+" has been created.");
                    Zombie Clamper = new Zombie ("Clamper");
                    alpha.addToGroup(Clamper);
                    System.out.println("A Zombie Follower with the name  "+Clamper.getName()+" has been created.");
                    zombieCounter++;
                }else if (zombieCounter!=0){
                    System.out.println("\nYou have already created Zombie-Followers, you can't create more.");
                }else {
                    System.out.println("\nYou have not created a SuperZombie yet, please create a SuperZombie first.");
                }
                break;
            case 3: //Return of the data sheet of the SuperZombie
                if (!Objects.equals(alpha.getName(), "1")) {
                    alpha.ShowData();
                } else {
                    System.out.println("\nYou have not created a SuperZombie yet, please create a SuperZombie first.");
                }
                break;
            case 4://Return of the data sheet of all Zombie-Followers
                if (zombieCounter==1) {
                    System.out.println("\nThe data of all Zombie-Followers is being returned.");
                    alpha.ShowGroupData();
                } else {
                    System.out.println("\nYou have not created Zombie-Followers yet, please create Zombie-Followers first.");
                }
                break;
            case 5://Foray through the night
                if (!Objects.equals(alpha.getName(), "1")) {
                    System.out.println("\n\n====================");
                    Foray.run();
                } else {
                    System.out.println("\nYou have not created a SuperZombie yet, please create a SuperZombie first.");
                }
                break;
            case 6://Exit
                System.out.println("\nExiting.Thank you for using the Zombie-App.");
                System.exit(0);
                break;
            default: {//Catch method for wrong input (e.g. wrong number)
                System.out.println("\nInvalid input. Please check your input.");
            }
            break;
        }
    }

    /**
     * Method readUserInput
     * Description: The method reads the user input and returns it
     * @return choiceInternal is the user input
     */
    private static int readUserInput() {
        System.out.print("\nPlease enter the number of the selected menu item\t");
        return scanner.nextInt();
    }

    public int getZombieCounter() {
        return zombieCounter;
    }

    public void setZombieCounter(int zombieCounter) {
        zombieCounter = zombieCounter;
    }

    public SuperZombie getSuperZombie() {
        return alpha;
    }

    public void setSuperZombie(SuperZombie zombie) {
        alpha = zombie;
    }
}