package explorer;

import framework.*;

/**
 * Cette interface doit etre implementee par toute les fonctions d'evaluation
 * pour pouvoir etre utilisee.
 * @author Sebastien Drobisz
 */
public interface EvalFunction extends MinMaxListener {

    /**
     * Permet d'analyser l'etat en cours du game passe en parametre par la
     * methode setGame().
     */
    public int evalFunction();

    /**
     * Cette methode permet de donner le jeu dont les etats seront evalues
     * @param game Le jeu dont les etats seront evalues
     */
    public void setGame(Game game);

    /**
     * Cette methode permet de selectionner le joueur pour qui l'evaluation doit
     * se faire. (le joueur qui joue en premier dans l'arbre de jeu selectionne.
     * @param player Le joueur pour qui l'evaluation doit se faire.
     */
    public void setPlayer(Player player);
}
