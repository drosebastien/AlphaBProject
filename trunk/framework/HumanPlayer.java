package framework;

import gui.GamePanelListener;

/**
 * Cette classe permet de creer un joueur humain. Les differents joueurs sont
 * utilise pour jouer a une partie. Lors de l'utilisation de l'outil
 * pedagogique,celui-ci ce charge de tout gerer independament du type de joueur.
 * @author Sebastien Drobisz
 */
public abstract class HumanPlayer extends Player implements GamePanelListener {
    public HumanPlayer(String name, int id) {
        super(name, id);
    }
}
