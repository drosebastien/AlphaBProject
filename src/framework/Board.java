package framework;

/**
 * Cette classe permet de creer le board d'un jeu.
 * @author Sebastien Drobisz
 */
public abstract class Board {
    /**
     * Ce constructeur permet de creer le board du jeu.
     */
    public Board() {
    }

    /**
     * Cette methode permet de savoir si une position est occupee
     * @param position La position a verifier.
     * @return true si inoccupee, false sinon.
     */
    public abstract boolean isFree(Position position);

    /**
     * Permet de placer une piece sur le board.
     * @param position La position ou poser la piece.
     * @param piece La piece a poser.
     */
    public abstract void placePiece(Position position, Piece piece);

    /**
     * Permet de retirer une piece du board.
     * @param position La position sur le board de la piece a retirer.
     */
    public abstract void removePiece(Position position);

    /**
     * Permet de donner la piece posee a une position donnee.
     * @param position La position de la piece a retourner.
     */
    public abstract Piece getPiece(Position position);

    /**
     * Permet de faire une copie du board
     * @param board Le board qui devra etre identique a ce board.
     */
    public abstract void copyBoard(Board board);

    /**
     * Permet de retourner un clone de ce board.
     * @return Le clone de ce board.
     */
    public abstract Board clone();
}
