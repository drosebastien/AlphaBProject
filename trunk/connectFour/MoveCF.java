package connectFour;

import framework.*;

public class MoveCF extends Move {
    private PositionCF position;
    private PieceCF piece;

    public MoveCF(PositionCF position, PieceCF piece) {
        this.position = position;
        this.piece = piece;
    }

    public PositionCF getPosition() {
        return position;
    }

    public PieceCF getPiece() {
        return piece;
    }

    public boolean equals(Object obj) {
        MoveCF move = (MoveCF) obj;
        return position.equals(move.getPosition()) &
               piece.equals(move.getPiece());
    }

    public String toString() {
        return "Position : " + position.toString() +
               "; piece :" + piece.toString();
    }
}
