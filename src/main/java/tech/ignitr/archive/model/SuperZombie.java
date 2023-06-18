package tech.ignitr.archive.model;

import java.util.Objects;

/**
 *
 * @version 1.1
 * @since 01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 - finaler Check + Abschluss der Dokumentation
 * @author Lennard Zündorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die Klasse SuperZombie beschreibt eine Erweiterung der Zombie-Klasse mit zusätzliches Attributen & Operationen
 */
public class SuperZombie extends Zombie{

    //Attribute
    public Zombie[] ZombieGroup = new Zombie[25];
    private int zombiePower = 0;

    //Konstruktor

    /**
     * Methode SuperZombie
     * Beschreibung: Konstruktor der Klasse SuperZombie, legt grundlegende Parameter des Superzombies fest und führt Konstruktor der Zombie Klasse aus
     * @param Name übernimmt den Namen des SuperZombies
     */
    public SuperZombie(String Name) {
        super(Name);//Weitergabe an Konstruktor der Zombie-Klasse
        this.setIntelligence(130);//Intelligenz für SuperZombies immer 130
    }

    //Operationen

    /**
     * Methode commandToAttack
     * Beschreibung: Methode befiehlt dem übernommenen Anhänger den Angriff auf das übernommene Ziel und führt eine der attack-Methode des Zombies aus
     * @param Attacker übernimmt den Zombie Anhänger dem Angreifen soll
     * @param Target übernimmt das Ziel der Angreifer
     */
    public void commandToAttack (Zombie Attacker, Victim Target) {
        for (Zombie zombie : ZombieGroup) {//Auswahl aller Zombies in der Gruppe (der Reihe nach)
            if (zombie == Attacker) {//Test, ob Array gefüllt ist, um NullPointerException zu verhindern
                zombie.attack(Target);//Angriffsbefehl
            }
        }
    }

    /**
     * Methode commandToAttack
     * Beschreibung: Die Methode befiehlt dem übernommenen Anhänger den Angriff auf das übernommene Ziel und führt die attackHuman-Methode des Zombies aus
     * (Die Methode ist analog zur 1. commandToAttack Methode und hat lediglich ein anderes Ziel)
     * @param Attacker übernimmt den Zombie Anhänger dem Angreifen soll
     * @param Target übernimmt das menschliche Ziel der Angreifer
     */
    public void commandToAttack (Zombie Attacker, HumanVictim Target) {//Die Methode ist prinzipiell die gleiche wie commandToAttack-Methode, deshalb keine Kommentare
        for (Zombie zombie : ZombieGroup) {
            if (zombie == Attacker) {
                zombie.attack(Target);
            }
        }
    }

    /**
     * Methode commandToAttack
     * Beschreibung: Die Methode befiehlt dem übernommenen Anhänger den Angriff auf das übernommene Ziel und führt die attackHuman-Methode des Zombies aus
     * (Die Methode ist analog zur 1. commandToAttack Methode und hat lediglich ein anderes Ziel)
     * @param Target übernimmt den ZombieHunter den der Angreifer angreifen soll
     */
    public void commandToAttack (ZombieHunter Target) {
        for (Zombie zombie : ZombieGroup) {
            if (zombie != null) {
                zombie.attackHunter(Target);
            }
        }
    }

    /**
     * Methode attack
     * Beschreibung: Die Methode übernimmt das Ziel des Angriffes und führt einen Angriff des SuperZombies durch, analog zur attack-Methode der ZombieKlasse (der Schaden ist unterschiedlich)
     * @param Target Übernimmt das Ziel des Angriffes, um defend-Methode des Ziels aufzurufen
     */
    public void attack (Victim Target) {
        System.out.println("\nDer SuperZombie "+ this.getName()+" greift an.");
        if((int)(Math.random() * 10)+1==1) { //10 % Wahrscheinlichkeit für kritischen Schaden
            Target.defend((int)(Math.random()*1)*2); //Zufälliger Schaden von (0-1)*2 (da kritisch); Weitergabe des Schadens an das Opfer, das sich verteidigen kann
        } else {
            Target.defend((int)(Math.random()*1)); //Zufälliger Schaden von 0 bis 5, Weitergabe des Schadens an das Opfer, das sich verteidigen kann
        }
    }

    /**
     * Methode attackHuman
     * Beschreibung: Die Methode übernimmt das Ziel des Angriffes und führt einen Angriff des SuperZombies durch, analog zur attack-Methode der ZombieKlasse (der Schaden ist unterschiedlich)
     * (Die Methode ist analog zur 1. attack Methode und hat lediglich ein anderes Ziel)
     * @param Target Übernimmt das Ziel des Angriffes, um defend-Methode des Ziels aufzurufen
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
     * Beschreibung: Die Methode übernimmt das Ziel des Angriffes und führt einen Angriff des SuperZombies durch, analog zur attack-Methode der ZombieKlasse (der Schaden ist unterschiedlich)
     * (Die Methode ist analog zur 1. attack Methode und hat lediglich ein anderes Ziel)
     * @param Target Übernimmt das Ziel des Angriffes, um takeDamage-Methode des Ziels aufzurufen und Schaden zu "verbuchen"
     */
    public void attackHunter (ZombieHunter Target) {//Die Methode ist prinzipiell die wie attack-Methode, deshalb keine Kommentare
        System.out.println("\nDer SuperZombie "+ this.getName()+" greift an.");
        if((int)(Math.random() * ((10) + 1))==1) {
            Target.takeDamage((int)(Math.random()*1)*2);
        } else {
            Target.takeDamage((int)(Math.random() *1));
        }
    }

    /**
     * Methode flee
     * Beschreibung: Die Methode führt einen Fluchtversuch des SuperZombies aus, Sie ist analog zur flee-Methode der ZombieKlasse (die Wahrscheinlichkeiten sind unterschiedlich)
     * @return gibt, aus ob der Fluchtversuch erfolgreich war (true) oder nicht (false)
     */
    public boolean flee () {
        if((int)(Math.random()*4)+1<=3) {//Wahrscheinlichkeit von 75 % das der Fluchversuch erfolgreich ist
            System.out.println("Der SuperZombie "+this.getName()+" konnte fliehen.");
            return true;//Ausgabe bei erfolgreichem Fluchtversuch
        }else {
            System.out.println("Der SuperZombie "+this.getName()+" konnte nicht fliehen.");
            return false;//Ausgabe bei erfolgreichem Fluchtversuch
        }
    }

    /**
     * Methode dance
     * Beschreibung: Die Methode lässt den SuperZombie und die gesamte Gruppe des Zombies tanzen, es werden zufällige Szenarien ausgeführt
     */
    public void dance () {
        System.out.println("\nDer SuperZombie "+this.getName()+" fängt an zu tanzen!");
        int Zufall = (int)(Math.random()*5)+1;//Tanz des SuperZombies, analog zur dance-Methode des Zombies, jedes Szenario hat eine 20 % Wahrscheinlichkeit
        if (Zufall == 1) {//Szenario 1
            System.out.println(this.getName()+" hat beim Tanzen sein linkes Bein verloren!");
        }else if (Zufall == 2) {//Szenario 2
            System.out.println(this.getName()+" hat beim Tanzen sein rechtes Bein verloren!");
        } else if (Zufall == 3) {//Szenario 3
            System.out.println(this.getName()+" hat beim Tanzen seinen linken Arm verloren!");
        } else if (Zufall ==4) {//Szenario 4
            System.out.println(this.getName()+" hat beim Tanzen seinen rechten Arm verloren!");
        }else {//Szenario 5
            System.out.println(this.getName()+" zu Single Ladies von Beyoncé getanzt!");
        }
        for (Zombie zombie : ZombieGroup) {//Tanzen der gesamten Gruppe, Auswahl jedes einzellnen Zombies im Array
            if (zombie != null) {//Überprüfung, ob der Wert des Arrays nicht leer ist, um NullPointerException zu Verhindern
                zombie.dance();//Ausführung der danc-Methode für jeden Zombie
            }
        }
    }

    /**
     * Methode addToGroup
     * Beschreibung: Die Methode fügt der ZombieGruppe (Array)
     * @param Member Übernimmt den Zombie, der der Gruppe hinzugefügt werden soll
     */
    public void addToGroup (Zombie Member) {
        int c=0;//Kontroll-Variable
        for (int x=0;x<ZombieGroup.length;x++){//Auswahl jedes einzellnen feldes des Arrays
            if (c==0&&ZombieGroup[x]==null) {//Überprüfung, ob das Feld leer ist & ob der Zombie-Anhaänger bereits hinzugefügt wurde (dann wäre c==1)
                ZombieGroup[x]=Member; //hinzufügen des Zombies zur ZombieGroup
                c++;//Erhöhung der Kontroll-Variable
                zombiePower++;//Erhöhung der Macht des Zombies (gibt Größe der Gruppe wieder)
            }
        }
        if (c==0) {//Überprüfung, ob tatsächlich ein Zombie hinzugefügt wurde, nach Ablauf der for-Schleife
            System.out.println("Der Zombie konnte nicht hinzugefügt werden, vielleicht ist die Gruppe bereits voll? Ein SuperZombie kann maximal 25 Anhänger haben.");
        }
    }

    /**
     * Methode deleteFromGroup
     * Beschreibung: Die Methode entfernt einen Zombie aus dem Array ZombieGroup
     * @param Member übernimmt den Zombie, der aus dem Array entfernt werden soll
     */
    public void deleteFromGroup (Zombie Member) {
        for (int x=0;x<ZombieGroup.length;x++) {//Auswahl jedes einzelnen feldes des Arrays
            if (ZombieGroup[x]!=null) {//Überprüfung, ob das Feld leer ist, um NullPointer Exception zu verhindern
                if (Objects.equals(ZombieGroup[x].getName(), Member.getName())) {//Überprüfung, ob es sich um den gesuchten Zombie handelt
                    ZombieGroup[x]=null;//Entfernen des Zombies aus dem Array
                    zombiePower=zombiePower-1;//Verringern der ZombiePower
                    System.out.println("Der Zombie "+Member.getName()+" ist nicht mehr ein Anhänger von "+this.getName()+".");
                }
            }
        }
    }

    /**
     * Methode showGroupData
     * Beschreibung: Die Methode gibt die Datenblätter aller Zombies in der ZombieGruppe aus
     */
    public void ShowGroupData() {
        for (Zombie zombie : ZombieGroup) {//Auswahl jedes einzelnen Feldes des Arrays
            if (zombie != null) {//Überprüfung, ob das Feld leer ist, um NullPointer Exception zu verhindern
                zombie.showData();// Ausführung der Methode showData des jeweiligen Zombies
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
        String GroupNames = "";//Ausgabe-String
        for (Zombie zombie : ZombieGroup) {//Auswahl jedes einzelnen Feldes des Arrays
            if (zombie != null) {//Kontrolle, ob das Feld leer ist, um NullPointer Exception zu verhindern
                GroupNames = GroupNames + zombie.getName() + ", ";//Name des Zombies wird zum Ausgabe-String hinzugefügt
                c++;//in Kontroll Variable wird vermerkt, dass ein ZombieName zur Gruppe hinzugefügt wurde
            }
        }
        if (c!=0) {//Ausgabe der Namen als String, falls mindestens ein Name hinzugefügt wurde
            GroupNames=GroupNames.substring(0, GroupNames.length()-2);//Löschen der letzten beiden Zeichen des Strings, um letztes Kommata zu löschen
            return GroupNames;//Ausgabe des Ausgabe-Strings
        }else {
            return "keine Mitglieder";//Ausgabe, wenn kein Mitglied in der Gruppe ist
        }
    }

    /**
     * Methode showData
     * Beschreibung: Die Methode gibt das Datenblatt des SuperZombies aus (Methode prinzipiell leich wie showData-Methode der Zombie-Klasse, hat aber mehr Parameter)
     */
    public void ShowData() {
        System.out.println("\nDatenblatt vom SuperZombie "+this.getName()+"\nIntelligenz: "+this.getIntelligence()+"\nMacht: "+this.getPower()+"\nEnergie: "+this.getEnergy()+"\nHunger: "+this.getHunger()+"\nAnhänger: "+this.ShowGroupNames()+"\ngefressene Gehirne: "+this.getbrainsEaten());
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
     * @param Power übernimmt den neuen Wert für den Parameter ZombiePower
     */
    public void setPower(int Power) {
        this.zombiePower=Power;
    }
}