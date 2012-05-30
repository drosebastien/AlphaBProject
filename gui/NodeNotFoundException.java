package gui;

/**
 * Cette exceotion est utilisee lorsque qu'un noeud n'est pas detecte
 * par la souris.
 * @author Sebastien Drobisz
 */
public class NodeNotFoundException extends Exception {
    /**
     * Constructeur permettant de creer l'exception sans message
     */
    public NodeNotFoundException() {
        super("Node is not found");
    }

    /**
     * Constructeur permettant de creer l'exception avec les coordonnees où
     * aurait du se trouver un noeud.
     */
    public NodeNotFoundException(int x, int y) {
        super("Node is not found with coordinate (" + x + ", " + y + ")");
    }
}
