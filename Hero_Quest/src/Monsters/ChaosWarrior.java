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
public class ChaosWarrior extends AbstractMonster{
    public ChaosWarrior()   {
        setMaxHitPoints(3);
        setCurrentHitPoints(3);
        setMindPoints(3);
        setDefendPoints(4);
        setAttackPoints(4);
        setMovementPoints(7);
        setActivated(false);
    }
    public String toString()    {
        return "A chaos Warrior";
    }
}
