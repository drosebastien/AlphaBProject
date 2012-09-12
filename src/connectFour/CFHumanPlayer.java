package connectFour;

import framework.*;
import java.util.Scanner;

public class CFHumanPlayer extends HumanPlayer {
    private Scanner in;

    /**
     * test
     */
    public CFHumanPlayer (String name, int id) {
        super(name, id);
        this.in = new Scanner(System.in);
    }

    public void run() {
        System.out.print("Position : ");
        int x = Integer.parseInt(in.next());

        CFMove move = new CFMove(new CFPosition(x), (CFPiece) getPiece());
        setPlayingMove(move);
        setIsFinalDecision();
        sem.release();
    }

    public void hitFired(int x, int y) {                                        // Attention, il faut changer ça
        // à faire
    }

    public void hitFired(Move move, boolean inExplorerMode) {
    }

    public Player clone() {
        Player playerCopy = new CFHumanPlayer(getName(), getId());
        playerCopy.piece = piece.clone();

        return playerCopy;
    }
}
