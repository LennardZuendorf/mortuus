package tech.ignitr.model;

import java.util.UUID;

/**
 * @version 2.0
 * @author Prof. Dr. Verena Majuntke and Lennard Zündorf
 * Description:
 */
public class Zombie {

    private String id;
    protected String Name;
    private int Intelligence;
    private int hunger;
    private int brainsEaten;
    private boolean inFight;
    private int energy=10;
    private boolean finallyDead=false;

    /**
     * Method Zombie
     * Description: Constructor of the class Zombie
     * @param Name name of the Zombie
     */
    public Zombie (String Name) {
        this.setId(UUID.randomUUID().toString());
        this.Name = Name;
        this.Intelligence = (int)(Math.random()*10) + 1;
        this.hunger = 0;
        this.brainsEaten = 0;
        this.inFight=false;
    }

    /**
     * Method attack
     * Description: Attack method of the Zombie class, which attacks the target
     * @param Target is the victim, which is attacked
     */
    public void attack (Victim Target) {
        System.out.println("\nThe Zombie "+ this.getName()+" attacks!");
        this.setInFight(true);
        if((int)(Math.random()*10)+1==1) {
            Target.defend((int)(Math.random()*6)*2);
        } else {
            Target.defend((int)(Math.random()*6));
        }
    }

    /**
     * Method attackHuman
     * Description: Special method for the attack on a HumanVictim, because of the different class of the target, otherwise everything is analog to the method attack
     * @param Target is the target of the attack, to pass the damage to the HumanVictim, which can defend itself
     */
    public void attackHuman (HumanVictim Target) {
        System.out.println("\nThe Zombie "+ this.getName()+" attacks!");
        this.setInFight(true);
        if((int)(Math.random()*10)+1==1) {
            int Damage = (int)(Math.random()*6)*2;
            Target.defend(Damage);
        } else {
            int Damage = (int)(Math.random()*6);
            Target.defend(Damage);
        }
    }

    /**
     * Method attackHunter
     * Description: Special method for the attack on a ZombieHunter, because of the different class of the target, otherwise everything is analog to the method attack
     * @param Target is the target of the attack, to pass the damage to the ZombieHunter, which can defend itself
     */
    public void attackHunter (ZombieHunter Target) {
        System.out.println("\nThe Zombie "+ this.getName()+" attacks!");
        this.setInFight(true);
        if((int)(Math.random()*10)+1==1) {
            int Damage = (int)(Math.random()*6)*2;
            Target.takeDamage(Damage);
        } else {
            int Damage = (int)(Math.random()*6);
            Target.takeDamage(Damage);
        }
    }

    /**
     * Method takeDamage
     * Description: Method to take damage, which is inflicted by the attacker
     * @param Damage is the damage, which is inflicted by the attacker
     */
    public void takeDamage (int Damage) {
        this.setInFight(true);
        if (Damage<this.energy) {
            this.setEnergy(this.getEnergy()-Damage);
            System.out.println("The Zombie "+this.getName()+" has taken "+Damage+" Damage.");
        } else if (Damage>=this.getEnergy()) {
            System.out.println("The Zombie "+this.getName()+" has taken "+this.getEnergy()+" Damage.");
            System.out.println("\nThe Zombie "+this.getName()+" is finally dead!");
            this.setEnergy(0);
            this.setFinallyDead(true);
            this.setInFight(false);
        }
    }

    /**
     * Method eat
     * Description: Method to eat a brain, which resets the hunger of the Zombie and increases the number of brains, which the Zombie has eaten
     */
    public void eat () {
        this.hunger=0;
        this.brainsEaten++;
    }

    /**
     * Method dance
     * Description: Method to let the Zombie dance, which has a 20 % chance for each of the 5 scenarios
     */
    public void dance () {
        System.out.println("\nThe Zombie "+this.getName()+" is dancing!");
        int Zufall=(int)(Math.random()*5)+1;
        if (Zufall==1) {
            System.out.println(this.getName()+ "has lost his left leg while dancing, he has to collect it again.\n");
        }else if (Zufall ==2) {
            System.out.println(this.getName()+" has lost his right leg while dancing, he has to collect it again.\n");
        } else if (Zufall ==3) {
            System.out.println(this.getName()+" has lost his left arm while dancing, he has to collect it again.\n");
        } else if (Zufall ==4) {
            System.out.println(this.getName()+" has lost his right arm while dancing, he has to collect it again.\n");
        }else {
            System.out.println(this.getName()+"has lost his head while dancing, he has to collect it again.\n");
        }
    }

    /**
     * Method showData
     * Description: Method to show the data of the Zombie
     */
    public void showData() {
        System.out.println("\nName: "+this.getName()+"\nIntelligence: "+this.getIntelligence()+"\nHunger: "+this.getHunger()+"\nBrains eaten: "+this.getbrainsEaten()+"\nEnergy: "+this.getEnergy()+"\nFinally dead: "+this.isFinallyDead());
    }

    /**
     * Method getName
     * Description: Method to get the name of the Zombie
     * @return returns the name of the Zombie
     */
    public String getName () {
        return Name;
    }

    /**
     * Method setNames
     * Description: Method to set the name of the Zombie
     * @param Name is the name of the Zombie
     */
    public void setName (String Name) {
        this.Name=Name;
    }

    /**
     * Method getId
     * Description: Method to get the ID of the Zombie
     * @return returns the ID of the Zombie
     */
    public String getId() {
        return id;
    }

    /**
     * Method setId
     * Description: Method to set the ID of the Zombie
     * @param id is the ID of the Zombie
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method getHunger
     * Description: Method to get the hunger of the Zombie
     * @return gibt den Wert des Parameters Hunger aus
     */
    public int getHunger() {
        return hunger;
    }

    /**
     * Method setHunger
     * Description: Method to set the hunger of the Zombie
     * @param Hunger takes the value of the hunger, which the Zombie should have
     */
    public void setHunger(int Hunger) {
        this.hunger=this.hunger+Hunger;
    }
    /**
     * Method getIntelligence
     * Description: Method to get the intelligence of the Zombie
     * @return return the value of the intelligence of the Zombie
     */
    public int getIntelligence() {
        return Intelligence;
    }

    /**
     * Method getbrainsEaten
     * Description: Returns the number of brains, which the Zombie has eaten
     * @return returns the number of brains, which the Zombie has eaten
     */
    public int getbrainsEaten() {
        return brainsEaten;
    }

    /**
     * Method getEnergy
     * Description: Returns the energy ("health points") of the Zombie
     * @return returns the energy ("health points") of the Zombie
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Method isInFight
     * Description: Method that returns whether the Zombie is currently in a fight
     * @return returns whether the Zombie is currently in a fight
     */
    public boolean isInFight() {
        return inFight;
    }

    /**
     * Method setInFight
     * Description: Method that sets whether the Zombie is currently in a fight
     * @param inFight takes the value of whether the Zombie is currently in a fight
     */
    public void setInFight(boolean inFight) {
        this.inFight = inFight;
    }

    /**
     * Method setIntelligence
     * Description: Methode legt die Intelligenz des Zombies fest
     * @param Intelligence takes the value of the intelligence, which the Zombie should have
     */
    public void setIntelligence(int Intelligence) {
        this.Intelligence = Intelligence;
    }

    /**
     * Method setEnergy
     * Description: Method sets the energy ("health points") of the Zombie
     * @param energy takes the value of the energy ("health points"), which the Zombie should have
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Method isFinallyDead
     * Description: Method returns whether the Zombie is finally dead
     * @return returns whether the Zombie is finally dead
     */
    public boolean isFinallyDead() {
        return finallyDead;
    }

    /**
     * Method setFinallyDead
     * Description: Method sets whether the Zombie is finally dead
     * @param finallyDead takes the value of whether the Zombie is finally dead or not
     */
    public void setFinallyDead(boolean finallyDead) {
        this.finallyDead = finallyDead;
    }
}