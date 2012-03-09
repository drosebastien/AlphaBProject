package explorer;

import gui.*;
import framework.*;

public class Executor {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;

    public Executor(Game game, GamePanel gamePanel, TreePanel treePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
    }

    public void printMessage(String message) {
        System.out.println("executor : " + message);
    }
}
