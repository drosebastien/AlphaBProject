package morpion;

import java.util.ArrayList;
import framework.*;
import morpion.gui.MorpionGamePanel;
import gui.GamePanel;

public class Morpion extends Game {
    private static final int NB_PLAYERS = 2;
    private int currentPlayer;
    private Board copyOfBoard;
    private int copyOfCurrentPlayer;

    public Morpion() {
        super(new MorpionBoard());
        gamePanel = new MorpionGamePanel(board);
        currentPlayer = 0;
    }

    public Morpion(MorpionBoard board) {
        super(board);
        gamePanel = new MorpionGamePanel(board);
        currentPlayer = 0;
    }

    public void piecesDistribution() {
        MorpionPiece[] pieces = {new MorpionPiece("Cross", 0),
                                 new MorpionPiece("Circle", 1)};

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
        MorpionMove morpionMove = (MorpionMove) move;
        board.placePiece(morpionMove.getPosition(), morpionMove.getPiece());
        currentPlayer = (currentPlayer + 1) % 2;
        //gamePanel.repaint();
    }

    public void removeMove(Move move) {
        board.removePiece(((MorpionMove) move).getPosition());
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public ArrayList<Move> getListOfPossibleMove() {
        ArrayList<Move> listOfPossibleMove = new ArrayList<Move>();
        MorpionPiece piece = (MorpionPiece) nextPlayer().getPiece();

        for(int i = 0; i < ((MorpionBoard) board).getWidth(); i++) {
            for(int j = 0; j < ((MorpionBoard) board).getWidth(); j++) {
                if(board.isFree(new MorpionPosition(i, j))) {
                    listOfPossibleMove.add(new MorpionMove(
                                    new MorpionPosition(i, j), piece));
                }
            }
        }

        return listOfPossibleMove;
    }

    public boolean isVictory() {
        MorpionPiece firstPiece = null;
        boolean isVictory = true;

        for(int i = 0; i < ((MorpionBoard) board).getWidth(); i++) {
            firstPiece = (MorpionPiece) board.getPiece(
                                    new MorpionPosition(i, 0));
            if(firstPiece != null) {
                isVictory = true;
                for(int j = 1; j < ((MorpionBoard) board).getWidth(); j++) {
                    MorpionPiece tmp = (MorpionPiece) board.getPiece(
                                    new MorpionPosition(i, j));
                    if(tmp == null || !tmp.equals(firstPiece)) {
                        j = ((MorpionBoard) board).getWidth();
                        isVictory = false;
                    }
                }
                if(isVictory) {
                    return true;
                }
            }
        }

        for(int i = 0; i < ((MorpionBoard) board).getWidth(); i++) {
            firstPiece = (MorpionPiece) board.getPiece(
                                    new MorpionPosition(0, i));
            if(firstPiece != null) {
                isVictory = true;
                for(int j = 1; j < ((MorpionBoard) board).getWidth(); j++) {
                    MorpionPiece tmp = (MorpionPiece) board.getPiece(
                                    new MorpionPosition(j, i));
                    if(tmp == null || !tmp.equals(firstPiece)) {
                        j = ((MorpionBoard) board).getWidth();
                        isVictory = false;
                    }
                }
                if(isVictory) {
                    return true;
                }
            }
        }

        firstPiece = (MorpionPiece) board.getPiece(new MorpionPosition(0, 0));
        if(firstPiece != null) {
            isVictory = true;
            for(int i = 1; i < ((MorpionBoard) board).getWidth(); i++) {
                MorpionPiece tmp = (MorpionPiece) board.getPiece(
                                    new MorpionPosition(i, i));
                if(tmp == null || !tmp.equals(firstPiece)) {
                    i = ((MorpionBoard) board).getWidth();
                    isVictory = false;
                }
            }
            if(isVictory) {
                return true;
            }
        }

        firstPiece = (MorpionPiece) board.getPiece(new MorpionPosition(0,
                                        ((MorpionBoard) board).getWidth() -1));
        if(firstPiece != null) {
            isVictory = true;
            for(int i = 1; i < ((MorpionBoard) board).getWidth(); i++) {
                MorpionPiece tmp = (MorpionPiece) board.getPiece(
                                    new MorpionPosition(i, 
                                    ((MorpionBoard) board).getWidth() - 1 - i));
                if(tmp == null || !tmp.equals(firstPiece)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    public Player getWinner() {
        return listOfPlayers.get((currentPlayer + 1) % 2);
    }

    public boolean isFinish() {
        boolean isFinish = true;

        for(int i = 0; i < ((MorpionBoard) board).getWidth(); i++) {
            for(int j = 0; j < ((MorpionBoard) board).getWidth(); j++) {
                if(board.getPiece(new MorpionPosition(i, j)) == null) {
                    isFinish = false;
                    i = ((MorpionBoard) board).getWidth();
                    j = i;
                }
            }
        }

        return isFinish || isVictory();
    }

    public void saveStateOfGame() {
        copyOfBoard = board.clone();
        copyOfCurrentPlayer = currentPlayer;
    }

    public void loadSavedState() {
        board.copyBoard(copyOfBoard);
        currentPlayer = copyOfCurrentPlayer;
    }

    public GamePanel getPanel() {
        return gamePanel;
    }

    public Game clone() {
        Morpion gameCopy = new Morpion((MorpionBoard) board.clone());
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
