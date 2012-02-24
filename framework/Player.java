package framework;

import java.util.concurrent.Semaphore;

public abstract class Player implements Runnable {
    private String name;
    public Piece piece;
    private int id;
    private boolean isStopped;
    private Move playingMove;
    private boolean isEnd;
    private boolean isFinalDecision;
    protected Game game;
    protected Semaphore sem;

    public Player(String name, int id) {
        this.name = name;
        this.piece = null;
        this.id = id;
        this.isStopped = false;
        this.isFinalDecision = false;
        this.isEnd = false;
        playingMove = null;
    }

    public void setSemaphore(Semaphore sem) {
        this.sem = sem;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void run();

    public boolean hasMove() {
        return playingMove != null;
    }

    protected void setPlayingMove(Move move) {
        playingMove = move;
    }

    protected void setIsFinalDecision() {
        isFinalDecision = true;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public Move getMove() {
        isFinalDecision = false;
        Move tmp = playingMove;
        playingMove = null;
        return tmp;
    }

    public boolean isFinalDecision() {
        return isFinalDecision;
    }

    public void setIsStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }

    public abstract Player clone();

    public String toString() {
        return "Player's name : " + name + "; id : " + id;
    }
}
