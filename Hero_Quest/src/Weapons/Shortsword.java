/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapons;

/**
 *
 * @author JeremyMcLeod
 */
public class Shortsword implements Weapon{

    @Override
    public int getWeaponPower() {
       return 2;
    }
    public String toString()    {
        return "Shortsword";
    }
    
}
