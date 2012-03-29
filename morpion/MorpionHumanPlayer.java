package morpion;

import framework.*;
import java.util.Scanner;

public class MorpionHumanPlayer extends HumanPlayer {
    private Scanner in;

    public MorpionHumanPlayer(String name, int id) {
        super(name, id);
        this.in = new Scanner(System.in);
    }

    public void run() {
        /*
        System.out.print("Position x : ");
        int x = Integer.parseInt(in.next());
        System.out.print("Position y : ");
        int y = Integer.parseInt(in.next());

        MorpionMove move = new MorpionMove(
                        new MorpionPosition(y, x), (MorpionPiece) getPiece());
        setPlayingMove(move);
        setIsFinalDecision();
        sem.release();
        */
    }

    public void hitFired(int x, int y) {
        System.out.println(getId() + "| " + getName() + " a joué en (" + x + ", " + y + ").");

        MorpionMove move = new MorpionMove(
                        new MorpionPosition(y, x), (MorpionPiece) getPiece());
        setPlayingMove(move);
        setIsFinalDecision();
        sem.release();
    }

    public void hitFired(Move move, boolean inExplorerMode) {
    }

    public Player clone() {
        Player playerCopy = new MorpionHumanPlayer(getName(), getId());
        playerCopy.piece = piece.clone();

        return playerCopy;
    }
}
