package morpion;

import java.util.ArrayList;
import framework.*;

public class Morpion extends Game {
    private static final int NB_PLAYERS = 2;
    private int currentPlayer;

    public Morpion() {
        super(new BoardMorpion());
        currentPlayer = 0;
    }

    public void piecesDistribution() {
        PieceMorpion[] pieces = {new PieceMorpion("Cross", 0),
                                 new PieceMorpion("Circle", 1)};

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
        MoveMorpion moveMorpion = (MoveMorpion) move;
        board.placePiece(moveMorpion.getPosition(), moveMorpion.getPiece());
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public void removeMove(Move move) {
        board.removePiece(((MoveMorpion) move).getPosition());
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public ArrayList<Move> getListOfPossibleMove() {
        ArrayList<Move> listOfPossibleMove = new ArrayList<Move>();
        PieceMorpion piece = (PieceMorpion) nextPlayer().getPiece();

        for(int i = 0; i < ((BoardMorpion) board).getWidth(); i++) {
            for(int j = 0; j < ((BoardMorpion) board).getWidth(); j++) {
                if(board.isFree(new PositionMorpion(i, j))) {
                    listOfPossibleMove.add(new MoveMorpion(
                                    new PositionMorpion(i, j), piece));
                }
            }
        }

        return listOfPossibleMove;
    }

    public boolean isVictory() {
        PieceMorpion firstPiece = null;
        boolean isVictory = true;

        for(int i = 0; i < ((BoardMorpion) board).getWidth(); i++) {
            firstPiece = (PieceMorpion) board.getPiece(
                                    new PositionMorpion(i, 0));
            if(firstPiece != null) {
                isVictory = true;
                for(int j = 1; j < ((BoardMorpion) board).getWidth(); j++) {
                    PieceMorpion tmp = (PieceMorpion) board.getPiece(
                                    new PositionMorpion(i, j));
                    if(tmp == null || !tmp.equals(firstPiece)) {
                        j = ((BoardMorpion) board).getWidth();
                        isVictory = false;
                    }
                }
                if(isVictory) {
                    return true;
                }
            }
        }

        for(int i = 0; i < ((BoardMorpion) board).getWidth(); i++) {
            firstPiece = (PieceMorpion) board.getPiece(
                                    new PositionMorpion(0, i));
            if(firstPiece != null) {
                isVictory = true;
                for(int j = 1; j < ((BoardMorpion) board).getWidth(); j++) {
                    PieceMorpion tmp = (PieceMorpion) board.getPiece(
                                    new PositionMorpion(j, i));
                    if(tmp == null || !tmp.equals(firstPiece)) {
                        j = ((BoardMorpion) board).getWidth();
                        isVictory = false;
                    }
                }
                if(isVictory) {
                    return true;
                }
            }
        }

        firstPiece = (PieceMorpion) board.getPiece(new PositionMorpion(0, 0));
        if(firstPiece != null) {
            isVictory = true;
            for(int i = 1; i < ((BoardMorpion) board).getWidth(); i++) {
                PieceMorpion tmp = (PieceMorpion) board.getPiece(
                                    new PositionMorpion(i, i));
                if(tmp == null || !tmp.equals(firstPiece)) {
                    i = ((BoardMorpion) board).getWidth();
                    isVictory = false;
                }
            }
            if(isVictory) {
                return true;
            }
        }

        firstPiece = (PieceMorpion) board.getPiece(new PositionMorpion(0,
                                        ((BoardMorpion) board).getWidth() -1));
        if(firstPiece != null) {
            isVictory = true;
            for(int i = 1; i < ((BoardMorpion) board).getWidth(); i++) {
                PieceMorpion tmp = (PieceMorpion) board.getPiece(
                                    new PositionMorpion(i, 
                                    ((BoardMorpion) board).getWidth() - 1 - i));
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

        for(int i = 0; i < ((BoardMorpion) board).getWidth(); i++) {
            for(int j = 0; j < ((BoardMorpion) board).getWidth(); j++) {
                if(board.getPiece(new PositionMorpion(i, j)) == null) {
                    isFinish = false;
                    i = ((BoardMorpion) board).getWidth();
                    j = i;
                }
            }
        }

        return isFinish || isVictory();
    }

    public Game clone() {
        Morpion gameCopy = new Morpion();
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
