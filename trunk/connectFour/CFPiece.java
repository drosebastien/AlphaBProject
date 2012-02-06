package connectFour;

import framework.*;

public class CFPiece extends Piece {
    public CFPiece(String name, int id) {
        super(name, id);
    }

    public boolean equals(Object obj) {
        CFPiece piece = (CFPiece) obj;
        return piece.getId() == getId();
    }

    public Piece clone() {
        return new CFPiece(getName(), getId());
    }

    public String toString() {
        return "Piece's name : " + getName() + "; Piece's id : " + getId();
    }
}
