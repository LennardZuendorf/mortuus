package tech.ignitr.model;

import java.util.Objects;

/**
 * @version 2.0
 * @author Prof. Dr. Verena Majuntke and Lennard Zündorf
 * Description:
 */
public class SuperZombie extends Zombie {

    public Zombie[] ZombieGroup = new Zombie[25];
    private int zombiePower = 0;

    /**
     * Method SuperZombie
     * Description: Constructor of the class SuperZombie, which is a subclass of Zombie
     * @param Name name of the SuperZombie
     */
    public SuperZombie(String Name) {
        super(Name);
        this.setIntelligence(130);
    }


    /**
     * Method commandToAttack
     * Description: The method commands the SuperZombie to attack the target and executes the attack method of the Zombie class
     * @param Attacker is the SuperZombie, which is commanded to attack
     * @param Target is the victim, which is attacked
     */
    public void commandToAttack (Zombie Attacker, Victim Target) {
        for (Zombie zombie : ZombieGroup) {
            if (zombie == Attacker) {
                zombie.attack(Target);
            }
        }
    }

    /**
     * Method commandToAttack
     * Description: The method commands the SuperZombie to attack the target and executes the attack method of the Zombie class
     * @param Attacker is the SuperZombie, which is commanded to attack
     * @param Target is the victim, which is attacked
     */
    public void commandToAttack (Zombie Attacker, HumanVictim Target) {
        for (Zombie zombie : ZombieGroup) {
            if (zombie == Attacker) {
                zombie.attack(Target);
            }
        }
    }

    /**
     * Method commandToAttack
     * Description: The method commands the SuperZombie to attack the target and executes the attack method of the Zombie class
     * @param Target is the victim, which is attacked
     */
    public void commandToAttack (ZombieHunter Target) {
        for (Zombie zombie : ZombieGroup) {
            if (zombie != null) {
                zombie.attackHunter(Target);
            }
        }
    }

    /**
     * Method attack
     * Description: The method takes the target of the attack and executes an attack of the SuperZombie, analogous to the attack method of the Zombie class (the damage is different)
     * @param Target takes the target of the attack, to call the defend method of the target
     */
    public void attack (Victim Target) {
        System.out.println("\nThe SuperZombie "+ this.getName()+" attacks.");
        if((int)(Math.random() * 10)+1==1) {
            Target.defend((int)(Math.random()*1)*2);
        } else {
            Target.defend((int)(Math.random()*1));
        }
    }

    /**
     * Method attackHuman
     * Description: The method takes the target of the attack and executes an attack of the SuperZombie, analogous to the attack method of the Zombie class (the damage is different)
     * @param Target takes the target of the attack, to call the defend method of the target
     */
    public void attackHuman (HumanVictim Target) {
        System.out.println("\nThe SuperZombie "+ this.getName()+" attacks.");
        if((int)(Math.random() * 10)+1==1) {
            Target.defend((int)(Math.random()*1)*2);
        } else {
            Target.defend((int)(Math.random()*1));
        }
    }
    /**
     * Method attack
     * Description: The method takes the target of the attack and executes an attack of the SuperZombie, analogous to the attack method of the Zombie class (the damage is different)
     * @param Target takes the target of the attack, to call the defend method of the target
     */
    public void attackHunter (ZombieHunter Target) {
        System.out.println("\nThe SuperZombie "+ this.getName()+" attacks");
        if((int)(Math.random() * ((10) + 1))==1) {
            Target.takeDamage((int)(Math.random()*1)*2);
        } else {
            Target.takeDamage((int)(Math.random() *1));
        }
    }

    /**
     * Method flee
     * Description: The method lets the SuperZombie try to flee, analogous to the flee method of the Zombie class
     * @return returns true, if the SuperZombie was able to flee, or false, if the SuperZombie was not able to flee
     */
    public boolean flee () {
        if((int)(Math.random()*4)+1<=3) {
            System.out.println("The SuperZombie "+this.getName()+" was able to flee.");
            return true;//Ausgabe bei erfolgreichem Fluchtversuch
        }else {
            System.out.println("The SuperZombie "+this.getName()+" was not able to flee.");
            return false;
        }
    }

    /**
     * Method dance
     * Description: The method lets the SuperZombie dance, analogous to the dance method of the Zombie class, but with a different output
     */
    public void dance () {
        System.out.println("\nThe SuperZombie "+this.getName()+" dances.");
        int Zufall = (int)(Math.random()*5)+1;
        if (Zufall == 1) {//Szenario 1
            System.out.println(this.getName()+" has lost his left leg while dancing!");
        }else if (Zufall == 2) {//Szenario 2
            System.out.println(this.getName()+" has lost his right leg while dancing!");
        } else if (Zufall == 3) {//Szenario 3
            System.out.println(this.getName()+" has lost his left arm while dancing!");
        } else if (Zufall ==4) {//Szenario 4
            System.out.println(this.getName()+" has lost his right arm while dancing!");
        }else {//Szenario 5
            System.out.println(this.getName()+" has lost his head while dancing!");
        }
        for (Zombie zombie : ZombieGroup) {
            if (zombie != null) {
                zombie.dance();
            }
        }
    }

    /**
     * Method addToGroup
     * Description: The method adds a Zombie to the ZombieGroup
     * @param Member takes the Zombie, which is added to the ZombieGroup
     */
    public void addToGroup (Zombie Member) {
        int c=0;
        for (int x=0;x<ZombieGroup.length;x++){
            if (c==0&&ZombieGroup[x]==null) {
                ZombieGroup[x]=Member;
                c++;
                zombiePower++;
            }
        }
        if (c==0) {
            System.out.println("The zombie "+Member.getName()+" could not be added to the group, because the group is full.");
        }
    }

    /**
     * Method deleteFromGroup
     * Description: The method removes a Zombie from the ZombieGroup
     * @param Member takes the Zombie, which is removed from the ZombieGroup
     */
    public void deleteFromGroup (Zombie Member) {
        for (int x=0;x<ZombieGroup.length;x++) {
            if (ZombieGroup[x]!=null) {
                if (Objects.equals(ZombieGroup[x].getName(), Member.getName())) {
                    ZombieGroup[x]=null;
                    zombiePower=zombiePower-1;
                    System.out.println("The zombie "+Member.getName()+" has been removed from the group.");
                }
            }
        }
    }

    /**
     * Method showGroupData
     * Description: The method returns the data sheet of all Zombies in the ZombieGroup
     */
    public void ShowGroupData() {
        for (Zombie zombie : ZombieGroup) {
            if (zombie != null) {
                zombie.showData();
            }
        }
    }

    /**
     * Method showGroupNames
     * Description: The method returns the names of all Zombies in the ZombieGroup
     * @return returns the names of all Zombies in the ZombieGroup as a String
     */
    public String ShowGroupNames() {
        int c=0;
        String GroupNames = "";
        for (Zombie zombie : ZombieGroup) {
            if (zombie != null) {
                GroupNames = GroupNames + zombie.getName() + ", ";
                c++;
            }
        }
        if (c!=0) {
            GroupNames=GroupNames.substring(0, GroupNames.length()-2);
            return GroupNames;
        }else {
            return "none";
        }
    }

    /**
     * Method showData
     * Description: The method returns the data sheet of the SuperZombie
     */
    public void ShowData() {
        System.out.println("\nName: "+this.getName()+"\nType: SuperZombie\nIntelligence: "+this.getIntelligence()+"\nPower: "+this.getPower()+"\nGroup: "+this.ShowGroupNames());
    }


    /**
     * Method getPower
     * Description: returns the power of the SuperZombie
     * @return power of the SuperZombie
     */
    public int getPower() {
        return this.zombiePower;
    }

    /**
     * Method setPower
     * Description: sets the power of the SuperZombie
     * @param Power power of the SuperZombie
     */
    public void setPower(int Power) {
        this.zombiePower=Power;
    }

    public Zombie[] getGroup(){
        return this.ZombieGroup;
    }
}