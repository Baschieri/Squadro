package iia.simpleGame.squadro;

import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IGame;

import java.util.ArrayList;

public abstract class ASquadroGame extends AGame {
    
    /*
    We would model a game board using two vectors containing an object with two value:
        - an integer from 0 that represent the position of the n-th pawn in the table.
        - a boolean that represent the direction of travel
    We would also use two counters for memorizing the number of pawns which return to the start 
    and a vector that stores how many steps each pawn can take.
    */
    
    ArrayList<PawnPosition> pawnPositionsH;
    Integer counterH;

    ArrayList<PawnPosition> pawnPositionsV;
    Integer counterV;
    
    int stepsNumber[] = {3,1,2,1,3};
    
    public ASquadroGame(){
        // TODO !
    }

    @Override
    public IGame play(String move, String role) {
        // TODO !
        return null;
    }
    

    @Override
    public ArrayList<String> possibleMoves(String role) {
        // TODO !
        return null;
    }

    @Override
    public boolean isValidMove(String move, String role) {
        // TODO !
        return false;
    }

    @Override
    public boolean isGameOver() {
        // TODO !
        return false;
    }
    
    public void setFromFile(String fileName)
    {
    
    }
    
    public void saveToFile(String fileName)
    {
    
    }
}
