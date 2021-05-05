package de.htwberlin.prog1.sose20.zombies.model;

/**
 * 
 * @version 1.1
 * @since	01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 - finaler Check + abschlie�ende Dokumentation
 * @author Lennard Z�ndorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die Klasse HumanVictim beschreibt eine Erweiterung der Klasse Victim mit zus�tzlichen Attributen & Operationen
 */
public class HumanVictim extends Victim{
	
	//Attribute
	private String Name;

	/**
	 * Methode HumanVictim
	 * Beschreibung: Konstruktor der Sonderform HumanVictim der Klasse Victim, legt Parameter des menschlichen Opfers fest
	 * @param Name
	 */
	public HumanVictim (String Name) {
		super("Human");//Weitergabe an Konstruktor von Victim
		this.setIntelligence((int)(Math.random()*70)+70);//andere Intelligenz f�r Menschen: zw. 70 und 140
		this.setName(Name);//Besonderheit des Menschen: HumanVictim hat zudem einen Namen
	}

	//Operationen
	
	/**
	 * Methode flee
	 * Beschreibung: Methode l�sst das Opfer einen Fluchtversucht durchf�hren, mit gr��erer Erfolgswahrscheinlichkeit als ein normales Opfer
	 */
	public Boolean flee() {
		if((int)(Math.random() * 5)+ 1<=2) {//40% Chance zur Flucht
			System.out.println("Das Opfer konnte fliehen.");
			return true;//Ausgabe bei gelungener Flucht
		}else {
			System.out.println("Der Fluchtversucht war nicht erfolgreich!");
			return false;//Ausgabe bei misslungener Flucht
		}
	}
	
	/**
	 * Methode takeDamage
	 * Beschreibung: Verbuchung von Schaden, analog zur Operation f�r die Klasse Victim, mit unterschiedlicher Ausgabe, da HumanVictim einen Namen hat
	 */
	public void takeDamage(int Damage) {
		if (Damage<this.getHealthPointsCurrent()) {//nicht t�dlicher Schaden
			this.setHealthPointsCurrent(this.getHealthPointsCurrent()-Damage);
			System.out.println(this.getName()+" hat "+Damage+" Schaden erlitten. "+this.getHealthPointsCurrent()+" Lebenspunkte verbleibend!");//Ausgabe mit Namen des Opfer (unterschiedlich zur Klasse Victim)
		} else if (Damage>=this.getHealthPointsCurrent()) {//t�dlicher Schaden
			System.out.println(this.getName()+" hat "+this.getHealthPointsCurrent()+" Schaden erlitten. "+this.getHealthPointsCurrent()+" Lebenspunkte verbleibend!");//Ausgabe mit Namen des Opfer (unterschiedlich zur Klasse Vi
			this.setHealthPointsCurrent(0);
			this.setAlive(false);//Opfer wird als tod markiert
		}
	}

	//Getter & Setter
	
	/**
	 * Methode getName
	 * Beschreibung: Gibt den Names des Opfers aus
	 * @return gibt den Inhalt des Parameters Name aus
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Methode setName
	 * Beschreibung: legt den Namen des Opfers fest
	 * @param name �bernimmt den gew�nschten Namen des Opfers
	 */
	public void setName(String name) {
		Name = name;
	}
}
