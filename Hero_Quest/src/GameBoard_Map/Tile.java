/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameBoard_Map;

import Characters.AbstractCharacter;

/**
 *
 * @author JeremyMcLeod
 */
public class Tile {
    /* What do I need to have for a tile?
            -Collision (Where's the Wall?)
            -A field that takes in what is ON the tile (Furniture, character, etc
            -A method determining if the tile has an object on it
            -a visual representation
    */
    private Object value;
    private AbstractCharacter hero;
    public Tile()   {
       this.value = null;
       this.hero = null;
    }
    
    public boolean isAvailable(Tile tile)   {
       if(this.value == null) {
           return true;
       }
       return false;
    }
    public void setValue(Object value)  {
        
    }
    public void setHero(AbstractCharacter hero) {
        this.hero = hero;
    }
    public Object getTileValue()    {
        return this.value;
    }
    public AbstractCharacter getHero()  {
        return this.hero;
    }
    public String toString() {
       return "Just an empty tile!";
   }
    
}
