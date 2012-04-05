package framework;

import java.util.ArrayList;
import gui.GamePanel;

public abstract class Game {
    protected ArrayList<Player> listOfPlayers;
    protected int depthToSelectState;
    protected int[] firsts;
    protected Board board;
    protected GamePanel gamePanel;

    public Game(Board board) {
        listOfPlayers = new ArrayList<Player>();
        this.board = board;
        saveStateOfGame();
    }

    public void addPlayer(Player player) {
        if(player instanceof HumanPlayer) {
            gamePanel.addListener((HumanPlayer) player);
        }
        listOfPlayers.add(player);
    }

    public abstract void piecesDistribution();

    public abstract int getNbMinPlayer();

    public abstract int getNbMaxPlayer();

    public ArrayList<Player> getListOfPlayer() {
        return listOfPlayers;
    }

    public abstract Player nextPlayer();

    public abstract void play(Move move) throws MoveException;

    public abstract void removeMove(Move move);

    public abstract Move completeMove(Move move);

    public abstract ArrayList<Move> getListOfPossibleMoves();

    public void setFirstMovesOfPossibleMoves(int[] firsts) {
        if(this.firsts != null) {
            int minLength = (firsts.length < this.firsts.length)?
                            firsts.length: this.firsts.length;
            for(int i = 0; i < minLength; i++) {
                if(firsts[i] == 0) {
                    firsts[i] = this.firsts[i];
                }
                else if(firsts[i] <= this.firsts[i]) {
                    firsts[i]--;
                }
            }
        }
        this.firsts = firsts;
        this.depthToSelectState = 0;
    }

    public void resetFirstMovesOfPossibleMove() {
        firsts = null;
        this.depthToSelectState = 0;
    }

    public abstract MoveIterator getPossibleMoves();

    public abstract MoveIterator getPossibleOrderedMoves();

    public abstract boolean isPossibleMove(Move move);

    public abstract boolean isFinish();

    public abstract boolean isVictory();

    public abstract void saveStateOfGame();

    public void loadSavedState() {
        depthToSelectState = 0;
    }

    public abstract Player getWinner();

    public Board getBoard() {
        return board;
    }

    public abstract GamePanel getPanel();

    public abstract Game clone();
}
