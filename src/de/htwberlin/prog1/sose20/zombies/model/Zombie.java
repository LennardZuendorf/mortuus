package de.htwberlin.prog1.sose20.zombies.model;

import java.util.UUID;

/**
 * 
 * @version 1.1
 * @since 01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 finaler Check + Abschluss der Dokumentation
 * @author Lennard Z�ndorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die Klasse Zombie beschreibt einen Zombie als Bestandteil des ZombieSpieles, genauer seine Attribute und Operationen
 */
public class Zombie {
	
	//Attribute
	private String id;
	protected String Name;
	private int Intelligence;
	private int hunger;
	private int brainsEaten;
	private boolean inFight;
	private int energy=10;
	private boolean finallyDead=false;


	//Konstruktor
	/**
	 * Methode Zombie
	 * Beschreibung: Konstruktor der Klasse Zombie, legt Parameter des Zombies fest (ein gro�teil ist immer gleich, lediglich Name bestimmbar)
	 * @param Name �bernimmt den gew�nschten Namen des Opfers
	 */
	public Zombie (String Name) {
		this.setId(UUID.randomUUID().toString());
		this.Name = Name;
		this.Intelligence = (int)(Math.random()*10) + 1;
		this.hunger = 0;
		this.brainsEaten = 0;
		this.inFight=false;
	}
	
	//Operationen

	/**
	 * Methode attack
	 * Beschreibung: l�sst den Zombie einen Angriff auf ein Opfer durchf�hren
	 * @param Target �bernimmt das Ziel des Angriffes um Verteidigung des Opfers einzuleiten
	 */
	public void attack (Victim Target) {
		System.out.println("\nDer Zombie "+ this.getName()+" greift an.");
		this.setInFight(true);
		if((int)(Math.random()*10)+1==1) { //10% Wahrscheinlichkeit f�r kritischen Schaden
			Target.defend((int)(Math.random()*6)*2); //Zuf�lliger Schaden von (0-5)*2 (da kritisch); Weitergabe des Schadens an das Opfer, das sich verteidigen kann
		} else { 
			Target.defend((int)(Math.random()*6));//Zuf�lliger Schaden von 0-5, Weitergabe des Schadens an das Opfer, das sich verteidigen kann
		}	
	}
	
	/**
	 * Methode attackHuman
	 * Beschreibung: Besondere Methode f�r den Angriff auf Menschen, da andere Klasse des Ziels, ansonsten ist alles analog zur Methode attack
	 * @param Target �bernimmt das Ziel des Angriffes um Verteidigung des Opfers einzuleiten
	 */
	public void attackHuman (HumanVictim Target) {
		System.out.println("\nDer Zombie "+ this.getName()+" greift an.");
		this.setInFight(true);
		if((int)(Math.random()*10)+1==1) {
			int Damage = (int)(Math.random()*6)*2;
			Target.defend(Damage); //Weitergabe des Schadens an das menschliche Opfer, das sich verteidigen kann
		} else { 
			int Damage = (int)(Math.random()*6);
			Target.defend(Damage); //Weitergabe des Schadens an das menschliche Opfer, das sich verteidigen kann
		}		
	}
	
	/**
	 * Methode attackHunter
	 * Beschreibung:Besondere Methode f�r den Angriff auf einen ZombieHunter, da andere Klasse des Ziels, ansonsten ist alles analog zur Methode attack
	 * @param Target �bernimmt das Ziel des Angriffes um Schaden an ZombieHunter weiter zu geben
	 */
	public void attackHunter (ZombieHunter Target) {
		System.out.println("\nDer Zombie "+ this.getName()+" greift an.");
		this.setInFight(true);
		if((int)(Math.random()*10)+1==1) { 
			int Damage = (int)(Math.random()*6)*2;
			Target.takeDamage(Damage); //Weitergabe des Schadens an den ZombieHunter
		} else { 
			int Damage = (int)(Math.random()*6);
			Target.takeDamage(Damage); //Weitergabe des Schadens an den ZombieHunter
		}		
	}
	 
	/**
	 * Methode takeDamage
	 * Beschreibung: Verbucht Schaden den der Zombie erlitten hat und pr�ft ob dieser t�dlich ist
	 * @param Damage �bernimmt Schaden der verbucht werden soll
	 */
	public void takeDamage (int Damage) {
		this.setInFight(true);
		if (Damage<this.energy) {//nicht t�dlicher Schaden
			this.setEnergy(this.getEnergy()-Damage);//"Verbuchen" von Schaden
			System.out.println("Der Zombie "+this.getName()+" hat "+Damage+" Schaden erlitten.");
		} else if (Damage>=this.getEnergy()) {//t�tlicher Schaden
			System.out.println("Der Zombie "+this.getName()+" hat "+this.getEnergy()+" Schaden erlitten.");//Ausgabe der Lebenspunkte als Schaden, das nicht mehr Schaden als Lebenspunkte verursacht werden kann
			System.out.println("\nDer Zombie "+this.getName()+" ist entg�ltig tod!");
			this.setEnergy(0);
			this.setFinallyDead(true);//Zombie wird als final Tod markiert
			this.setInFight(false);
		}
	}
	
	/**
	 * Methode eat
	 * Beschreibung: Methode zur Verbuchung eines gefressenen Gehirnes, �nderung des Hungers und der Zahl der gefressenen Gehirne
	 */
	public void eat () {
		this.hunger=0;
		this.brainsEaten++;
	}
	
	/**
	 * Methode: dance
	 * Beschreibung: Methode, welche den Zombie tanzen l�sst und zuf�llige das Ergebnis bestimmt
	 */
	public void dance () {
		System.out.println("\nDer Zombie "+this.getName()+" f�ngt an zu tanzen!");
		int Zufall=(int)(Math.random()*5)+1; //Jeweils 20% Wahrscheinlichkeit f�r jedes Szenario
		if (Zufall==1) {//Szenario 1
			System.out.println(this.getName()+" hat beim Tanzen sein linkes Bein verloren, er muss es wieder einsammeln.\n");
		}else if (Zufall ==2) {//Szenario 2
			System.out.println(this.getName()+" hat beim Tanzen sein rechtes Bein verloren, er muss es wieder einsammeln.\n");
		} else if (Zufall ==3) {//Szenario 3
			System.out.println(this.getName()+" hat beim Tanzen seinen linken Arm verloren, er muss ihn wieder einsammeln.\n");
		} else if (Zufall ==4) {//Szenario 4
			System.out.println(this.getName()+" hat beim Tanzen seinen rechten Arm verloren, er muss ihn wieder einsammeln.\n");
		}else {//Szenario 5
			System.out.println(this.getName()+" hat zu Gagnam Style getanzt!\n");
		}
	}
	
	/**
	 * Methode showData
	 * Beschreibung: Methode die das Datenblatt des Zombies ausgibt
	 */
	public void showData() {
		System.out.println("\nDatenblatt vom Zombie "+this.getName()+"\nEnergie: "+this.getEnergy()+"\nIntelligenz: "+this.getIntelligence()+"\nHunger: "+this.getHunger()+"\ngefressene Gehirne: "+this.getbrainsEaten());
	}
	
	//Getter & Setter
	
	/**
	 * Methode getName
	 * Beschreibung: gibt den Names des Zombies aus
	 * @return gibt den Wert des Parameters Names aus
	 */
	public String getName () {
	return Name;
	}
	
	/**
	 * Methode: setNames
	 * Beschreibung: legt den Names des Zombies fest
	 * @param Name �bernimmt den gew�nschten Names des Zombies
	 */
	public void setName (String Name) {
		this.Name=Name;
	}

	/**
	 * Methode getId
	 * Beschreibung: 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Methode setId
	 * Beschreibung: 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Methode getHunger
	 * Beschreibung: gibt den Hunger des Zombie aus
	 * @return gibt den Wert des Parameters Hunger aus
	 */
	public int getHunger() {
		return hunger;
	}
	
	/**
	 * Methode setHunger
	 * Beschreibung: legt den Hunger des Zombies fest
	 * @param �bernimmt den neuen Hunger-Wert des Zombies
	 */
	public void setHunger(int Hunger) {
		this.hunger=this.hunger+Hunger;
	}
	/**
	 * Methode getIntelligence
	 * Beschreibung: Methode gibt die Intelligenz des Zombies aus
	 * @return gibt den Wert des Parameters Intelligence aus
	 */
	public int getIntelligence() {
		return Intelligence;
	}
	
	/**
	 * Methode getbrainsEaten
	 * Beschreibung: Methode gibt die Anzahl der gefressenen Gehirne des Zombies aus
	 * @return gibt den Wert des Parameters brainsEaten aus
	 */
	public int getbrainsEaten() {
		return brainsEaten;
	}
	
	/**
	 * Methode getEnergy
	 * Beschreibung: Methode gibt die Energy ("Lebenspunkte") des Zombies aus
	 * @return gibt den Wert des Parameters energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Methode isInFight
	 * Beschreibung: Methode gibt aus ob sich der Zombie im Kampf befindet
	 * @return gibt true oder false aus, je nachdem ob sich der Zombie gerade im Kampf befindet
	 */
	public boolean isInFight() {
		return inFight;
	}

	/**
	 * Methode setInFight
	 * Beschreibung: Methode legt fest ob der Zombie sich aktuell in einem Kampf befindet
	 * @param inFight �bernimmt ob der Zombie sich aktuell im Kampf befindet (true oder false)
	 */
	public void setInFight(boolean inFight) {
		this.inFight = inFight;
	}
	
	/**
	 * Methode setIntelligence
	 * Beschreibung: Methode legt fest welche Intelligenz der Zombie hat
	 * @param Intelligence �bernimmt den Intelligenz-Wert den der Zombie haben soll
	 */
	public void setIntelligence(int Intelligence) {
		this.Intelligence = Intelligence;
	}

	/**
	 * Methode setEnergy
	 * Beschreibung: Methode legt die Energie ("Lebenspunkte") des Zombies fest
	 * @param energy �bernimmt die Energie, die der Zombie haben soll
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * Methode isFinallyDead
	 * Beschreibung:Methode gibt aus ob der Zombie final tod ist (true wenn ja, false wenn nicht)
	 * @return gibt true oder false aus, je nachdem ob der Zombie final tod ist
	 */
	public boolean isFinallyDead() {
		return finallyDead;
	}
	
	/**
	 * Methode setFinnalyDead
	 * Beschreibung: Methode legt fesst ob der Zobmie final tod ist
	 * @param finallyDead �bernimmt ob der Zombie final tod ist (true oder false)
	 */
	public void setFinallyDead(boolean finallyDead) {
		this.finallyDead = finallyDead;
	}
}	
