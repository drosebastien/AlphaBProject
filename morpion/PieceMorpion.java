package morpion;

import framework.*;

public class PieceMorpion extends Piece {
    private String name;
    private int id;

    public PieceMorpion(String name, int id) {
        super(name, id);
        this.name = name;
        this.id = id;
    }

    public boolean equals(Object obj) {
        PieceMorpion piece = (PieceMorpion) obj;
        return piece.getId() == getId();
    }

    public Piece clone() {
        return new PieceMorpion(name, id);
    }

    public String toString() {
        return "Piece's name : " + name + "; Piece's id : " + id;
    }
}
