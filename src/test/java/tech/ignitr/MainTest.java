package tech.ignitr;

import org.junit.jupiter.api.Test;
import tech.ignitr.model.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @version 1.1
 * @since 01.07.2020 - Abschluss der Entwicklung
 * 05.07.2020 - finaler Check + Abschluss der Dokumentation
 * @author Lennard Zündorf, E-Mail: s0568383@htw-berlin.de
 * Beschreibung: Die Klasse DemoData ist zum Testen der Klassen & Methoden Zombie, SuperZombie, Vicitim, HumanVicitm und ZombieHunter entworfen
 */
class MainTest {

    @Test
    void objectCreationTest() {
        SuperZombie Connor = new SuperZombie("Connor");
        assertEquals("Connor", Connor.getName());

        Zombie Linda = new Zombie ("Linda");
        assertEquals("Linda", Linda.getName());

        Zombie Silvia = new Zombie ("Silvia");
        assertFalse(Silvia.isFinallyDead());

        Zombie Pete = new Zombie ("Pete");
        assertEquals(10, Pete.getEnergy());

        ZombieHunter Belmont = new ZombieHunter ("Belmont");
        assertEquals("Belmont", Belmont.getName());
        assertTrue(Belmont.alive());

        Victim Spider = new Victim ("Spider");
        assertTrue(Spider.isAlive());

        HumanVictim Mike = new HumanVictim ("Mike");
        assertEquals("Human", Mike.getType());
    }

    @Test
    void setupActionTest(){
        SuperZombie Connor = new SuperZombie("Connor");
        Zombie Linda = new Zombie ("Linda");
        Zombie Silvia = new Zombie ("Silvia");
        Zombie Pete = new Zombie ("Pete");
        ZombieHunter Belmont = new ZombieHunter ("Belmont");
        Victim Spider = new Victim ("Spider");
        HumanVictim Mike = new HumanVictim ("Mike");

        Connor.addToGroup(Pete);
        Connor.addToGroup(Silvia);
        Connor.addToGroup(Linda);
        System.out.println(Connor.ShowGroupNames());
        assertTrue(Connor.ShowGroupNames().contains("Linda"));

        Connor.deleteFromGroup(Pete);
        assertFalse(Connor.ShowGroupNames().contains("Pete"));
    }
}