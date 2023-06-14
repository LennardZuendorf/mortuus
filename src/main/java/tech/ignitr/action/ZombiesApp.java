package tech.ignitr.action;

import java.util.Scanner;

import tech.ignitr.action.Foray;
import tech.ignitr.character.*;


/**
 *
 * @version 1.2
 * @since 15.06.2020 - Bezogen von Moodle
 * 01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 - finaler Check + Abschluss der Dokumentation
 * @author Prof. Dr. Majuntke
 * Angepasst von Lennard Zündorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die ZombiesApp führt den Konsolendialog des ZombieGame aus und ist die Startklasse des Spiels
 */

public class ZombiesApp {
    public static SuperZombie Alpha = new SuperZombie("1");
    private static Scanner scanner = new Scanner(System.in);
    static int ZombieCounter=0;

    /**
     * Methode main
     * Beschreibung: main-Methode der ZombiesApp, legt grundlegenden Ablauf der Klasse fest und führt den Konsolendialog aus
     * @param arg
     */
    public static void main(String[] arg) {
        while (true) {
            showMenu();
            int choice = readUserInput();
            handle(choice);
            System.out.println("\n\n====================");
        }
    }
    /**
     * Methode showMenu
     * Beschreibung: Die Methode gibt die einzellnen Menü-Optionen des Konsolendialoges auf der Konsole aus
     */
    private static void showMenu() {
        String menuItems[] = { "", "(1)\t SuperZombie anlegen", "(2)\t Drei Zombie-Anhaenger generieren",
                "(3)\t Daten des SuperZombies ausgeben", "(4)\t Daten aller Zombie-Anhaenger ausgeben",
                "(5)\t Die Nacht durchstreifen", "(6)\t Beenden" };
        System.out.println("\nZombies Version 1.0\n");
        for (int i = 1; i < menuItems.length; i++) {
            System.out.println(menuItems[i]);
        }
    }

    /**
     * Methode handle
     * Beschreibung: Die Methode führt je nach Eingabe des Nutzer andere Befehle aus (1-6)
     * @param choice übernimmt die Auswahl der Nutzers (choice), die von der Methode readUserInput zur Verfügung gestellt wurde
     */
    private static void handle(int choice) {
        switch (choice) {
            case 1://Erstellung eines neuen SuperZombies
                if (Alpha.getName()=="1") {	//Kontrolle, ob schon ein SuperZombie erstellt wurde
                    Alpha = new SuperZombie("Cannibal");//Erstellung eines neues SuperZombies
                    System.out.println("Es wurde eine neuer SuperZombie mit dem Namen "+Alpha.getName()+" erstellt.");
                } else {//Ausgabe falls bereits ein SuperZombie erstellt wurde
                    System.out.println("\nSie haben bereits einen SuperZombie generiert, Sie können keinen zweiten generieren.");
                }
                break;
            case 2://Erstellen von 3 Zombie-Anhängern
                if (ZombieCounter==0&&Alpha.getName()!="1") {//Kontrolle ob bereits eine ZombieGruppe oder noch kein SuperZombie erstellt wurde
                    System.out.println("\nEs werden drei neue Zombies generiert.");
                    Zombie Scuttler = new Zombie ("Scuttler"); //Erstellung eines neuen Zombies
                    Alpha.addToGroup(Scuttler);//Hinzufügen zur Gruppe des SuperZombies
                    System.out.println("Es wurde eine neuer Zombie mit dem Namen "+Scuttler.getName()+" erstellt.");
                    Zombie Snapper = new Zombie ("Snapper");//Befehle sind analog zur ersten Erstellung eines Zombies, deswegen keine Kommentare
                    Alpha.addToGroup(Snapper);
                    System.out.println("Es wurde eine neuer Zombie mit dem Namen "+Snapper.getName()+" erstellt.");
                    Zombie Clamper = new Zombie ("Clamper");
                    Alpha.addToGroup(Clamper);
                    System.out.println("Es wurde eine neuer Zombie mit dem Namen "+Clamper.getName()+" erstellt.");
                    ZombieCounter++;//Erhöhen des ZombieCounters zur Kontrolle ob bereits einmal Anhänger hinzugefügt wurden
                }else if (ZombieCounter!=0){//Ausgabe falls bereits einmal Anhänger hinzugefügt wurden
                    System.out.println("\nSie können nur einmal 3 Zombies generieren!");
                }else {//Ausgabe falls noch kein SuperZombie erstellt wurde
                    System.out.println("\nSie müssen zunächst einen SuperZombie generieren!");
                }
                break;
            case 3://Ausgabe des Datenblattes des SuperZombies
                if (Alpha.getName()!="1") {//Kontrolle ob bereits ein SuperZombie erstellt wurde
                    Alpha.ShowData();//Ausgabe des Datenblattes
                } else {//Ausgabe wenn noch kein SuperZombie erstellt wurde
                    System.out.println("\nSie haben noch keinen SuperZombie generiert, bitte generieren sie zuerst einen SuperZombie.");
                }
                break;
            case 4://Ausgabe der Datenblätter aller Angänger
                if (ZombieCounter==1) {//Kontrolle ob bereits Zombie-Anhänger erstellt wurden
                    System.out.println("\nDie Daten der ZombieGruppe werden ausgegeben.");
                    Alpha.ShowGroupData();//Ausführung der entsprechenden Methode des SuperZombies
                } else {//Ausgabe falls noch keine Zombie-Anhänger erstellt wurden
                    System.out.println("\nSie haben noch keine Zombies erstellt, bitte erstellen Sie zunächst Zombies.");
                }
                break;
            case 5://Streifzug durch die Nacht
                if (Alpha.getName()!="1") {//Kontrolle ob bereits ein SuperZombie generiert wurde
                    System.out.println("\n\n====================");
                    Foray.main();//Ausführung des Streifzuges
                } else {//Ausgabe falls noch kein SuperZombie generiert wurde
                    System.out.println("\nSie haben noch keinen SuperZombie generiert, bitte generieren sie zuerst einen SuperZombie.");
                }
                break;
            case 6://Beenden der App
                System.out.println("\nDie App wird beendet.");
                System.exit(0);
                break;
            default: {//Abfang-Methode bei falscher Eingabe (z.B. falsche Zahl)
                System.out.println("\nUngueltige Eingabe. Bitte ueberpruefen Sie Ihre Eingabe");
            }
            break;
        }
    }

    /**
     * Methode: readUserInput
     * Beschreibung: ließt die Eingabe des Nutzer auf der Konsole ein und gibt diese aus
     * @return choiceInternal Ausgabe der Eingabe des Nutzer auf der Konsole
     */
    private static int readUserInput() {
        System.out.print("\nBitte geben Sie die Nummer des gewaehlten Menueeintrags ein:\t");
        int choiceInternal = scanner.nextInt();
        return choiceInternal;
    }
}