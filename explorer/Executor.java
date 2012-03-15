package explorer;

import gui.*;
import framework.*;

import java.util.ArrayList;

public class Executor {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;

    public Executor(Game game, GamePanel gamePanel, TreePanel treePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
    }

    public void progress(ArrayList<Integer> moves) {
        String line = "| ";
        for(int i = 0; i < moves.size(); i++) {
            line += moves.get(i) + " | ";
        }

        System.out.println("je dois avancer mon algo en : " + line);
    }

    public void printMessage(String message) {
        System.out.println("executor : " + message);
    }
}
