package framework;

import java.util.ArrayList;
import gui.GamePanel;

public abstract class Game {
    protected ArrayList<Player> listOfPlayers;
    protected Board board;
    protected GamePanel gamePanel;

    public Game(Board board) {
        listOfPlayers = new ArrayList<Player>();
        this.board = board;
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

    public abstract void play(Move move);

    public abstract void removeMove(Move move);

    public abstract ArrayList<Move> getListOfPossibleMove();

    public abstract boolean isFinish();

    public abstract boolean isVictory();

    public abstract Player getWinner();

    public Board getBoard() {
        return board;
    }

    public abstract GamePanel getPanel();

    public abstract Game clone();
}
