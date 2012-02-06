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
        System.out.print("Position x : ");
        int x = Integer.parseInt(in.next());
        System.out.print("Position y : ");
        int y = Integer.parseInt(in.next());

        MorpionMove move = new MorpionMove(
                        new MorpionPosition(y, x), (MorpionPiece) getPiece());
        setPlayingMove(move);
        setIsFinalDecision();
    }

    public Player clone() {
        Player playerCopy = new MorpionHumanPlayer(getName(), getId());
        playerCopy.piece = piece.clone();

        return playerCopy;
    }
}
