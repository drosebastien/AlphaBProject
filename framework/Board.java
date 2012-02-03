package framework;

public abstract class Board {
    public Board() {
    }

    public abstract boolean isFree(Position position);

    public abstract void placePiece(Position position, Piece piece);

    public abstract void removePiece(Position position);

    public abstract Piece getPiece(Position position);

    public abstract Board clone();
}
