package framework;

/**
 * Cette classe permet de creer un jeton.
 * @author Sebastien Drobisz.
 */
public abstract class Piece {
    private String name;
    private int id;

    /**
     * Permet de creer un jeton en lui donnant un nom et un id.
     * @param name Le nom donne au jeton.
     * @param id L'id donne au jeton.
     */
    public Piece(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Cette methode permet de retourner l'id du jeton.
     * @return L'id du jeton.
     */
    public int getId() {
        return id;
    }

    /**
     * Cette methode permet de retourner le nom d'un jeton.
     * @return Le nom du jeton.
     */
    public String getName() {
        return name;
    }

    /**
     * Permet de retourner un clone du jeton.
     * @return Le clone du jeton.
     */
    public abstract Piece clone();
}
