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
public class Skeleton extends AbstractMonster {
    public Skeleton()   {
        setMaxHitPoints(1);
        setCurrentHitPoints(1);
        setMindPoints(0);
        setDefendPoints(2);
        setAttackPoints(2);
        setMovementPoints(6);
        setActivated(false);
    }
    public String toString()    {
        return "A Skeleton";
    }
}
