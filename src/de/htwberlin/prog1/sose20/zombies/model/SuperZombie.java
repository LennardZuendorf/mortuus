package de.htwberlin.prog1.sose20.zombies.model;

/**
 * 
 * @version 1.1
 * @since 01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 - finaler Check + Abschluss der Dokumentation
 * @author Lennard Z�ndorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die Klasse SuperZombie beschreibt eine Erweiterung der Zombie-Klasse mit zus�tzliches Attributen & Operationen
 */
public class SuperZombie extends Zombie{

	//Attribute
	public Zombie[] ZombieGroup = new Zombie[25];
	private int zombiePower = 0;
	
	//Konstruktor
	
	/**
	 * Methode SuperZombie
	 * Beschreibung: Konstruktor der Klasse SuperZombie, legt grundlegende Parameter des SuperZombie fest und f�hrt Konstruktor der Zombie Klasse aus
	 * @param Name
	 */
	public SuperZombie(String Name) {
		super(Name);//Weitergabe an Konstruktor der Zombie-Klasse
		this.setIntelligence(130);//Intelligenz f�r SuperZombies immer 130
	}
	
	//Operationen
	
	/**
	 * Methode commandToAttack
	 * Beschreibung: Methode befiehlt dem �bernommenen Anh�nger den Angriff auf das �bernommene Ziel und f�hrt eine der attack-Methode des Zombies aus
	 * @param Attacker �bernimmt den Zombie Anh�nger der Angreifen soll
	 * @param Target �bernimmt das Ziel des Angreifer
	 */
	public void commandToAttack (Zombie Attacker, Victim Target) {
		for (int x=0;x<ZombieGroup.length;x++) {//Auswahl aller Zombies in der Gruppe (der Reihe nach)
			if (ZombieGroup[x]==Attacker) {//Test ob Array gef�llt ist, um NullPointerException zu verhindern
				ZombieGroup[x].attack(Target);//Angriffsbefehl 
			}
		}
	}
	
	/**
	 * Methode commandToAttack
	 * Beschreibung: Die Methode befiehlt dem �bernommenen Anh�nger den Angriff auf das �bernommene Ziel und f�hrt die attackHuman-Methode des Zombies aus
	 * (Die Methode ist analog zur 1. commandToAttack Methode und hat lediglich ein anderes Ziel)
	 * @param Attacker �bernimmt den Zombie Anh�nger der Angreifen soll
	 * @param Target �bernimmt das menschliche Ziel des Angreifer
	 */
	public void commandToAttack (Zombie Attacker, HumanVictim Target) {//Die Methode ist prinzipiell die gleiche wie commandToAttack-Methode, deshalb keine Kommentare
		for (int x=0;x<ZombieGroup.length;x++) {
			if (ZombieGroup[x]==Attacker) {
				ZombieGroup[x].attack(Target);
			}
		}
	}
	
	/**
	 * Methode commandToAttack
	 * Beschreibung: Die Methode befiehlt dem �bernommenen Anh�nger den Angriff auf das �bernommene Ziel und f�hrt die attackHuman-Methode des Zombies aus
	 * (Die Methode ist analog zur 1. commandToAttack Methode und hat lediglich ein anderes Ziel)
	 * @param Attacker �bernimmt den Zombie Anh�nger der Angreifen soll
	 * @param Target �bernimmt den ZombieHunter den der Angreifer angreifen soll
	 */
	public void commandToAttack (ZombieHunter Target) {//Die Methode ist prinzipiell die gleiche wie commandToAttack-Methode, deshalb keine Kommentare
		for (int x=0;x<ZombieGroup.length;x++) {
			if (ZombieGroup[x]!= null) {
				ZombieGroup[x].attackHunter(Target);
			}
		}
	}
	
	/**
	 * Methode attack
	 * Beschreibung: Die Methode �bernimmt das Ziel des Angriffes und f�hrt einen Angriff des SuperZombies durch, analog zur attack-Methode der ZombieKlasse (der Schaden ist unterschiedlich)
	 * @param Target �bernimmt das Ziel des Angriffes, um defend-Methode des Ziels auf zu rufen
	 */
	public void attack (Victim Target) {
		System.out.println("\nDer SuperZombie "+ this.getName()+" greift an.");
		if((int)(Math.random() * 10)+1==1) { //10% Wahrscheinlichkeit f�r kritischen Schaden
			Target.defend((int)(Math.random()*1)*2); //Zuf�lliger Schaden von (0-1)*2 (da kritisch); Weitergabe des Schadens an das Opfer, das sich verteidigen kann
		} else { 
			Target.defend((int)(Math.random()*1)); //Zuf�lliger Schaden von 0-5, Weitergabe des Schadens an das Opfer, das sich verteidigen kann
		}
	}
	
	/**
	 * Methode attackHuman
	 * Beschreibung: Die Methode �bernimmt das Ziel des Angriffes und f�hrt einen Angriff des SuperZombies durch, analog zur attack-Methode der ZombieKlasse (der Schaden ist unterschiedlich)
	 * (Die Methode ist analog zur 1. attack Methode und hat lediglich ein anderes Ziel)
	 * @param Target �bernimmt das Ziel des Angriffes, um defend-Methode des Ziels auf zu rufen
	 */
	public void attackHuman (HumanVictim Target) {//Die Methode ist prinzipiell die gleiche wie attack-Methode, deshalb keine Kommentare
		System.out.println("\nDer SuperZombie "+ this.getName()+" greift an.");
		if((int)(Math.random() * 10)+1==1) { 
			Target.defend((int)(Math.random()*1)*2);
		} else { 
			Target.defend((int)(Math.random()*1));
		}
	}
	/**
	 * Methode attack
	 * Beschreibung: Die Methode �bernimmt das Ziel des Angriffes und f�hrt einen Angriff des SuperZombies durch, analog zur attack-Methode der ZombieKlasse (der Schaden ist unterschiedlich)
	 * (Die Methode ist analog zur 1. attack Methode und hat lediglich ein anderes Ziel)
	 * @param Target �bernimmt das Ziel des Angriffes, um takeDamage-Methode des Ziels auf zu rufen und Schaden zu "verbuchen"
	 */
	public void attackHunter (ZombieHunter Target) {//Die Methode ist prinzipiell die wie attack-Methode, deshalb keine Kommentare
		System.out.println("\nDer SuperZombie "+ this.getName()+" greift an.");
		if((int)(Math.random() * ((10 - 0) + 1))==1) { 
			Target.takeDamage((int)(Math.random()*1)*2); 
		} else { 
			Target.takeDamage((int)(Math.random() *1));
		}
	}
	
	/**
	 * Methode flee
	 * Beschreibung: Die Methode f�hrt einen Fluchtversuch des SuperZombies aus, Sie ist analog zur flee-Methode der ZombieKlasse (die Wahrscheinlichkeiten sind unterschiedlich)
	 * @return gibt aus ob der Fluchtversuch erfolgreich war (true) oder nicht (false)
	 */
	public boolean flee () {
		if((int)(Math.random()*4)+1<=3) {//Wahrscheinlichkeit von 75% das der Fluchversuch erfolgreich ist
			System.out.println("Der SuperZombie "+this.getName()+" konnte fliehen.");
			return true;//Ausgabe bei erfolgreichem Fluchtversuch
		}else {
			System.out.println("Der SuperZombie "+this.getName()+" konnte nicht fliehen.");
			return false;//Ausgabe bei erfolgreichem Fluchtversuch
		}
	}
	
	/**
	 * Methode dance
	 * Beschreibung: Die Methode l�sst den SuperZombie und die gesamte Gruppe des Zombies tanzen, es werden zuf�llige Szenarien ausgef�hrt
	 */
	public void dance () {
		System.out.println("\nDer SuperZombie "+this.getName()+" f�ngt an zu tanzen!");
		int Zufall = (int)(Math.random()*5)+1;//Tanz des SuperZombies, analog zur dance-Methode des Zombies, jedes Szenario hat eine 20% Wahrscheinlichkeit
		if (Zufall == 1) {//Szenario 1
			System.out.println(this.getName()+" hat beim Tanzen sein linkes Bein verloren!");
		}else if (Zufall == 2) {//Szenario 2
			System.out.println(this.getName()+" hat beim Tanzen sein rechtes Bein verloren!");
		} else if (Zufall == 3) {//Szenario 3
			System.out.println(this.getName()+" hat beim Tanzen seinen linken Arm verloren!");
		} else if (Zufall ==4) {//Szenario 4
			System.out.println(this.getName()+" hat beim Tanzen seinen rechten Arm verloren!");
		}else {//Szenario 5
			System.out.println(this.getName()+" zu Single Ladies von Beyonc� getanzt!");
		}
		for (int i=0;i<ZombieGroup.length;i++){//Tanzen der gesamten Gruppe, Auswahl jedes einzellnen Zombies im Array
			if(ZombieGroup[i]!=null) {//�berpr�fung ob der Wert des Arrays nicht leer ist, um NullPointerException zu Verhindern
				ZombieGroup[i].dance();//Ausf�hrung der danc-Methode f�r jeden Zombie
			}
		}
	}
	
	/**
	 * Methode addToGroup
	 * Beschreibung: Die Methode f�gt der ZombieGruppe (Array)
	 * @param Member
	 */
	public void addToGroup (Zombie Member) {
		int c=0;//Kontroll-Variable
		for (int x=0;x<ZombieGroup.length;x++){//Auswahl jedes einzellnen feldes des Arrays
			if (c==0&&ZombieGroup[x]==null) {//�berpr�fung ob das Feld leer ist & ob der Zombie-Anha�nger bereits hinzugef�gt wurde (dann w�re c==1)
				ZombieGroup[x]=Member; //hinzuf�gen des Zombies zur ZombieGroup
				c++;//Erh�hung der Kontroll-Variable
				zombiePower++;//Erh�hung der Macht des Zombies (gibt Gr��e der Gruppe wieder)
			}
		}
		if (c==0) {//�berpr�fung ob tats�chlich ein Zombie hinzugef�gt wurde, nach Ablauf der for-Schleife
			System.out.println("Der Zombie konnte nicht hinzugef�gt werden, vielleicht ist die Gruppe bereits voll? Ein SuperZombie kann maximal 25 Anh�nger haben.");
		}
	}
	
	/**
	 * Methode deleteFromGroup
	 * Beschreibung: Die Methode entfernt einen Zombie aus dem Array ZombieGroup
	 * @param Member �bernimmt den Zombie der aus dem Array entfernt werden soll
	 */
	public void deleteFromGroup (Zombie Member) {
		for (int x=0;x<ZombieGroup.length;x++) {//Auswahl jedes einzellnen feldes des Arrays
			if (ZombieGroup[x]!=null) {//�berpr�fung ob das Feld leer ist, um NullPointer Exception zu verhindern
				if (ZombieGroup[x].getName()==Member.getName()) {//�berpr�fung ob es sich um den gesuchten Zombie handelt
					ZombieGroup[x]=null;//Entfernen des Zombies aus dem Array
					zombiePower=zombiePower-1;//Verringern der ZombiePower
					System.out.println("Der Zombie "+Member.getName()+" ist nicht mehr ein Anh�nger von "+this.getName()+".");
				}
			}
		}
	}
	
	/**
	 * Methode showGroupData
	 * Beschreibung: Die Methode gibt die Datenbl�tter aller Zombies in der ZombieGruppe aus
	 */
	public void ShowGroupData() {
		for (int x=0;x<ZombieGroup.length;x++){//Auswahl jedes einzellnen Feldes des Arrays
			if (ZombieGroup[x]!=null) {//�berpr�fung ob das Feld leer ist, um NullPointer Exception zu verhindern
				ZombieGroup[x].showData();// Ausf�hrung der Methode showData des jeweiligen Zombies
			}
		}
	}
	
	/**
	 * Methode showGroupNames
	 * Beschreibung: Die Methode gibt eine Liste der Mitglieder der ZombieGruppe aus
	 * @return gibt eine Liste der Mitglieder als String aus, oder das keine Mitglieder vorhanden sind
	 */
	public String ShowGroupNames() {
		int c=0;//Kontroll-Variable
		String GroupNames = new String("");//Ausgabe-String
			for (int x=0;x<ZombieGroup.length;x++){//Auswahl jedes einzellnen Feldes des Arrays
				if (ZombieGroup[x]!=null) {//Kontrolle ob das Feld leer ist, um NullPointer Exception zu verhindern
					GroupNames=GroupNames+ZombieGroup[x].getName()+", ";//Name des Zombies wird zum Ausgabe-String hinzugef�gt
					c++;//in Kontroll Variable wird vermerkt, dass ein ZombieName zur Gruppe hinzugef�gt wurde
				}
			} 
		if (c!=0) {//Ausgabe der Namen als String, falls mindestens ein Name hinzugef�gt wurde
			GroupNames=GroupNames.substring(0, GroupNames.length()-2);//L�schen der letzten beiden Zeichen des Strings, um letztes Kommata zu l�schen
			return GroupNames;//Ausgabe des Ausgabe-Strings
		}else {
			return "keine Mitglieder";//Ausgabe wenn kein Mitglied in der Gruppe ist
		}
	}
	
	/**
	 * Methode showData
	 * Beschreibung: Die Methode gibt das Datenblatt des SuperZombies aus (Methode prinzipiell leich wie showData-Methode der Zombie-Klasse, hat aber mehr Parameter)
	 */
	public void ShowData() {
		System.out.println("\nDatenblatt vom SuperZombie "+this.getName()+"\nIntelligenz: "+this.getIntelligence()+"\nMacht: "+this.getPower()+"\nEnergie: "+this.getEnergy()+"\nHunger: "+this.getHunger()+"\nAnh�nger: "+this.ShowGroupNames()+"\ngefressene Gehirne: "+this.getbrainsEaten());		
	}
	
	//Getter & Setter
	
	/**
	 * Methode getPower
	 * Beschreibung: Die Methode gibt die Macht (Power) des SuperZombies aus
	 * @return gibt Wert des Parameters ZombiePower aus
	 */
	public int getPower() {
		return this.zombiePower;
	}
	
	/**
	 * Methode setPower
	 * Beschreibung: legt die Macht (Power) des SuperZombies fest
	 * @param Power �bernimmt den neuen Wert f�r den Parameter ZombiePower
	 */
	public void setPower(int Power) {
		this.zombiePower=Power;
	}
}
