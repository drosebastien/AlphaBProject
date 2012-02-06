package morpion;

import framework.*;

public class MorpionMove extends Move {
    private MorpionPosition position;
    private MorpionPiece piece;

    public MorpionMove(MorpionPosition position, MorpionPiece piece) {
        this.position = position;
        this.piece = piece;
    }

    public MorpionPosition getPosition() {
        return position;
    }

    public MorpionPiece getPiece() {
        return piece;
    }

    public boolean equals(Object obj) {
        MorpionMove move = (MorpionMove) obj;
        return position.equals(move.getPosition()) &
               piece.equals(move.getPiece());
    }

    public String toString() {
        return "Position : " + position.toString() +
               "; piece :" + piece.toString();
    }
}
