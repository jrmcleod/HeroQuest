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
public class Gargoyle extends AbstractMonster {
    public Gargoyle()   {
        setMaxHitPoints(3);
        setCurrentHitPoints(3);
        setMindPoints(4);
        setDefendPoints(5);
        setAttackPoints(4);
        setMovementPoints(6);
        setActivated(false);
    }
    public String toString()    {
        return "A Gargoyle";
    }
}
