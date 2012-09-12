package explorer;

/**
 * Cette enumeration permet de decrire le sens du mouvemetn que prend
 * un algorithme.
 * @author Sebastien Drobisz.
 */
public enum Movement {
    /**
     * Reste a la meme position.
     */
    NEUTRAL,
    /**
     * Descend dans l'arbre.
     */
    FORWARD,
    /**
     * Remonte dans l'arbre.
     */
    BACKWARD;
}
