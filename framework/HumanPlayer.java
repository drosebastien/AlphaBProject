package framework;

import gui.GamePanelListener;

public abstract class HumanPlayer extends Player implements GamePanelListener {
    public HumanPlayer(String name, int id) {
        super(name, id);
    }

    public abstract void hitFired(int x, int y);                                // à changer. mettre une action
}
