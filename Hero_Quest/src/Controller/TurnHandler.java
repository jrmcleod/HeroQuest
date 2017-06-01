package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * An object used to keep track of turns for a game
 * @author grmallon
 * @author jrmcleod
 */
public class TurnHandler {
    private final int PLAYER_ONE = 0;
    private final int PLAYER_TWO = 1;
    private final int PLAYER_THREE = 2;
    private final int PLAYER_FOUR = 3;
    //private final int PLAYER_FIVE = 4;
    private int currentPlayer;
    /**
     * No-arg empty constructor
     */
    public TurnHandler(){
        
    }
    /**
     * Creates a TurnHandler and takes a parameter allowing the initial player to be set.
     * @param currentPlayer Player to go first(0 or 1)
     */
    public TurnHandler(int currentPlayer){
        this.currentPlayer = currentPlayer;
    }
    
    /**
     * Switch who the active player is
     */
    public void switchSides(){
        if(currentPlayer == PLAYER_ONE){
            currentPlayer = PLAYER_TWO;
        }
        else if(currentPlayer == PLAYER_TWO) {
            currentPlayer = PLAYER_THREE;
        }
        else if(currentPlayer == PLAYER_THREE) {
            currentPlayer = PLAYER_FOUR;
        }
        else    {
            currentPlayer = PLAYER_ONE;
        }
    }
    
    public int getSleepingPlayer(){
        if(currentPlayer == PLAYER_ONE){
            return PLAYER_TWO;
        }else{
            return PLAYER_ONE;
        }
    }
    
    /**
     * Accessor for PLAYER_ONE
     * @return int value of PLAYER_ONE
     */
    public int getPLAYER_ONE() {
        return PLAYER_ONE;
    }
    
    /**
     * Accessor for PLAYER_TWO
     * @return int value of PLAYER_TWO
     */
    public int getPLAYER_TWO() {
        return PLAYER_TWO;
    }
    
    /**
     * Accessor for the currentPlayer
     * @return int value of the currentPlayer
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    /**
     * Set the value of the currentPlayer
     * @param player int value of player to set to
     */
    public void setCurrentPlayer(int player){
        this.currentPlayer = player;
    }
}
