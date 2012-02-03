package morpion;

import framework.*;

public class MoveMorpion extends Move {
    private PositionMorpion position;
    private PieceMorpion piece;

    public MoveMorpion(PositionMorpion position, PieceMorpion piece) {
        this.position = position;
        this.piece = piece;
    }

    public PositionMorpion getPosition() {
        return position;
    }

    public PieceMorpion getPiece() {
        return piece;
    }

    public boolean equals(Object obj) {
        MoveMorpion move = (MoveMorpion) obj;
        return position.equals(move.getPosition()) &
               piece.equals(move.getPiece());
    }

    public String toString() {
        return "Position : " + position.toString() +
               "; piece :" + piece.toString();
    }
}
