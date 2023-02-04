package de.htwberlin.prog1.sose20.zombies.app;

import java.util.Scanner;
import java.util.Set;

import de.htwberlin.prog1.sose20.zombies.model.HumanVictim;
import de.htwberlin.prog1.sose20.zombies.model.SuperZombie;
import de.htwberlin.prog1.sose20.zombies.model.Victim;
import de.htwberlin.prog1.sose20.zombies.model.ZombieHunter;

/**
 * 
 * @version 1.1
 * @since 01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 - finaler Check + Abschluss der Dokumentation
 * @author Lennard Z�ndorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Klasse zur Durchf�hung der Aktion Erkundung und Kampf-Aktionen als Teil des Streifzugs der ZombieApp
 */

public class Action {
	
	//Attribute (f�r die m�glichen Begegnungen wird ebenfalls bereits die Grundlage gelegt)
	private static Scanner scanner = new Scanner(System.in);
	static Victim Victim = new Victim("");
	static HumanVictim HumanVictim = new HumanVictim("");
	static ZombieHunter Hunter = new ZombieHunter("");

	/**
	 * Methode main
	 * Beschreibung: Main-Methode der Action-Klasse, legt grunds�tliches Ablauf bei zwei m�glichen Szenarien fest (Zombie trifft Opfer/Zombie trifft Zombie Hunter
	 * 
	 */
	public static void main() {
		while (true) {
			System.out.println("\n\n====================");
			System.out.println("\nErkundung....\n");
			ZombiesApp.Alpha.setHunger(ZombiesApp.Alpha.getHunger()+1);//Erh�hung des Hungers jede Runde
			if ((int)(Math.random()*5)+1<4) { //Szenario 1: Zombie trifft Opfer (normaler Typ oder Mensch)
				MeetVictim();//Erstellung und Auswahl der Art des Opfers
				AttackVictim();//Angriff auf Opfer
				if (Victim.isAlive()==false) {//Aktionen bei Tod des Opfers
					System.out.println("Das Opfer wurde erlegt und das Gehirn gefressen!\nDie Erkundung wird beendet.");
					Streifzug.main();
				}
			}else {//Szenario 2: Zombie trifft auf einen ZombieHunter
				MeetHunter();//Erstellung ZombieHunter
				AttackHunter();//Angriff auf ZombieHunter
				if (Hunter.alive()==false) {//Aktionen bei Tod des ZombieHunter
					System.out.println("Der ZombieHunter wurde besiegt und sein Gehirn gefressen!\nDie Erkundung wird beendet.");
					Hunter = new ZombieHunter("");//freigeben des Hunters bei Tod des ZombieHunters
					Streifzug.main();
				}else if (ZombiesApp.Alpha.isFinallyDead()==true) {//Aktionen bei Tod des SuperZombies
					System.out.println("Sie haben verloren und kehren zum Hauptmen� zur�ck.");
					ZombiesApp.Alpha = new SuperZombie ("1"); //freigeben des SuperZombies
					ZombiesApp.ZombieCounter=0;//Zur�cksetzen des Counter auf Standart-Wert
					ZombiesApp.main(null);
				}
			}
		}
	}
		
	//Methoden
	
	/**
	 * Methode readUserInput
	 * Beschreibung: lie�t die Eingabe des Nutzer auf der Konsole ein und gibt diese aus
	 * @return choiceInternal Ausgabe der Eingabe des Nutzer auf der Konsole
	 */
	private static int readUserInput() {
		System.out.print("\nBitte, geben Sie die Nummer des gewaehlten Menueeintrags ein:\t");
		int choiceInternal = scanner.nextInt();
		return choiceInternal;
	}
		
	/**
	 * Methode: MeetVictim
	 * Beschreibung: legt zuf�llig die Art des Opfers fest und erstellt diese
	 */
	private static void MeetVictim() {
		int random = (int)(Math.random()*5)+1;//jede Option hat eine 25% Wahrscheinlichkeit das sie eintrifft
		if (random==1) {//menschliches Opfer
			HumanVictim = new HumanVictim ("Ben Little");
			System.out.println("Sie treffen auf einen Menschen!");
		}else if(random==2) {//andere Opfer - 4 verschiedene Typen
			Victim = new Victim("Goblin");
			System.out.println("Sie treffen auf einen "+Victim.getType()+"!");
		}else if(random==3) {
			Victim = new Victim("Elfe");
			System.out.println("Sie treffen auf einen "+Victim.getType()+"!");
		}else if(random==4) {
			Victim = new Victim("Dryade");
			System.out.println("Sie treffen auf einen "+Victim.getType()+"!");
		}else {
			Victim = new Victim("Minotaurus");
			System.out.println("Sie treffen auf einen "+Victim.getType()+"!");
		}
	}
		
	/**
	 * Methode:MeetHunter
	 * Beschreibung: erstellt einen ZombieHunter und l�sst ihn seinen ersten Angriff ausf�hren
	 */
	private static void MeetHunter() {
		ZombieHunter Hunter = new ZombieHunter ("Ballista the Slayer");
		System.out.println("Sie treffen auf einen ZombieHunter. Er greift an!\n");
		Hunter.attack(ZombiesApp.Alpha);
			
	}
	
	/**
	 * Methode AttackVictim
	 * Beschreibung: F�hrt den Konsolendialog Angriff f�r Szenario 1 aus, in dem ausgew�hlt werden kann wer das Opfer angreift, f�hrt ggf. zudem den Dialog "Auswahl des Anh�ngers" zur Auswahl des angreifenden Zombie-Anh�ngers aus
	 */
	private static void AttackVictim() {
		System.out.println("\n=========");
		System.out.println("\nAngriff\n");
		if (ZombiesApp.ZombieCounter==0) {//Optionen wenn keine Anh�nger vorhanden sind, sondern nur der SuperZombie
			String menuItems[] = { "", "(1)\t Angriff mit SuperZombie "};
			System.out.println("Das Opfer hat " +Victim.getHealthPointsCurrent()+" Lebenpunkte.\n\nSie k�nnen lediglich mit ihrem SuperZombie angreifen, da sie keine Anh�nger haben.");
			for (int i = 1; i < menuItems.length; i++) {
				System.out.println(menuItems[i]);
			}
			int choice = readUserInput();
			switch (choice) {
				case 1:				
					if (Victim.getType()=="") {//Angriff auf menschliches Opfer
						ZombiesApp.Alpha.attackHuman(HumanVictim);;
						if (Victim.isAlive()==false) {
							ZombiesApp.Alpha.eat();
						}
					}else {//Angriff auf normales Opfer
						ZombiesApp.Alpha.attack(Victim);
						if (Victim.isAlive()==false) {
							ZombiesApp.Alpha.eat();
						}
					}
					break;
				default: {
					System.out.println("\nUngueltige Eingabe. Bitte ueberpruefen Sie Ihre Eingabe");
					AttackVictim();
				}
			}
		}else {//Optionen wenn der SuperZombie Anh�nger hat
			String menuItems[] = { "", "(1)\t Angriff mit SuperZombie ","(2)\t Angriff mit Anh�nger"};
			System.out.println("\nSie k�nnen ausw�hlen ob mit dem SuperZombie oder einem Anh�nger angegriffen werden soll.\nDas Opfer hat " +Victim.getHealthPointsCurrent()+" Lebenpunkte.\n");
			for (int i = 1; i < menuItems.length; i++) {
				System.out.println(menuItems[i]);
			}
			int choice = readUserInput();
			switch (choice) {
				case 1:	//Aktionen wenn der SuperZombie angreifen soll			
					if (Victim.getType()!="") {//Angriff auf menschliches Opfer
						while (Victim.isAlive()==true) { //SuperZombie greift solange an, wie das Opfer lebt oder nicht geflohen ist
							ZombiesApp.Alpha.attack(Victim);
							if (Victim.flee()==true) {//Fluchversuch des Opfers
								Victim=new Victim("");//Victim wird zur�ckgesetzt - vorgesehener Parameter frei gemacht
								System.out.println("\n\n====================");
								Streifzug.main();//bei gelungener Flucht wird Erkundung automatisch beendet
							}
						}
						if (Victim.isAlive()==false) {//Der SuperZombie frisst das Hirn des toten Opfers
							ZombiesApp.Alpha.eat();
						}
					}else {//Angriff auf normales Opfer
						while (HumanVictim.isAlive()==true) {//SuperZombie greift solange an, wie das Opfer lebt oder nicht geflohen ist
							ZombiesApp.Alpha.attack(HumanVictim);
							if (HumanVictim.flee()==true) {//Fluchversuch des Opfers (mit h�herer Chance, da Mensch)
								HumanVictim=new HumanVictim("");//Victim wird zur�ckgesetzt - vorgesehener Parameter frei gemacht
								System.out.println("\n\n====================");
								Streifzug.main();//bei gelungener Flucht wird Erkundung automatisch beendet
							}
						}
						if (Victim.isAlive()==false) {//Der SuperZombie frisst das Hirn des toten Opfers
							ZombiesApp.Alpha.eat();
						}
					}
					break;
				case 2://Angriff mit Zombieanh�nger - Auswahlm�glichkeit welcher Anh�nger angreifen soll
					System.out.println("\n\n=========");
					System.out.println("\nAngriff mit Anh�ngern\n");
					String Z1="";//Strings f�r besondere Erstellung des Men�s
					String Z2="";
					String Z3="";
					if (ZombiesApp.Alpha.ZombieGroup[0]!=null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
						Z1="(1)\t Angriff mit "+ZombiesApp.Alpha.ZombieGroup[0].getName()+" ("+ZombiesApp.Alpha.ZombieGroup[0].getEnergy()+" Energie)";
					}else {
						Z1="Zombie bereits final Tod";//Option falls Zombie bereits tod - auf diese Weise wird keine NullPointerException ausgegeben
					}
					if (ZombiesApp.Alpha.ZombieGroup[1]!=null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
						Z2="(2)\t Angriff mit "+ZombiesApp.Alpha.ZombieGroup[1].getName()+" ("+ZombiesApp.Alpha.ZombieGroup[1].getEnergy()+" Energie)";
					}else {
						Z2="Zombie bereits final Tod";//Option falls Zombie bereits tod - auf diese Weise wird keine NullPointerException ausgegeben
					}
					if (ZombiesApp.Alpha.ZombieGroup[2]!=null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
						Z3="(3)\t Angriff mit "+ZombiesApp.Alpha.ZombieGroup[2].getName()+" ("+ZombiesApp.Alpha.ZombieGroup[2].getEnergy()+" Energie)";
					}else {
						Z3="Zombie bereits final Tod";//Option falls Zombie bereits tod - auf diese Weise wird keine NullPointerException ausgegeben
					}
					String menuItems2[] = { "",Z1,Z2,Z3,"(4)\t Zur�ck"};//Ausgabe der zun�chst beschriebenen Strings
					System.out.println("Sie k�nnen ausw�hlen mit welchem Anh�nger angegriffen werden soll.\n\n");
					if (Victim.getType()!="") {//Ausgabe der Lebenpunkte, je nachdem um welches Opfer es sich handelt
						System.out.println("Das Opfer hat "+Victim.getHealthPointsCurrent()+" Lebenpunkte.");//normales Opfer
					}else {
						System.out.println("Das Opfer hat "+HumanVictim.getHealthPointsCurrent()+" Lebenpunkte.");//menschliches Opfer
					}
					for (int i = 1; i < menuItems2.length; i++) {
						System.out.println(menuItems2[i]);
					}
					int choice2 = readUserInput();
					switch (choice2) {
						case 1:	// Angriff durch ersten Anh�nger - restliche Befehle sind analog zum Angriff durch einen SuperZombie! Siehe dazu oben.
							if (ZombiesApp.Alpha.ZombieGroup[0]!=null) {
								if (Victim.getType()!="") {
									while (Victim.isAlive()==true) {
										ZombiesApp.Alpha.ZombieGroup[0].attack(Victim);
										if (Victim.flee()==true) {
											Victim=new Victim("");
											System.out.println("\n\n====================");
											Streifzug.main();
										}
									}
									if (Victim.isAlive()==false) {
										ZombiesApp.Alpha.eat();
									}
								}else {
									while (HumanVictim.isAlive()==true) {
										ZombiesApp.Alpha.ZombieGroup[0].attack(HumanVictim);
										if (HumanVictim.flee()==true) {
											HumanVictim=new HumanVictim("");
											System.out.println("\n\n====================");
											Streifzug.main();
										}
									}
									if (Victim.isAlive()==false) {
										ZombiesApp.Alpha.eat();
									}
								}
							}else {//Abbruch des Angriffes falls der Zombie bereits final Tod ist - um NullPointer Exception zu Verhindern
								System.out.println("Bitte w�hlen sie einen Zombie der noch am Leben ist.");
							}
							break;
						case 2:// Angriff durch zweiten Anh�nger - restliche Befehle sind analog zum Angriff durch einen SuperZombie! Siehe dazu oben.
							if (ZombiesApp.Alpha.ZombieGroup[1]!=null) {
								if (Victim.getType()!="") {
									while (Victim.isAlive()==true) {
										ZombiesApp.Alpha.ZombieGroup[1].attack(Victim);
										if (Victim.flee()==true) {
											Victim=new Victim("");
											System.out.println("\n\n====================");
											Streifzug.main();
										}
									}
									if (Victim.isAlive()==false) {
										ZombiesApp.Alpha.eat();
									}
								}else {
									while (HumanVictim.isAlive()==true) {
										ZombiesApp.Alpha.ZombieGroup[1].attack(HumanVictim);
										if (HumanVictim.flee()==true) {
											HumanVictim=new HumanVictim("");
											System.out.println("\n\n====================");
											Streifzug.main();
										}
									}
									if (Victim.isAlive()==false) {
										ZombiesApp.Alpha.eat();
									}
								}
							}else {//Abbruch des Angriffes falls der Zombie bereits final Tod ist - um NullPointer Exception zu Verhindern
								System.out.println("Bitte w�hlen sie einen Zombie der noch am Leben ist.");
							}
							break;
						case 3:// Angriff durch dritten Anh�nger - restliche Befehle sind analog zum Angriff durch einen SuperZombie! Siehe dazu oben.
							if (ZombiesApp.Alpha.ZombieGroup[2]!=null) {
								if (Victim.getType()!="") {
									while (Victim.isAlive()==true) {
										ZombiesApp.Alpha.ZombieGroup[2].attack(Victim);
										if (Victim.flee()==true) {
											Victim=new Victim("");
											System.out.println("\n\n====================");
											Streifzug.main();
										}
									}
									if (Victim.isAlive()==false) {
										ZombiesApp.Alpha.eat();
									}
								}else {
									while (HumanVictim.isAlive()==true) {
										ZombiesApp.Alpha.ZombieGroup[2].attack(HumanVictim);
										if (HumanVictim.flee()==true) {
											HumanVictim=new HumanVictim("");
											System.out.println("\n\n====================");
											Streifzug.main();
										}
									}
									if (Victim.isAlive()==false) {
										ZombiesApp.Alpha.eat();
									}
								}
							}else {//Abbruch des Angriffes falls der Zombie bereits final Tod ist - um NullPointer Exception zu Verhindern
								System.out.println("Bitte w�hlen sie einen Zombie der noch am Leben ist.");
							}
							break;
						default: {//Ausgabe bei ung�ltiger Eingabe (falsche zahl, Buchstabe, etc.
							System.out.println("\nUngueltige Eingabe. Bitte ueberpruefen Sie Ihre Eingabe");
							AttackVictim();
						}
					}
					break;
				default: {//Ausgabe bei ung�ltiger Eingabe (falsche zahl, Buchstabe, etc.
					System.out.println("\nUngueltige Eingabe. Bitte ueberpruefen Sie Ihre Eingabe");
					AttackVictim();
				}
			}
		}
	}
				
	/**
	 * Methode AttackHunter
	 * Beschreibung: F�hrt den Konsolendialog Angriff f�r Szenario 2 aus, in dem ausgew�hlt werden kann wer den ZombieHunter angreift. G�hrt ggf. zudem den Dialog "Auswahl des Anh�ngers" zur Auswahl des angreifenden Zombie-Anh�ngers aus.
	 */
	private static void AttackHunter() {
		int round = 0;//Rundenz�hler um Fluchtversuch erst ab Runden 2 zu erm�glichen
		while (Hunter.alive()==true&&ZombiesApp.Alpha.isFinallyDead()!=true) {//Angriff-Dialog wird so lange durchgef�hrt, ZombieHunter und der SuperZombie leben
			System.out.println("\n=========");
			System.out.println("\nAngriff\n");//Auswahl des Angreifenden oder Flucht (ab Runde 2)
			String menuItems[] = { "","(1)\t Angriff mit SuperZombie ("+ZombiesApp.Alpha.getEnergy()+" Energie)","(2)\t Angriff mit Anh�nger","(3)\t Fluchtversuch (ab Runde 2)"};
			round ++;//Rundenz�hler erh�ht sich pro Runden
			System.out.println("\nSie k�nnen mit dem SuperZombie oder einem Anh�nger angreifen oder versuchen zu fliehen.\nDer ZombieHunter hat " +Hunter.getHealthPointsCurrent()+" Lebenpunkte.\n");
			for (int i = 1; i < menuItems.length; i++) {
				System.out.println(menuItems[i]);
			}
			int choice = readUserInput();
			switch (choice) {
				case 1:	//Angriff mit SuperZombie			
					ZombiesApp.Alpha.attackHunter(Hunter);
					if (Hunter.alive()==false) {//SuperZombie isst das Hirn des ZombieHunter, falls dieser Tod ist
						ZombiesApp.Alpha.eat();
					}
					System.out.println("\nDer ZombieHunter greift ebenfalls an!");
					Hunter.attack(ZombiesApp.Alpha);//ZombieHunter greift automatisch den angreifenden Zombie an
					//kein Test ob SuperZombie tod ist, da in oberer While-Schleife getestet und "Freigabe" der Variable in main-Methode
					break;
				case 2://Angriff mit Anh�nger - Auswahl welcher Anh�nger angreifen soll unter Verhinderung von NullPointerExceptions bei tod der Zombies
					System.out.println("\n\n=========");
					System.out.println("\nAngriff mit Anh�ngern\n");
					String Z1="";//Strings f�r besondere Erstellung des Men�s
					String Z2="";
					String Z3="";
					if (ZombiesApp.Alpha.ZombieGroup[0]!=null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
						Z1="(1)\t Angriff mit "+ZombiesApp.Alpha.ZombieGroup[0].getName()+" ("+ZombiesApp.Alpha.ZombieGroup[0].getEnergy()+" Energie)";
					}else {
						Z1="Zombie bereits final Tod";//Option falls Zombie bereits tod - auf diese Weise wird keine NullPointerException ausgegeben
					}
					if (ZombiesApp.Alpha.ZombieGroup[1]!=null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
						Z2="(2)\t Angriff mit "+ZombiesApp.Alpha.ZombieGroup[1].getName()+" ("+ZombiesApp.Alpha.ZombieGroup[1].getEnergy()+" Energie)";
					}else {
						Z2="Zombie bereits final Tod";//Option falls Zombie bereits tod - auf diese Weise wird keine NullPointerException ausgegeben
					}
					if (ZombiesApp.Alpha.ZombieGroup[2]!=null) {//String wird beschrieben je nachdem ob der Zombie noch existent ist
						Z3="(3)\t Angriff mit "+ZombiesApp.Alpha.ZombieGroup[2].getName()+" ("+ZombiesApp.Alpha.ZombieGroup[2].getEnergy()+" Energie)";
					}else {
						Z3="Zombie bereits final Tod";//Option falls Zombie bereits tod - auf diese Weise wird keine NullPointerException ausgegeben
					}
					String menuItems2[] = { "",Z1,Z2,Z3,"(4)\t Zur�ck"};//Ausgabe der zun�chst beschriebenen Strings
					System.out.println("Sie k�nnen ausw�hlen mit welchem Anh�nger angegriffen werden soll.\n\n");	
					for (int i = 1; i < menuItems2.length; i++) {
						System.out.println(menuItems2[i]);
					}
					int choice2 = readUserInput();
					switch (choice2) {
						case 1:	//Angriff mit ersten Zombie-Anh�nger		
							if (ZombiesApp.Alpha.ZombieGroup[0]!=null) {//Kontrolle ob Zombie �berhaupt lebt
								ZombiesApp.Alpha.ZombieGroup[0].attackHunter(Hunter);//Angriff des Zombies
								if (Hunter.alive()==false) {//Test ob der ZombieHunter tod ist
									ZombiesApp.Alpha.eat();
									break;
								}
								System.out.println("\nDer ZombieHunter greift ebenfalls an!");
								Hunter.attack(ZombiesApp.Alpha.ZombieGroup[0]);//automatischer Angriff des ZombieHunters auf angreifenden Zombie
								if (ZombiesApp.Alpha.ZombieGroup[0].isFinallyDead()==true) {
									ZombiesApp.Alpha.deleteFromGroup(ZombiesApp.Alpha.ZombieGroup[0]);
								}
							}else {//Option wenn der Zombie bereits final Tod ist um NullPointer Exception zu verhindern
								System.out.println("Bitte w�hlen sie einen Zombie der noch am Leben ist.");
							}
							break;	
						case 2://Angriff mit zweitem Zombie-Anh�nger, Befehle sind analog zum Angriff des ersten Zombies
							if (ZombiesApp.Alpha.ZombieGroup[1]!=null) {
								ZombiesApp.Alpha.ZombieGroup[1].attackHunter(Hunter);
								if (Hunter.alive()==false) {//Test ob der ZombieHunter tod ist
									ZombiesApp.Alpha.eat();
									break;
								}
								System.out.println("\nDer ZombieHunter greift ebenfalls an!");
								Hunter.attack(ZombiesApp.Alpha.ZombieGroup[1]);
								if (ZombiesApp.Alpha.ZombieGroup[1].isFinallyDead()==true) {
									ZombiesApp.Alpha.deleteFromGroup(ZombiesApp.Alpha.ZombieGroup[1]);
								}
							}else {
								System.out.println("Bitte w�hlen sie einen Zombie der noch am Leben ist.");
							}	
							break;
						case 3://Angriff mit drittem Zombie-Anh�nger, Befehle sind analog zum Angriff des ersten Zombies
							if (ZombiesApp.Alpha.ZombieGroup[2]!=null) {
								ZombiesApp.Alpha.ZombieGroup[2].attackHunter(Hunter);
								System.out.println("\nDer ZombieHunter greift ebenfalls an!");
								Hunter.attack(ZombiesApp.Alpha.ZombieGroup[2]);
								if (Hunter.alive()==false) {//Test ob der ZombieHunter tod ist
									ZombiesApp.Alpha.eat();
									break;
								}
								if (ZombiesApp.Alpha.ZombieGroup[2].isFinallyDead()==true) {
									ZombiesApp.Alpha.deleteFromGroup(ZombiesApp.Alpha.ZombieGroup[2]);
								}
							}else {
								System.out.println("Bitte w�hlen sie einen Zombie der noch am Leben ist.");
							}
							break;
						default: {//Ausgabe bei ung�ltiger Eingabe (falsche Zahl,etc.)
							System.out.println("\nUngueltige Eingabe. Bitte ueberpruefen Sie Ihre Eingabe");
							AttackHunter();
						}	
					}
					break;
				case 3://Fluchtversuch
					if (round>1) {//Kontrolle ob bereits Runde 2 erreicht wurde
						if (ZombiesApp.Alpha.flee()==true) {//Fluchversuch, bei Erfolg r�ckkehr zum Streifzug-Dialog
							Streifzug.main();
						}
					}else {
						System.out.println("Ein Fluchtversucht ist erst ab Runde 2 m�glich!");
					}
					break;
				default: {//Ausgabe bei ung�ltiger Eingabe (falsche Zahl,etc.)
					System.out.println("\nUngueltige Eingabe. Bitte ueberpruefen Sie Ihre Eingabe");
				}
			}
		}
	}
}
	