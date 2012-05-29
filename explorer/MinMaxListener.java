package explorer;

/**
 * Cette interface permet d'autoriser l'ecoute d'un algorithme de type
 * MiniMax.
 * @author Sebastien Drobisz.
 */
public interface MinMaxListener {

    /**
     * Cette methode permet de prevenir les listeners que l'algorithme commence
     * son exploration.
     */
    public void started();

    /**
     * Cette methode permet de prevenir les listeners qu'une action impliquant
     * un voyage dans l'arbre a ete realise.
     * @param move La direction du mouvement realise.
     * @param indexInTreeGame L'indice du mouvement dans l'arbre d'exploration.
     * @param event L'evenement associe a l'action.
     */
    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event);

    /**
     * Cette methode permet de donner la valeur minimax de l'etat courant
     * lorsque l'exploration est terminee a tous les listeners.
     * @param value La valeur minimax.
     * @param event L'evenement associe.
     */
    public void setValueOfNode(String value, MinMaxEvent event);

    /**
     * Cette methode permet de prevenir les listeners que l'a recherche va
     * reprendre a partir du debut. (Cela se fait par exemple pour l'iterative
     * deepening depth-first search.)
     * @param event L'evenement associé à l'action.
     */
    public void refreshTree(MinMaxEvent event);

    /**
     * Cette methode permet de prevenir les listeners qu'un nouveau meilleur
     * noeud est selectionne.
     * @param indexOfChild L'indice du meilleur coup par rapport au noeud
     * courant a l'exploration.
     * @param event L'evenement associe a l'action.
     */
    public void setNewBestNode(int indexOfChild, MinMaxEvent event);

    /**
     * Cette methode permet de prevenir les listeners que le meilleur
     * noeud par rapport au noeud courant a l'exploration 
     * n'en est plus un.
     * @param indexOfChild L'index de l'ancien meilleur noeud.
     * @param event L'evenement associe à cette action.
     */
    public void setDropedNode(int indexOfChild, MinMaxEvent event);

    /**
     * Cette methode permet de prevenir les listeners que l'algorithme a
     * fini l'exploration pour l'etat initial.
     * @param evt L'action associee
     */
    public void finished(MinMaxEvent evt);
}
