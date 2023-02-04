package de.htwberlin.prog1.sose20.zombies.model;

/**
 * 
 * @version 1.1
 * @since 01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 - finaler Check + Abschluss der Dokumentation
 * @author Lennard Zündorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die Klasse DemoData ist zum Testen der Klassen & Methoden Zombie, SuperZombie, Vicitim, HumanVicitm und ZombieHunter entworfen 
 */
public class DemoData {

	/**
	 * Methode main
	 * Beschreibung: main-Methode der Klasse DemoData, welche Testweise Daten & Befehle enthält
	 * @param args
	 */
	public static void main (String [] args) {
		SuperZombie Peter = new SuperZombie("Peter");
		Zombie Lina = new Zombie ("Lina");
		Zombie Silvia = new Zombie ("Silvia");
		Zombie Lennard = new Zombie ("Lennard");
		ZombieHunter Tim = new ZombieHunter ("Tim");
		Victim Spinne = new Victim ("Spinne");
		HumanVictim Mike = new HumanVictim ("Mike");
		Peter.addToGroup(Lennard);
		Peter.addToGroup(Silvia);
		Peter.addToGroup(Lina);
		Peter.deleteFromGroup(Lennard);
		Peter.ShowData();
		Peter.attackHunter(Tim);
		Peter.flee();
		Peter.ShowGroupData();
		Peter.commandToAttack(Lina, Spinne);
		Lennard.attack(Spinne);
		Lennard.attack(Mike);
		Lennard.eat();
		System.out.println(Lennard.getHunger());
		Tim.flee();
		Tim.attack(Lennard);
		Tim.alive();
	}
}
