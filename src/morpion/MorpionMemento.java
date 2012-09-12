package morpion;

import framework.GameMemento;
import framework.Board;
import framework.Player;

public class MorpionMemento extends GameMemento {
    private Board gameBoard;
    private int player;

    public void setBoardState(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setCurrentPlayerState(int player) {
        this.player = player;
    }

    public Board getBoardSavedState() {
        return gameBoard;
    }

    public int getCurrentPlayerSavedState() {
        return player;
    }
}
