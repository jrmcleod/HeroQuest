/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import Weapons.Dagger;
import Weapons.Weapon;

/**
 *
 * @author JeremyMcLeod
 */
public class Wizard extends AbstractCharacter {
    public Wizard() {
        Weapon dagger = new Dagger();
        setMaxHitPoints(4);
        setCurrentHitPoints(4);
        setMindPoints(6);
        setDefendPoints(2);
        setWeapon(dagger);
    }
    public String toString()    {
       String s = "Wizard Information: \n";
       s+= "He has " + getCurrentHitPoints() + " hitpoints out of " + getMaxHitPoints() + " hitpoints\n";
       s+= "He has a " + getWeapon() + " with attack power " + getWeaponPower() +"\n";
       s+= "He has " + getDefendPoints() + " defense dice and " + getMindPoints() + " mind points\n";
       return s;
    }
    public static void main(String args[])  {
        Wizard wizard = new Wizard();
        System.out.println("The current HP for the wizard is " + wizard.getCurrentHitPoints());
        System.out.println("The max HP for the wizard is " + wizard.getMaxHitPoints());
        System.out.println("The defence dice for the wizard is " +wizard.getDefendPoints());
        System.out.println("The mind points of the wizard is " + wizard.getMindPoints());
        System.out.println("The power of your current weapon is " + wizard.getWeaponPower());
    }
    
}
