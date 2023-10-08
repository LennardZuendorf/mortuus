package tech.ignitr.model;

import java.util.UUID;


/**
 * @version 2.0
 * @author Prof. Dr. Verena Majuntke and Lennard Zündorf
 * Description:
 */
public class ZombieHunter {

    private String id;
    String name;
    private int experiencePoints;
    private int healthPointsCurrent;

    /**
     * Method ZombieHunter
     * Description: Constructor of the class ZombieHunter
     * @param name takes the name of the ZombieHunter
     */
    public ZombieHunter(String name){
        this.setId(UUID.randomUUID().toString());
        this.name=name;
        this.setExperiencePoints(0);
        this.healthPointsCurrent=1000;
    }

    /**
     * Method attack
     * Description: The method executes an attack of the ZombieHunter on a Zombie
     * @param Target takes the target of the attack
     */
    public void attack(Zombie Target) {
        if((int)(Math.random() *2)+1==1) {
            System.out.println("The ZombieHunter "+this.getName()+" has successfully attacked!");
            Target.takeDamage(5);
        }else {
            System.out.println("The ZombieHunter "+this.getName()+" has missed!");
        }
    }

    /**
     * Method attackSuperZombie
     * Description: The method executes an attack of the ZombieHunter on a SuperZombie
     * @param Target takes the target of the attack
     */
    public void attackSuperZombie(SuperZombie Target) {
        if((int)(Math.random() *2)+1==1) {
            System.out.println("The ZombieHunter "+this.getName()+" has successfully attacked!");
            Target.takeDamage(5);
        }else {
            System.out.println("The ZombieHunter "+this.getName()+" has missed!");
        }
    }

    /**
     * Method takeDamage
     * Description: The method adds the damage to the ZombieHunter
     * @param Damage takes the damage, which is inflicted by the attacker
     */
    public  void takeDamage(int Damage) {
        this.healthPointsCurrent=this.healthPointsCurrent-Damage;
        System.out.println("The ZombieHunter "+this.getName()+" has taken "+Damage+" points of damage. It has "+this.getHealthPointsCurrent()+" health points remaining!");
    }

    /**
     * Methode flee
     * Description: The method prints out that the ZombieHunter does not flee
     */
    public void flee() {
        System.out.println("Ein ZombieHunter flieht nicht!");
    }

    /**
     * Method alive
     * Description: The method checks if the ZombieHunter is alive
     * @return returns true, if the ZombieHunter is alive, or false, if the ZombieHunter is dead
     */
    public boolean alive() {
        return this.healthPointsCurrent > 0;
    }

    /**
     * Method getId
     * Description: The method returns the ID of the ZombieHunter
     * @return returns the ID of the ZombieHunter
     */
    public String getId() {
        return id;
    }

    /**
     * Method setId
     * Description: The method sets the ID of the ZombieHunter
     * @param id takes the ID of the ZombieHunter
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method getName
     * Description: The method returns the name of the ZombieHunter
     * @return returns the name of the ZombieHunter
     */
    public String getName() {
        return name;
    }

    /**
     * Method getExperiencePoints
     * Description: The method returns the experience of the ZombieHunter
     * @return returns the experience of the ZombieHunter
     */
    public int getExperiencePoints() {
        return experiencePoints;
    }

    /**
     * Method setExperiencePoints
     * Description: The method sets the experience of the ZombieHunter
     * @param experiencePoints takes the experience of the ZombieHunter
     */
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    /**
     * Method getHealthPointsCurrent
     * Description: The method returns the current health points of the ZombieHunter
     * @return returns the current health points of the ZombieHunter
     */
    public int getHealthPointsCurrent() {
        return healthPointsCurrent;
    }

    /**
     * Method setHealthPointsCurrent
     * Description: The method sets the current health points of the ZombieHunter
     * @param HealthPoints takes the current health points of the ZombieHunter
     */
    public void setHealthPointsCurrent(int HealthPoints) {
        this.healthPointsCurrent = HealthPoints;
    }


}
