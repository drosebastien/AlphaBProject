package connectFour;

import java.util.ArrayList;
import framework.*;
import gui.GamePanel;

public class ConnectFour extends Game {
    private static final int NB_PLAYERS = 2;
    private int currentPlayer;
    private CFMove lastMove;

    public ConnectFour() {
        super(new CFBoard());
        currentPlayer = 0;
    }

    public GamePanel getPanel() {                                               // attention, il faut changer ça
        return null;
    }

    public void piecesDistribution() {
        CFPiece[] pieces = {new CFPiece("yellow", 0),
                            new CFPiece("red", 1)};

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
        lastMove = (CFMove) move;
        board.placePiece(lastMove.getPosition(), lastMove.getPiece());
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public void removeMove(Move move) {
        board.removePiece(((CFMove) move).getPosition());
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public ArrayList<Move> getListOfPossibleMove() {
        ArrayList<Move> listOfPossibleMove = new ArrayList<Move>();
        CFPiece piece = (CFPiece) nextPlayer().getPiece();

        for(int i = 0; i < ((CFBoard) board).getWidth(); i++) {
            if(board.isFree(new CFPosition(i))) {
                listOfPossibleMove.add(new CFMove(new CFPosition(i), piece));
            }
        }

        return listOfPossibleMove;
    }

    public boolean isVictory() {
        if(lastMove == null) {
            return false;
        }
        int vTab[] = {1, 0, 1, 1};
        int hTab[] = {0, 1, 1, -1};
        int xHit = lastMove.getPosition().getX();
        int yHit = lastMove.getPosition().getY();

        for(int i = 0; i < vTab.length; i++) {
            int nb = 0;
            for(int j = -1; j <= 1; j+=2) {
                boolean test = true;
                for(int z = 1; z < 4 && test; z++) {
                    CFPosition pos = new CFPosition(xHit + (j * z * vTab[i]),
                                                    yHit + (j * z * hTab[i]));
                    if(((CFBoard) board).inBoard(pos) &&
                       ((CFBoard) board).getPiece(pos) != null &&
                       ((CFBoard) board).getPiece(pos).equals(lastMove.getPiece())) {
                        nb++;
                    }
                    else {
                        test = false;
                    }
                }
            }

            if(nb >= 3) {
                return true;
            }
        }

        return false;
    }

    public Player getWinner() {
        return listOfPlayers.get((currentPlayer + 1) % 2);
    }

    public boolean isFinish() {
        boolean isFinish = true;

        for(int i = 0; i < ((CFBoard) board).getWidth(); i++) {
            if(board.isFree(new CFPosition(i))) {
                isFinish = false;
                i = ((CFBoard) board).getWidth();
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
