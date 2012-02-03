package morpion;

import framework.*;
import java.util.Scanner;

public class HumanPlayerMorpion extends HumanPlayer {
    private Scanner in;

    public HumanPlayerMorpion(String name, int id) {
        super(name, id);
        this.in = new Scanner(System.in);
    }

    public void run() {
        System.out.print("Position x : ");
        int x = Integer.parseInt(in.next());
        System.out.print("Position y : ");
        int y = Integer.parseInt(in.next());

        MoveMorpion move = new MoveMorpion(
                        new PositionMorpion(y, x), (PieceMorpion) getPiece());
        System.out.println("player: debug");
        setPlayingMove(move);
        setIsFinalDecision();
    }

    public Player clone() {
        Player playerCopy = new HumanPlayerMorpion(name, id);
        playerCopy.piece = piece.clone();
        return playerCopy;
    }
}
