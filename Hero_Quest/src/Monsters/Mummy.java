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
public class Mummy extends AbstractMonster {
    public Mummy()  {
        setMaxHitPoints(2);
        setCurrentHitPoints(2);
        setMindPoints(0);
        setDefendPoints(4);
        setAttackPoints(3);
        setMovementPoints(3);
        setActivated(false);
    }
    public String toString()    {
        return "A Mummy";
    }
}
