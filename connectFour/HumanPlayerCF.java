package connectFour;

import framework.*;
import java.util.Scanner;

public class HumanPlayerCF extends HumanPlayer {
    private Scanner in;

    public HumanPlayerCF (String name, int id) {
        super(name, id);
        this.in = new Scanner(System.in);
    }

    public void run() {
        System.out.print("Position : ");
        int x = Integer.parseInt(in.next());

        MoveCF move = new MoveCF(new PositionCF(x), (PieceCF) getPiece());
        setPlayingMove(move);
        setIsFinalDecision();
    }

    public Player clone() {
        Player playerCopy = new HumanPlayerCF(getName(), getId());
        playerCopy.piece = piece.clone();

        return playerCopy;
    }
}
