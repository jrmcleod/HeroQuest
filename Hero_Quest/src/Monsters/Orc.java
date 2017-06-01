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
public class Orc extends AbstractMonster {
    public Orc()    {
        setMaxHitPoints(1);
        setCurrentHitPoints(1);
        setMindPoints(2);
        setDefendPoints(1);
        setAttackPoints(3);
        setMovementPoints(8);
        setActivated(false);
    }
    public String toString()    {
        return "An Orc";
    }
}
