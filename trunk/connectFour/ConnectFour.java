package connectFour;

import java.util.ArrayList;
import framework.*;

public class ConnectFour extends Game {
    private static final int NB_PLAYERS = 2;
    private int currentPlayer;

    public ConnectFour() {
        super(new BoardCF());
        currentPlayer = 0;
    }

    public void piecesDistribution() {
        PieceCF[] pieces = {new PieceCF("yellow", 0),
                            new PieceCF("red", 1)};

        for(int i = 0; i < listOfPlayers.size(); i++) {
            listOfPlayers.get(i).setPiece(pieces[i]);
        }
    }

    public int getNbMinPlayer() {
        return NB_PLAYERS;
    }

    public int getNbMaxPlayer() {
        return NB_PLAYERS;
    }

    public Player nextPlayer() {
        return listOfPlayers.get(currentPlayer);
    }

    public void play(Move move) {
        MoveCF moveCF = (MoveCF) move;
        board.placePiece(moveCF.getPosition(), moveCF.getPiece());
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public void removeMove(Move move) {
        board.removePiece(((MoveCF) move).getPosition());
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public ArrayList<Move> getListOfPossibleMove() {
        ArrayList<Move> listOfPossibleMove = new ArrayList<Move>();
        PieceCF piece = (PieceCF) nextPlayer().getPiece();

        for(int i = 0; i < ((BoardCF) board).getWidth(); i++) {
            if(board.isFree(new PositionCF(i))) {
                listOfPossibleMove.add(new MoveCF(new PositionCF(i), piece));
            }
        }

        return listOfPossibleMove;
    }

    public boolean isVictory() {
        return false;
    }

    public Player getWinner() {
        return listOfPlayers.get((currentPlayer + 1) % 2);
    }

    public boolean isFinish() {
        boolean isFinish = true;

        for(int i = 0; i < ((BoardCF) board).getWidth(); i++) {
            if(board.isFree(new PositionCF(i))) {
                isFinish = false;
                i = ((BoardCF) board).getWidth();
            }
        }

        return isFinish || isVictory();
    }

    public Game clone() {
        ConnectFour gameCopy = new ConnectFour();
        gameCopy.board = board.clone();
        gameCopy.currentPlayer = currentPlayer;

        for(int i = 0; i < listOfPlayers.size(); i++) {
            gameCopy.listOfPlayers.add(listOfPlayers.get(i).clone());
        }

        return gameCopy;
    }

    public String toString() {
        return board.toString();
    }
}
