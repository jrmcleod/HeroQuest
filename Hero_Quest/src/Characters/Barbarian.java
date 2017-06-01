/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import Weapons.Broadsword;
import Weapons.Weapon;

/**
 * @author JeremyMcLeod
 */
public class Barbarian extends AbstractCharacter {
    public Barbarian()  {
        Weapon broadsword = new Broadsword();
        setMaxHitPoints(8);
        setCurrentHitPoints(8);
        setMindPoints(2);
        setDefendPoints(2);
        setWeapon(broadsword);
    }
    /**
     * This shows the string representation of the Barbarian
     * @return s The String representation of the Barbarian
     */
    public String toString()    {
       String s = "Barbarian Information: \n";
       s+= "He has " + getCurrentHitPoints() + " hitpoints out of " + getMaxHitPoints() + " hitpoints\n";
       s+= "He has a " + getWeapon() + " with attack power " + getWeaponPower() +"\n";
       s+= "He has " + getDefendPoints() + " defense dice and " + getMindPoints() + " mind points\n";
       return s;
    }
    public static void main(String args[])  {
        Barbarian barbarian = new Barbarian();
        System.out.println("The current HP for the barbarian is " + barbarian.getCurrentHitPoints());
        System.out.println("The max HP for the barbarian is " + barbarian.getMaxHitPoints());
        System.out.println("The defence dice for the barbarian is " + barbarian.getDefendPoints());
        System.out.println("The mind points of the barbarian is " + barbarian.getMindPoints());
        System.out.println("The power of your current weapon is " + barbarian.getWeaponPower());
        
        System.out.println("Testing how many hits we get through an attack... " + barbarian.attack());
        System.out.println("Testing movement..." + barbarian.rollMovementDice());
    }
}
