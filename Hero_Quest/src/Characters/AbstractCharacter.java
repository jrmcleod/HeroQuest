/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

import Weapons.Weapon;
import java.util.ArrayList;

/**
 * The class from which the characters values and actions are placed
 * @author JeremyMcLeod
 */
public abstract class AbstractCharacter {
    private int maxHitPoints;
    private int currentHitpoints;
    private int mindPoints;
    private int defendPoints;
    private Weapon weapon;
    private int count;
    private int diceResult;
    private ArrayList<Integer> movementDice;
    private int xLoc;
    private int yLoc;
    /**
     * This just rolls two dice and stores the values
     * @return An ArrayList of Integer for each of the two dice rolled
     */
    public ArrayList<Integer> rollMovementDice()   {
        this.movementDice = new ArrayList<Integer>();
        this.movementDice.clear();
        this.movementDice.add((int) (Math.random()*6+1));
        //System.out.println(this.movementDice.get(0));
        this.movementDice.add((int) (Math.random()*6+1));
        //System.out.println(this.movementDice.get(1));
        return this.movementDice;
    }
    /**
     * This returns the dice rolls in movementDice
     * @return An ArrayList of Integer that holds the dice values
     */
    public ArrayList<Integer> getMovementDice()    {
        return this.movementDice;
    }
    /**
     * This rolls x attack dice depending on the weapon the hero is rolling and determines damage
     * @return count The damage value of the hero's current attack
     */
    public int attack()   {
        int attackDice = weapon.getWeaponPower();
        count = 0;
        for(int i = 0; i < attackDice; i++) {
            diceResult = (int) (Math.random()*6+1);
            //System.out.println(diceResult);
            if(diceResult < 4)  {
                count++;
            }
        }
        return count;
    }
    /**
     * This rolls two dice for defense on the hero and determines how many points of damage are blocked by the attacker
     * @return count The amount of damage points the hero blocked
     */
    public int defend() {
        count = 0;
        for(int i = 0; i < 2; i++) {
            diceResult = (int) (Math.random()*6+1);
            if(diceResult == 4 || diceResult == 5)  {
                count++;
            }
        }
        return count;
    }   
    /**
     * This sets the xLoc of the hero on the game board
     * @param x the x location of the Hero on the game board
     */
    public void setXLoc(int x)  {
        this.xLoc = x;
    }
    /**
     * 
     * @return xLoc The x value of the hero on the game board
     */
    public int getXLoc()    {
        return this.xLoc;
    }
    /**
     * This sets the y location of the hero on the game board
     * @param y the y location of the Hero on the game board
     */
    public void setYLoc(int y)  {
        this.yLoc = y;
    }
    /**
     * This gets the y location of the hero on the game board
     * @return yLoc The y value of the hero on the game board
     */
    public int getYLoc()    {
        return this.yLoc;
    }
    /**
     * This sets the maximum hit points of the Hero
     * @param maxHP The maximum hit points of the hero allowed
     */
    public void setMaxHitPoints(int maxHP)   {
        this.maxHitPoints = maxHP;
    }
    /**
     * This gets the maximum hit points of the Hero
     * @return maxHitPoints The maximum hit points of the hero
     */
    public int getMaxHitPoints()    {
        return this.maxHitPoints;
    }
    /**
     * This sets the current hit points of the Hero
     * @param currentHP The current hit points of the Hero
     */
    public void setCurrentHitPoints(int currentHP)  {
        this.currentHitpoints = currentHP;
    }
    /**
     * This gets the current hit points of the Hero
     * @return currentHitPoints The current hit points of the hero
     */
    public int getCurrentHitPoints()    {
        return this.currentHitpoints;
    }
    /**
     * This sets the mind points of the Hero
     * @param mind The mind points of the Hero 
     */
    public void setMindPoints(int mind) {
        this.mindPoints = mind;
    }
    /**
     * This gets the mind points of the hero
     * @return mindPoints The amount of mind points the Hero has
     */
    public int getMindPoints()  {
        return this.mindPoints;
    }
    /**
     * This sets the defense points of the Hero
     * @param defend The amount of defense points the Hero has
     */
    public void setDefendPoints(int defend) {
        this.defendPoints = defend;
    }
    /**
     * This gets the defense points of the Hero
     * @return defendPoints The amount of defense points the hero has
     */
    public int getDefendPoints()    {
        return this.defendPoints;
    }
    /**
     * This sets the weapon of the Hero
     * @param weapon The weapon to be equipped to the hero 
     */
    public void setWeapon(Weapon weapon)    {
        this.weapon = weapon;
    }
    /**
     * This gets the weapon the hero is currently wielding
     * @return weapon The weapon the Hero is currently using
     */
    public Weapon getWeapon()   {
        return this.weapon;
    }
    /**
     * This gets the weapon's attack power
     * @return weapon.getWeaponPower The value of the weapon's attack power
     */
    public int getWeaponPower() {
        return this.weapon.getWeaponPower();
    }
}
