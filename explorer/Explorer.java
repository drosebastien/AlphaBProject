package explorer;

import gui.*;
import framework.*;

public class Explorer {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private Executor executor;

    public Explorer(Game game, GamePanel gamePanel, TreePanel treePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
    }

    public void addExecutor(Executor executor) {
        this.executor = executor;
    }

    public void printMessage(String message) {
        System.out.println("explorer : " + message);
    }
}
