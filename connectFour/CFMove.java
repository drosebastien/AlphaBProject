package connectFour;

import framework.*;

public class CFMove extends Move {
    private CFPosition position;
    private CFPiece piece;

    public CFMove(CFPosition position, CFPiece piece) {
        this.position = position;
        this.piece = piece;
    }

    public CFPosition getPosition() {
        return position;
    }

    public CFPiece getPiece() {
        return piece;
    }

    public boolean equals(Object obj) {
        CFMove move = (CFMove) obj;
        return position.equals(move.getPosition()) &
               piece.equals(move.getPiece());
    }

    public String toString() {
        return "Position : " + position.toString() +
               "; piece :" + piece.toString();
    }
}
