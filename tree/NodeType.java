package tree;

/**
 * Cette enumeration permet de determiner le type d'un noeud.
 * @author Sebastien Drobisz
 */
public enum NodeType {
    /**
     * Si noeud ancetre au noeud courant.
     */
    ANCESTOR_OF_CURRENT,
    /**
     * Si noeud ancetre a un meilleur noeud.
     */
    ANCESTOR_OF_IMPORTANT,
    /**
     * Si noeud courant.
     */
    CURRENT,
    /**
     * Si meilleur noeud.
     */
    IMPORTANT,
    /**
     * Si Noeud d'apercu.
     */
    PREVIEW,
    /**
     * Si noeud ancetre a un noeud apercu.
     */
    ANCESTOR_OF_PREVIEW,
    /**
     * Si simple noeud deja explore.
     */
    VIEWED,
    /**
     * Autre.
     */
    NEITHER
}
