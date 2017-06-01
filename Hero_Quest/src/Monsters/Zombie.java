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
public class Zombie extends AbstractMonster {
    public Zombie() {
        setMaxHitPoints(1);
        setCurrentHitPoints(1);
        setMindPoints(0);
        setDefendPoints(3);
        setAttackPoints(2);
        setMovementPoints(5);
        setActivated(false);
    }
    public String toString()    {
        return "A Zombie";
    }
}
