package connectFour;

import framework.GameMemento;
import framework.Board;
import framework.Player;

import java.util.ArrayList;

public class CFMemento extends GameMemento {
    private Board gameBoard;
    private int player;
    private CFMove[] setMoves;

    public void setBoardState(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCurrentPlayerState(int player) {
        this.player = player;
    }

    public void setLastMove(ArrayList<CFMove> lastMoves) {
        setMoves = new CFMove[lastMoves.size()];
        for(int i = 0; i < lastMoves.size(); i++) {
            setMoves[i] = lastMoves.get(i);
        }
    }

    public Board getBoardSavedState() {
        return gameBoard;
    }

    public int getCurrentPlayerSavedState() {
        return player;
    }

    public ArrayList<CFMove> getLastMove() {
        ArrayList<CFMove> array = new ArrayList<CFMove>();
        for(int i = 0; i < setMoves.length; i++) {
            array.add(setMoves[i]);
        }
        return array;
    }
}
