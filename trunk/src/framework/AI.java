package framework;

/**
 * La super classe de tous les joueurs IA
 * @author Sebastien Drobisz
 */
public abstract class AI extends Player {

    /**
     * Initialise une IA
     * @param name Le nom de l'IA.
     * @param id Numero utilise pour identifier le joueur.
     */
    public AI(String name, int id) {
        super(name, id);
    }
}
