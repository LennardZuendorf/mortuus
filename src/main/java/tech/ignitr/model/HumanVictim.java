package tech.ignitr.model;

/**
 * @version 2.0
 * @author Prof. Dr. Verena Majuntke and Lennard Zündorf
 * Description:
 */
public class HumanVictim extends Victim {
    private String Name;

    /**
     * Method HumanVictim
     * Description: Constructor of the class HumanVictim, which is a subclass of Victim
     * @param Name name of the human victim
     */
    public HumanVictim (String Name) {
        super("Human");
        this.setIntelligence((int)(Math.random()*70)+70);
        this.setName(Name);
    }

    /**
     * Method flee
     * Description: Implements the flee method of the Victim class, but with a different output, because the HumanVictim has a name
     */
    public Boolean flee() {
        if((int)(Math.random() * 5)+ 1<=2) {
            System.out.println("The escape attempt was successful!");
            return true;
        }else {
            System.out.println("The escape attempt failed!");
            return false;
        }
    }

    /**
     * Method takeDamage
     * Description: Implements the takeDamage method of the Victim class, but with a different output, because the HumanVictim has a name
     */
    public void takeDamage(int Damage) {
        if (Damage<this.getHealthPointsCurrent()) {
            this.setHealthPointsCurrent(this.getHealthPointsCurrent()-Damage);
            System.out.println(this.getName()+" has taken "+Damage+" points of damage. It has "+this.getHealthPointsCurrent()+" health points remaining!");
        } else if (Damage>=this.getHealthPointsCurrent()) {//tödlicher Schaden
            System.out.println(this.getName()+" has taken "+this.getHealthPointsCurrent()+" points of damage. It has "+this.getHealthPointsCurrent()+" health points remaining!");
            this.setHealthPointsCurrent(0);
            this.setAlive(false);
        }
    }

    /**
     * Methode getName
     * Description: returns the name of the human victim
     * @return name of the human victim
     */
    public String getName() {
        return Name;
    }

    /**
     * Methode setName
     * Description: sets the name of the human victim
     * @param name name of the human victim
     */
    public void setName(String name) {
        Name = name;
    }
}