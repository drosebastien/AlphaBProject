package framework;
/**
 * Cette classe permet de creer lancer un exception lorsqu'un mouvement est
 * mal utilise.
 * @author Sebastien Drobisz    
 */
public class MoveException extends Exception {
    /**
     * Permet de construire une exception sans aucun texte lie.
     */
    public MoveException() {
    }

    /**
     * Permet de lier un texte lors de la creation d'une exception.
     * @param message Le texte lie au lancement d'une exception.
     */
    public MoveException(String message) {
        super(message);
    }
}
