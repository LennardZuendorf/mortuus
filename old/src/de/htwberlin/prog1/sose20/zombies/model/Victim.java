package de.htwberlin.prog1.sose20.zombies.model;

import java.util.UUID;

/**
 * 
 * @version 1.1
 * @since	01.07.2020 - Abschluss der Entwicklung
 *05.07.2020 - finaler Check + abschlie�ende Dokumentation
 * @author Lennard Z�ndorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die Klasse Victim beschreibt ein Opfer als Bestandteil des ZombieSpieles, genau seine Attribute und Operationen
 */
public class Victim {

	//Atribute
	private String id;
	private String type;
	private int Intelligence;
	private int healthPointsCurrent;
	private boolean alive;
	
	//Konstruktor
		
	/**
	 * Methode Victim
	 * Beschreibung: Konstruktor f�r Klasse Victim, legt Werte f�r Typ, Intelligenz, Lebenspunkte fest
	 * @param type Eingabe des gew�nschten Typs
	 */
	public Victim (String type) {
		this.setId(UUID.randomUUID().toString()); //generierte ID
		this.type=type; 
		this.setIntelligence((int)(Math.random()*80)+ 1);//Zuflliger Wert zwischen 1 und 80
		this.setHealthPointsCurrent(10); //Lebenspunkte sind immer 10
		this.setAlive(true);
	}
	
	//Operationen
	
	/**
	 * Methode defend
	 * Beschreibung: 
	 * @param Damage
	 */
	public void defend(int Damage) {
		if((int)(Math.random()*4)+1==1) {//25% Wahrscheinlichkeit das der Angriff abgewehrt wird
			System.out.println("Das Opfer konnte den Angriff abwehren.");//falls Abwehr erfolgreich wird kein Schaden verbucht
		} else {
			this.takeDamage(Damage);//Ausf�hrung der TakeDamage-Operation wenn Angriff nicht abgewehrt werden kann
		}
	}
	
	/**
	 * Methode takDamage
	 * Beschreibung:Verbucht Schaden den das Opfer zugef�gt bekommt, bei t�dlichem Schaden wird das Opfer als tod markiert
	 * @param Damage Schadenswert, der von der Funktion defend �bergeben wird
	 */
	public void takeDamage(int Damage) {
		if (Damage<this.healthPointsCurrent) {//Abzug von Lebenspunkten falls Schaden nicht t�dlich
			this.healthPointsCurrent=this.healthPointsCurrent-Damage;
			System.out.println(this.getType()+" hat "+Damage+" Schaden erlitten. "+this.getHealthPointsCurrent()+" Lebenspunkte verbleibend!");
		} else if (Damage>=this.healthPointsCurrent) {//Abzug von Lebenspunkten wenn Schaden t�dlich (da nicht mehr Schaden als Lebenspunkte zugef�gt werden kann)
			System.out.println(this.getType()+" hat "+this.healthPointsCurrent+" Schaden erlitten. "+this.getHealthPointsCurrent()+" Lebenspunkte verbleibend!");//Als Schaden werden in diesem Fall maximal die �brigen Lebenspunkte angegeben
			this.healthPointsCurrent=0;//genauer Schaden wird au�er acht gelassen, da Opfer sowieso tod
			this.setAlive(false);//Opfer wird als tod markiert
		}
	}
	
	/**
	 * Methode flee
	 * Beschreibung: L�sst das Opfer einen Fluchversuch unternehmen
	 * @return gibt das Ergebnis des Fluchversuches aus, also ob dieser Erfolgreich war
	 */
	public Boolean flee() {
		if((int)(Math.random()*5)+1==1) {//20% Chance zur Flucht
			System.out.println("Das Opfer konnte fliehen!");
			return true;//Ausgabe bei erfolgreichem Fluchtversuch
		}else {
			System.out.println("Der Fluchtversucht war nicht erfolgreich!");
			return false;//Ausgabe bei misslugendem Fluchtversuch
		}
	}

	//Getter & Setter
	
	/**
	 * Methode getID
	 * Beschreibung: Getter-Methode f�r den Parameter ID
	 * @return gibt den Wert des Parameters ID aus
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Methode setId
	 * Beschreibung: legt den Inhalt des Parameters ID fest
	 * @param �bernimmt den gew�nschten Wert des Parameters ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Methode getType
	 * Beschreibung: Gibt des Typ des Opfers aus
	 * @return gibt den Inhalt des Parameters Typ aus
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Methode getIntelligence
	 * Beschreibung: Gibt den Intelligence-Wer des Opfer aus
	 * @return gibt den Inhalt des Parameters Intelligence aus
	 */
	public int getIntelligence() {
		return Intelligence;
	}
	
	/**
	 * Methode setIntelligence
	 * Beschreibung: legt den Intelligence-Wer des Opfers fest
	 * @param Intelligence �bernimmt den gew�nschten Intelligence-Wert f�r das Opfer
	 */
	public void setIntelligence(int Intelligence) {
		this.Intelligence=Intelligence;
	}
	
	/**
	 * Methode getHealthPointsCurrent
	 * Beschreibung:gibt die aktuellen Lebenspunkte des Opfers aus
	 * @return gibt den Inhalt des Parameters healthPointsCurrent aus (Lebenspunkte)
	 */
	public int getHealthPointsCurrent() {
		return healthPointsCurrent;
	}
	
	/**
	 * Methode setHealthPointsCurrent
	 * Beschreibung: legt die aktuellen Lebenspunkte des Opfers fest
	 * @param HealthPoints �bernimmt den Wert f�r die aktuellen Lebenspunkte des Opfers
	 */
	public void setHealthPointsCurrent(int HealthPoints) {
		this.healthPointsCurrent=HealthPoints;
	}

	/**
	 * Methode isAlive
	 * Beschreibung: Gibt aus ob das Opfer am Leben ist
	 * @return gibt true aus wenn das Opfer lebt, false wenn das Opfer nicht mehr lebt
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Methode setAlive
	 * Beschreibung: legt fest ob das Opferam Leben ist
	 * @param alive �bernimmt ob das Opfer noch lebt (true=ja, false=nein)
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
