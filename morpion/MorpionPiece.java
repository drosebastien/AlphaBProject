package morpion;

import framework.*;

public class MorpionPiece extends Piece {
    public MorpionPiece(String name, int id) {
        super(name, id);
    }

    public boolean equals(Object obj) {
        MorpionPiece piece = (MorpionPiece) obj;
        return piece.getId() == getId();
    }

    public Piece clone() {
        return new MorpionPiece(getName(), getId());
    }

    public String toString() {
        return "Piece's name : " + getName() + "; Piece's id : " + getId();
    }
}
