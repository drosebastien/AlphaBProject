package gui;

import framework.*;

/**
 * Cette interface permet une classe d'ecouter un panel de jeu.
 * @author Sebastien Drobisz.
 */
public interface GamePanelListener {

    /**
     * Cette methode permet de prevenir qu'un coup a ete joue et dans quel
     * mode cela a ete fait.
     * @param move Le coup joue.
     * @param inExplorerMode true si dans le mode explorer (changement d'etat),
     * false sinon
     */
    public void hitFired(Move move, boolean inExplorerMode);
}
