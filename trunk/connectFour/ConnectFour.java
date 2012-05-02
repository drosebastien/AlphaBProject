package connectFour;

import java.util.ArrayList;
import framework.*;
import connectFour.gui.CFGamePanel;
import gui.GamePanel;

public class ConnectFour extends Game {
    private static final int NB_PLAYERS = 2;
    private int currentPlayer;
    private Board copyOfBoard;
    private ArrayList<CFMove> lastMoves;

    public ConnectFour() {
        super(new CFBoard());

        lastMoves = new ArrayList<CFMove>();
        gamePanel = new CFGamePanel(board);
        currentPlayer = 0;
    }

    public ConnectFour(CFBoard board) {
        super(board);

        lastMoves = new ArrayList<CFMove>();
        gamePanel = new CFGamePanel(board);
        currentPlayer = 0;
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

    public void play(Move move) throws MoveException {
        CFMove cFMove = (CFMove) move;
        board.getPiece(cFMove.getPosition());
        if(board.getPiece(cFMove.getPosition()) != null) {
            throw new MoveException("Position is already taken");
        }
        lastMoves.add(0,cFMove);//dernier coup retenu pour test de victoire !

        board.placePiece(cFMove.getPosition(), cFMove.getPiece());
        currentPlayer = (currentPlayer + 1) % 2;

        depthToSelectState++;
    }

    public void removeMove(Move move) {
        board.removePiece(((CFMove) move).getPosition());
        currentPlayer = (currentPlayer + 1) % 2;
        //la ligne suivant peu se faire étant donné que si on retire un coup
        //gagnant, le précédent ne pouvait être gagnant.
        lastMoves.remove(0);

        depthToSelectState--;
    }

    public Move completeMove(Move move) {
        CFPiece piece = (CFPiece) nextPlayer().getPiece();
        CFPosition position = ((CFMove) move).getPosition();

        return new CFMove(position, piece);
    }

    public MoveIterator getPossibleMoves() {
        if(depthToSelectState >= 0 && firsts != null &&
                                      firsts.length > depthToSelectState) {
            return new MoveIterator(getListOfPossibleMoves(),
                                    firsts[depthToSelectState]);
        }
        return new MoveIterator(getListOfPossibleMoves());
    }

    public boolean isPossibleMove(Move move) {
        CFMove cFMove = (CFMove) move;

        return board.getPiece(cFMove.getPosition()) == null;
    }

    public boolean isVictory() {
        if(lastMoves.size() == 0) {
            return false;
        }
        int vTab[] = {1, 0, 1, 1};
        int hTab[] = {0, 1, 1, -1};
        int xHit = lastMoves.get(0).getPosition().getX();
        int yHit = lastMoves.get(0).getPosition().getY();
        Piece piece = lastMoves.get(0).getPiece();

        for(int i = 0; i < vTab.length; i++) {
            int nb = 0;
            for(int j = -1; j <= 1; j+=2) {
                boolean test = true;
                for(int z = 1; z < 4 && test; z++) {
                    CFPosition pos = new CFPosition(xHit + (j * z * vTab[i]),
                                                    yHit + (j * z * hTab[i]));
                    if(((CFBoard) board).inBoard(pos) &&
                       ((CFBoard) board).getPiece(pos) != null &&
                       ((CFBoard) board).getPiece(pos).equals(piece)) {
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

    public void saveStateOfGame() {
    }

    public void loadSavedState() {
    }

    public GameMemento saveToMemento() {
        CFMemento memento = new CFMemento();
        memento.setBoardState(this.board.clone());
        memento.setCurrentPlayerState(this.currentPlayer);
        memento.setLastMove(lastMoves);

        return memento;
    }

    public void resetFromMemento(GameMemento memento) {
        if(memento != null) {
            super.loadSavedState();
            CFMemento cFMemento = (CFMemento) memento;
            board.copyBoard(cFMemento.getBoardSavedState());
            currentPlayer = cFMemento.getCurrentPlayerSavedState();
            lastMoves = cFMemento.getLastMove();
        }
    }

    public GamePanel getPanel() {
        return gamePanel;
    }

    public Game clone() {
        ConnectFour gameCopy = new ConnectFour((CFBoard) board.clone());
        gameCopy.currentPlayer = currentPlayer;

        for(int i = 0; i < listOfPlayers.size(); i++) {
            gameCopy.listOfPlayers.add(listOfPlayers.get(i).clone());
        }

        return gameCopy;
    }

    public String toString() {
        return board.toString();
    }

    private ArrayList<Move> getListOfPossibleMoves() {
        ArrayList<Move> listOfPossibleMoves = new ArrayList<Move>();
        CFPiece piece = (CFPiece) nextPlayer().getPiece();

        for(int i = 0; i < ((CFBoard) board).getWidth(); i++) {
            if(board.isFree(new CFPosition(i))) {
                listOfPossibleMoves.add(new CFMove(new CFPosition(i), piece));
            }
        }

        return listOfPossibleMoves;
    }
}
