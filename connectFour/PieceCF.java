package connectFour;

import framework.*;

public class PieceCF extends Piece {
    public PieceCF(String name, int id) {
        super(name, id);
    }

    public boolean equals(Object obj) {
        PieceCF piece = (PieceCF) obj;
        return piece.getId() == getId();
    }

    public Piece clone() {
        return new PieceCF(getName(), getId());
    }

    public String toString() {
        return "Piece's name : " + getName() + "; Piece's id : " + getId();
    }
}
