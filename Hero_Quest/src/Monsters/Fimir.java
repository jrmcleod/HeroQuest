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
public class Fimir extends AbstractMonster {
    public Fimir()  {
        setMaxHitPoints(2);
        setCurrentHitPoints(2);
        setMindPoints(3);
        setDefendPoints(3);
        setAttackPoints(3);
        setMovementPoints(6);
        setActivated(false);
    }
    public String toString()    {
        return "A Fimir";
    }
}
