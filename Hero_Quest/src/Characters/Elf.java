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
public class Elf extends AbstractCharacter {
    public Elf()    {
        Weapon shortsword = new Shortsword();
        setMaxHitPoints(6);
        setCurrentHitPoints(6);
        setMindPoints(4);
        setDefendPoints(2);
        setWeapon(shortsword);
    }
    public String toString()    {
       String s = "Elf Information: \n";
       s+= "He has " + getCurrentHitPoints() + " hitpoints out of " + getMaxHitPoints() + " hitpoints\n";
       s+= "He has a " + getWeapon() + " with attack power " + getWeaponPower() +"\n";
       s+= "He has " + getDefendPoints() + " defense dice and " + getMindPoints() + " mind points\n";
       return s;
    }
     public static void main(String args[])  {
        Elf elf = new Elf();
        System.out.println("The current HP for the elf is " + elf.getCurrentHitPoints());
        System.out.println("The max HP for the elf is " + elf.getMaxHitPoints());
        System.out.println("The defence dice for the elf is " + elf.getDefendPoints());
        System.out.println("The mind points of the elf is " + elf.getMindPoints());
        System.out.println("The power of your current weapon is " + elf.getWeaponPower());
    }
}
