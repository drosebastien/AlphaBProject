package morpion;

import framework.*;

public class PieceMorpion extends Piece {
    public PieceMorpion(String name, int id) {
        super(name, id);
    }

    public boolean equals(Object obj) {
        PieceMorpion piece = (PieceMorpion) obj;
        return piece.getId() == getId();
    }

    public Piece clone() {
        return new PieceMorpion(getName(), getId());
    }

    public String toString() {
        return "Piece's name : " + getName() + "; Piece's id : " + getId();
    }
}
