package tech.ignitr.model;

import java.util.UUID;

/**
 * @version 2.0
 * @author Lennard Zündorf
 * Description: Victim Class is the superclass of HumanVictim
 */
public class Victim {

    private String id;
    private final String type;
    private int Intelligence;
    private int healthPointsCurrent;
    private boolean alive;

    /**
     * Method Victim
     * Description: Constructor of the class Victim
     * @param type is the type of the victim (Human, Zombie, ZombieHunter)
     */
    public Victim (String type) {
        this.setId(UUID.randomUUID().toString());
        this.type=type;
        this.setIntelligence((int)(Math.random()*80)+ 1);
        this.setHealthPointsCurrent(10);
        this.setAlive(true);
    }

    /**
     * Method defend
     * Description: allows the victim to defend itself against an attack
     * @param Damage is the damage, which is inflicted by the attacker
     */
    public void defend(int Damage) {
        if((int)(Math.random()*4)+1==1) {
            System.out.println(this.getType()+" has successfully defended itself!");
        } else {
            this.takeDamage(Damage);
        }
    }

    /**
     * Method takDamage
     * Description allows the victim to take damage
     * @param Damage is the damage, which is inflicted by the attacker
     */
    public void takeDamage(int Damage) {
        if (Damage<this.healthPointsCurrent) {
            this.healthPointsCurrent=this.healthPointsCurrent-Damage;
            System.out.println(this.getType()+" has taken "+Damage+" points of damage. "+this.getHealthPointsCurrent()+" Health Points remaining.");
        } else {
            System.out.println(this.getType()+" has taken "+this.healthPointsCurrent+" points of damage. "+this.getHealthPointsCurrent()+" Health Points remaining.");
            this.healthPointsCurrent=0;
            this.setAlive(false);
        }
    }

    /**
     * Method flee
     * Description: allows the victim to try to flee
     * @return returns true, if the victim was able to flee, or false, if the victim was not able to flee
     */
    public Boolean flee() {
        if((int)(Math.random()*5)+1==1) {
            System.out.println("The escape attempt was successful!");
            return true;
        }else {
            System.out.println("The escape attempt failed!");
            return false;
        }
    }

    /**
     * Method getID
     * Description: returns the ID of the victim
     * @return returns the ID of the victim
     */
    public String getId() {
        return id;
    }

    /**
     * Method setId
     * Description: sets the ID of the victim
     * @param id is the ID of the victim
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method getType
     * Description: Returns the type of the victim
     * @return returns the type of the victim
     */
    public String getType() {
        return type;
    }

    /**
     * Method getIntelligence
     * Description: returns the intelligence of the victim
     * @return returns the intelligence of the victim
     */
    public int getIntelligence() {
        return Intelligence;
    }

    /**
     * Method setIntelligence
     * Description: sets the intelligence of the victim
     * @param Intelligence is the intelligence of the victim
     */
    public void setIntelligence(int Intelligence) {
        this.Intelligence=Intelligence;
    }

    /**
     * Method getHealthPointsCurrent
     * Description: returns the current health points of the victim
     * @return returns the current health points of the victim
     */
    public int getHealthPointsCurrent() {
        return healthPointsCurrent;
    }

    /**
     * Method setHealthPointsCurrent
     * Description: sets the current health points of the victim
     * @param HealthPoints übernimmt den Wert für die aktuellen Lebenspunkte des Opfers
     */
    public void setHealthPointsCurrent(int HealthPoints) {
        this.healthPointsCurrent=HealthPoints;
    }

    /**
     * Method isAlive
     * Description: gives information, if the victim is alive or dead
     * @return returns true, if the victim is alive, or false, if the victim is dead
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Method setAlive
     * Description: sets, if the victim is alive or dead
     * @param alive is true, if the victim is alive, or false, if the victim is dead
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}