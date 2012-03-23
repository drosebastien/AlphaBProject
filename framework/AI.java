package framework;

/**
 * The superclass of all AI players.
 * @author Sebastien Drobisz
 */
public abstract class AI extends Player {

    /**
     * Initialize an AI player.
     * @param name Name of the player.
     * @param id Number used to identify the player
     */
    public AI(String name, int id) {
        super(name, id);
    }
}
