/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import Weapons.Shortsword;
import Weapons.Weapon;

/**
 *
 * @author JeremyMcLeod
 */
public class Dwarf extends AbstractCharacter {
    public Dwarf()  {
        Weapon shortsword = new Shortsword();
        setMaxHitPoints(7);
        setCurrentHitPoints(7);
        setMindPoints(3);
        setDefendPoints(2);
        setWeapon(shortsword);
    }
    public String toString()    {
       String s = "Dwarf Information: \n";
       s+= "He has " + getCurrentHitPoints() + " hitpoints out of " + getMaxHitPoints() + " hitpoints\n";
       s+= "He has a " + getWeapon() + " with attack power " + getWeaponPower() +"\n";
       s+= "He has " + getDefendPoints() + " defense dice and " + getMindPoints() + " mind points\n";
       return s;
    }
    public static void main(String args[])  {
        Dwarf dwarf = new Dwarf();
        System.out.println("The current HP for the dwarf is " + dwarf.getCurrentHitPoints());
        System.out.println("The max HP for the dwarf is " + dwarf.getMaxHitPoints());
        System.out.println("The defence dice for the dwarf is " + dwarf.getDefendPoints());
        System.out.println("The mind points of the dwarf is " + dwarf.getMindPoints());
        System.out.println("The power of your current weapon is " + dwarf.getWeaponPower());
    }
}
