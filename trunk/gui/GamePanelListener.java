package gui;

import framework.*;

public interface GamePanelListener {

    public void hitFired(int x, int y);

    public void hitFired(Move move, boolean inExplorerMode);
}
