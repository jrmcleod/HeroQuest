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
public class Goblin extends AbstractMonster {
   public Goblin()  {
        setMaxHitPoints(1);
        setCurrentHitPoints(1);
        setMindPoints(1);
        setDefendPoints(1);
        setAttackPoints(2);
        setMovementPoints(10);
        setActivated(false);
   }
   public String toString()    {
        return "A Goblin";
    }
}
