/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Monsters;




/**
 *
 * @author JeremyMcLeod
 */
public class AbstractMonster {
    private int maxHitpoints;
    private int currentHitpoints;
    private int mindPoints;
    private int defendPoints;
    private int attackPoints;
    private int movementPoints;
    private int count;
    private int diceResult;
    private int xLoc;
    private int yLoc;
    private boolean activate;

    public int attack()   {
        count = 0;
        for(int i = 0; i < attackPoints; i++) {
            diceResult = (int) (Math.random()*6+1);
            //System.out.println(diceResult);
            if(diceResult < 4)  {
                count++;
            }
        }
        return count;
    }
    public int defend() {
        count = 0;
        for(int i = 0; i < defendPoints; i++) {
            diceResult = (int) (Math.random()*6+1);
            if(diceResult == 6)  {
                count++;
            }
        }
        return count;
    }
    public void setXLoc(int x)  {
        this.xLoc = x;
    }
    public int getXLoc()    {
        return this.xLoc;
    }
    public void setYLoc(int y)  {
        this.yLoc = y;
    }
    public int getYLoc()    {
        return this.yLoc;
    }
    public void setAttackPoints(int attackPoints)    {
        this.attackPoints = attackPoints;
    }
    public int getAttackPoints()    {
        return this.attackPoints;
    }
    public void setMovementPoints(int movementPoints)    {
        this.movementPoints = movementPoints;
    }
    public int getMovementPoints()  {
        return this.movementPoints;
    }
    public void setMaxHitPoints(int maxHP)   {
        this.maxHitpoints = maxHP;
    }
    public int getMaxHitPoints()    {
        return this.maxHitpoints;
    }
    public void setCurrentHitPoints(int currentHP)  {
        this.currentHitpoints = currentHP;
    }
    public int getCurrentHitPoints()    {
        return this.currentHitpoints;
    }
    public void setMindPoints(int mind) {
        this.mindPoints = mind;
    }
    public int getMindPoints()  {
        return this.mindPoints;
    }
    public void setDefendPoints(int defend) {
        this.defendPoints = defend;
    }
    public int getDefendPoints()    {
        return this.defendPoints;
    }
    public void setActivated(boolean flag)   {
        this.activate = flag;
    }
    public boolean isActivated()    {
        if(this.activate)   {
            return true;
        }
        return false;
    }
    
}
