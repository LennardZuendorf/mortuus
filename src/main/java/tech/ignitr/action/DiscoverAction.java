package tech.ignitr.action;

import tech.ignitr.App;
import tech.ignitr.model.HumanVictim;
import tech.ignitr.model.SuperZombie;
import tech.ignitr.model.Victim;
import tech.ignitr.model.ZombieHunter;

import java.util.Objects;
import java.util.Scanner;

/**
 * @version 2.0
 * @author Prof. Dr. Verena Majuntke and Lennard Zündorf
 * Description:
 */

public class DiscoverAction {

    private static final Scanner scanner = new Scanner(System.in);
    static Victim Victim = new Victim("");
    static HumanVictim HumanVictim = new HumanVictim("");
    static ZombieHunter Hunter = new ZombieHunter("");

    /**
     * Methode main
     * Description:
     */
    public static void run() {
        do {
            System.out.println("\n\n====================");
            System.out.println("\nDiscovering....\n");

            App.alpha.setHunger(App.alpha.getHunger() + 1);
            if ((int) (Math.random() * 5) + 1 < 4) { //Scenario 1: The SuperZombie meets a Victim
                MeetVictim();
                AttackVictim();
                if (!Victim.isAlive()) {
                    System.out.println("The victim is dead and has been eaten!\nThe foray is over.");
                    Foray.run();
                }
            } else {//Scenario 2: The SuperZombie meets a ZombieHunter
                MeetHunter();
                AttackHunter();
                if (!Hunter.alive()) {
                    System.out.println("The ZombieHunter has been killed und their brain has been eaten!\nThe foray ist over.");
                    Hunter = new ZombieHunter("");
                    Foray.run();
                } else if (App.alpha.isFinallyDead()) {
                    System.out.println("The SuperZombie has been killed!\nYour game is over.");
                    App.alpha = new SuperZombie("1");
                    App.zombieCounter = 0;
                    App.run();
                }
            }
        } while (true);
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

    /**
     * Methode: MeetVictim
     * Description: Creates a Victim and prints the type of the Victim
     */
    private static void MeetVictim() {
        int random = (int)(Math.random()*5)+1;
        if (random==1) {
            HumanVictim = new HumanVictim ("Ben Little");
            System.out.println("You meet a human victim!");
        }else if(random==2) {
            Victim = new Victim("Goblin");
            System.out.println("You meet a "+Victim.getType()+"!");
        }else if(random==3) {
            Victim = new Victim("Elf");
            System.out.println("You meet an "+Victim.getType()+"!");
        }else if(random==4) {
            Victim = new Victim("Dryad");
            System.out.println("You meet a "+Victim.getType()+"!");
        }else {
            Victim = new Victim("Minotaur");
            System.out.println("You meet a "+Victim.getType()+"!");
        }
    }

    /**
     * Methode: MeetHunter
     * Description: Creates a ZombieHunter and prints the name of the ZombieHunter
     */
    private static void MeetHunter() {
        ZombieHunter Hunter = new ZombieHunter ("Ballista the Slayer");
        System.out.println("You meet a Zombiehunter, they attack!\n");
        Hunter.attack(App.alpha);


    }

    /**
     * Methode AttackVictim
     * Description: Prints the menu for the attack of a Victim and executes the attack
     */
    private static void AttackVictim() {
        System.out.println("\n=========");
        System.out.println("\nAttack\n");
        if (App.zombieCounter==0) {//Options, if the SuperZombie has no followers
            String[] menuItems = { "", "(1)\t Attack with SuperZombie "};
            System.out.println("The victim has " +Victim.getHealthPointsCurrent()+" Health Points.\n\n You can only attack with the SuperZombie.\n");
            for (int i = 1; i < menuItems.length; i++) {
                System.out.println(menuItems[i]);
            }
            int choice = readUserInput();
            if (choice == 1) {
                if (Objects.equals(Victim.getType(), "")) {
                    App.alpha.attackHuman(HumanVictim);
                } else {
                    App.alpha.attack(Victim);
                }
                if (!Victim.isAlive()) {
                    App.alpha.eat();
                }
            } else {
                System.out.println("\nInvalid input. Please check your input");
                AttackVictim();
            }
        }else {//Options, if the SuperZombie has followers
            String[] menuItems = { "", "(1)\t Attack with SuperZombie ","(2)\t Attack with Follower"};
            System.out.println("\nYou can either attack with the SuperZombie or one of your followers,\nThe victim has " +Victim.getHealthPointsCurrent()+" Health Points.\n");
            for (int i = 1; i < menuItems.length; i++) {
                System.out.println(menuItems[i]);
            }
            int choice = readUserInput();
            switch (choice) {
                case 1 -> {//Attack with SuperZombie
                    if (!Objects.equals(Victim.getType(), "")) {
                        while (Victim.isAlive()) {
                            App.alpha.attack(Victim);
                            if (Victim.flee()) {
                                Victim = new Victim("");
                                System.out.println("\n\n====================");
                                Foray.run();
                            }
                        }
                    } else {
                        while (HumanVictim.isAlive()) {
                            App.alpha.attack(HumanVictim);
                            if (HumanVictim.flee()) {
                                HumanVictim = new HumanVictim("");
                                System.out.println("\n\n====================");
                                Foray.run();
                            }
                        }
                    }
                    if (!Victim.isAlive()) {
                        App.alpha.eat();
                    }
                }
                case 2 -> {//Attack with a follower
                    System.out.println("\n\n=========");
                    System.out.println("\nAttack with a follower.\n");
                    String Z1 = "";
                    String Z2 = "";
                    String Z3 = "";
                    if (App.alpha.ZombieGroup[0] != null) {
                        Z1 = "(1)\t Attack with " + App.alpha.ZombieGroup[0].getName() + " (" + App.alpha.ZombieGroup[0].getEnergy() + " Energy)";
                    } else {
                        Z1 = "Your Zombie is finally dead";
                    }
                    if (App.alpha.ZombieGroup[1] != null) {
                        Z2 = "(2)\t Attack with " + App.alpha.ZombieGroup[1].getName() + " (" + App.alpha.ZombieGroup[1].getEnergy() + " Energy)";
                    } else {
                        Z2 = "Your Zombie is finally dead";
                    }
                    if (App.alpha.ZombieGroup[2] != null) {
                        Z3 = "(3)\t Attack with " + App.alpha.ZombieGroup[2].getName() + " (" + App.alpha.ZombieGroup[2].getEnergy() + " Energy)";
                    } else {
                        Z3 = "Your Zombie is finally dead";
                    }
                    String[] menuItems2 = {"", Z1, Z2, Z3, "(4)\t Back"};
                    System.out.println("You can choose which follower you want to attack with.\n\n");
                    if (!Objects.equals(Victim.getType(), "")) {
                        System.out.println("The victim has " + Victim.getHealthPointsCurrent() + " Health Points.");
                    } else {
                        System.out.println("The victim has " + HumanVictim.getHealthPointsCurrent() + " Health Points.");
                    }
                    for (int i = 1; i < menuItems2.length; i++) {
                        System.out.println(menuItems2[i]);
                    }
                    int choice2 = readUserInput();
                    switch (choice2) {
                        case 1 -> {
                            if (App.alpha.ZombieGroup[0] != null) {
                                if (!Objects.equals(Victim.getType(), "")) {
                                    while (Victim.isAlive()) {
                                        App.alpha.ZombieGroup[0].attack(Victim);
                                        if (Victim.flee()) {
                                            Victim = new Victim("");
                                            System.out.println("\n\n====================");
                                            Foray.run();
                                        }
                                    }
                                } else {
                                    while (HumanVictim.isAlive()) {
                                        App.alpha.ZombieGroup[0].attack(HumanVictim);
                                        if (HumanVictim.flee()) {
                                            HumanVictim = new HumanVictim("");
                                            System.out.println("\n\n====================");
                                            Foray.run();
                                        }
                                    }
                                }
                                if (!Victim.isAlive()) {
                                    App.alpha.eat();
                                }
                            } else {
                                System.out.println("Please choose a Zombie that is still alive.");
                            }
                        }
                        case 2 -> {//Attack with second follower, commands are analogous to the attack with the first follower
                            if (App.alpha.ZombieGroup[1] != null) {
                                if (!Objects.equals(Victim.getType(), "")) {
                                    while (Victim.isAlive()) {
                                        App.alpha.ZombieGroup[1].attack(Victim);
                                        if (Victim.flee()) {
                                            Victim = new Victim("");
                                            System.out.println("\n\n====================");
                                            Foray.run();
                                        }
                                    }
                                } else {
                                    while (HumanVictim.isAlive()) {
                                        App.alpha.ZombieGroup[1].attack(HumanVictim);
                                        if (HumanVictim.flee()) {
                                            HumanVictim = new HumanVictim("");
                                            System.out.println("\n\n====================");
                                            Foray.run();
                                        }
                                    }
                                }
                                if (!Victim.isAlive()) {
                                    App.alpha.eat();
                                }
                            } else {
                                System.out.println("Please choose a Zombie that is still alive.");
                            }
                        }
                        case 3 -> {//Attack with third follower, commands are analogous to the attack with the first follower
                            if (App.alpha.ZombieGroup[2] != null) {
                                if (!Objects.equals(Victim.getType(), "")) {
                                    while (Victim.isAlive()) {
                                        App.alpha.ZombieGroup[2].attack(Victim);
                                        if (Victim.flee()) {
                                            Victim = new Victim("");
                                            System.out.println("\n\n====================");
                                            Foray.run();
                                        }
                                    }
                                } else {
                                    while (HumanVictim.isAlive()) {
                                        App.alpha.ZombieGroup[2].attack(HumanVictim);
                                        if (HumanVictim.flee()) {
                                            HumanVictim = new HumanVictim("");
                                            System.out.println("\n\n====================");
                                            Foray.run();
                                        }
                                    }
                                }
                                if (!Victim.isAlive()) {
                                    App.alpha.eat();
                                }
                            } else {
                                System.out.println("Please choose a Zombie that is still alive.");
                            }
                        }
                        default -> {//Catch method for wrong input (e.g. wrong number)
                            System.out.println("\nInvalid input. Please check your input.");
                            AttackVictim();
                        }
                    }
                }
                default -> {//Catch method for wrong input (e.g. wrong number)
                    System.out.println("\nInvalid input. Please check your input.");
                    AttackVictim();
                }
            }
        }
    }

    /**
     * Methode AttackHunter
     * Description: Prints the menu for the attack of a ZombieHunter and executes the attack
     */
    private static void AttackHunter() {
        int round = 0;
        while (Hunter.alive() && !App.alpha.isFinallyDead()) {
            System.out.println("\n=========");
            System.out.println("\nAttack\n");//Auswahl des Angreifenden oder Flucht (ab Runde 2)
            String[] menuItems = { "","(1)\t Attack with SuperZombie ("+App.alpha.getEnergy()+" Enery)","(2)\t Attack with Follower","(3)\t Escape Attempt (starting round2)"};
            round ++;
            System.out.println("\n You can attack with the SuperZombie or one of your followers, the ZombieHunter has" +Hunter.getHealthPointsCurrent()+" HealthPoints.\n");
            for (int i = 1; i < menuItems.length; i++) {
                System.out.println(menuItems[i]);
            }
            int choice = readUserInput();
            switch (choice) {
                case 1 -> {//Attack with SuperZombie
                    App.alpha.attackHunter(Hunter);
                    if (!Hunter.alive()) {
                        App.alpha.eat();
                    }
                    System.out.println("\nThe ZombieHunter attacks as well!");
                    Hunter.attack(App.alpha);
                }
                case 2 -> {//Attack with a follower
                    System.out.println("\n\n=========");
                    System.out.println("\nAttack with a follower.\n");
                    String Z1 = "";
                    String Z2 = "";
                    String Z3 = "";
                    if (App.alpha.ZombieGroup[0] != null) {
                        Z1 = "(1)\t Attack with " + App.alpha.ZombieGroup[0].getName() + " (" + App.alpha.ZombieGroup[0].getEnergy() + " Energy)";
                    } else {
                        Z1 = "Zombie is already finally dead.";
                    }
                    if (App.alpha.ZombieGroup[1] != null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
                        Z2 = "(2)\t Attack with " + App.alpha.ZombieGroup[1].getName() + " (" + App.alpha.ZombieGroup[1].getEnergy() + " Energy)";
                    } else {
                        Z2 = "Zombie is already finally dead.";
                    }
                    if (App.alpha.ZombieGroup[2] != null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
                        Z3 = "(3)\t Attack with " + App.alpha.ZombieGroup[2].getName() + " (" + App.alpha.ZombieGroup[2].getEnergy() + " Energie)";
                    } else {
                        Z3 = "Zombie is already finally dead.";
                    }
                    String[] menuItems2 = {"", Z1, Z2, Z3, "(4)\t Back"};
                    System.out.println("You can choose what should happen next.\n\n");
                    for (int i = 1; i < menuItems2.length; i++) {
                        System.out.println(menuItems2[i]);
                    }
                    int choice2 = readUserInput();
                    switch (choice2) {
                        case 1 -> {//Attack with first follower
                            if (App.alpha.ZombieGroup[0] != null) {
                                App.alpha.ZombieGroup[0].attackHunter(Hunter);
                                if (!Hunter.alive()) {
                                    App.alpha.eat();
                                    break;
                                }
                                System.out.println("\nThe ZombieHunter attacks as well!");
                                Hunter.attack(App.alpha.ZombieGroup[0]);
                                if (App.alpha.ZombieGroup[0].isFinallyDead()) {
                                    App.alpha.deleteFromGroup(App.alpha.ZombieGroup[0]);
                                }
                            } else {
                                System.out.println("Please choose a Zombie that is still alive.");
                            }
                        }
                        case 2 -> {
                            if (App.alpha.ZombieGroup[1] != null) {
                                App.alpha.ZombieGroup[1].attackHunter(Hunter);
                                if (!Hunter.alive()) {
                                    App.alpha.eat();
                                    break;
                                }
                                System.out.println("\nThe ZombieHunter attacks as well!");
                                Hunter.attack(App.alpha.ZombieGroup[1]);
                                if (App.alpha.ZombieGroup[1].isFinallyDead()) {
                                    App.alpha.deleteFromGroup(App.alpha.ZombieGroup[1]);
                                }
                            } else {
                                System.out.println("Please choose a Zombie that is still alive.");
                            }
                        }
                        case 3 -> {
                            if (App.alpha.ZombieGroup[2] != null) {
                                App.alpha.ZombieGroup[2].attackHunter(Hunter);
                                System.out.println("\nThe ZombieHunter attacks as well!");
                                Hunter.attack(App.alpha.ZombieGroup[2]);
                                if (!Hunter.alive()) {
                                    App.alpha.eat();
                                    break;
                                }
                                if (App.alpha.ZombieGroup[2].isFinallyDead()) {
                                    App.alpha.deleteFromGroup(App.alpha.ZombieGroup[2]);
                                }
                            } else {
                                System.out.println("Bitte waehlen Sie einen Zombie, der noch lebt.");
                            }
                        }
                        default -> {//Catch method for wrong input (e.g. wrong number)
                            System.out.println("\nInvalid input. Please check your input.");
                            AttackHunter();
                        }
                    }
                }
                case 3 -> {//Escape attempt
                    if (round > 1) {
                        if (App.alpha.flee()) {
                            Foray.run();
                        }
                    } else {
                        System.out.println("An escape attempt is only possible from round 2 onwards.");
                    }
                }
                default -> {//Catch method for wrong input (e.g. wrong number)
                    System.out.println("\nInvalid input. Please check your input.");
                }
            }
        }
    }
}

