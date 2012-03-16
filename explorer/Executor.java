package explorer;

import gui.*;
import tree.*;
import framework.*;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Executor implements MinMaxListener {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private TreeNode root;

    public Executor(Game game, GamePanel gamePanel, TreePanel treePanel) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
    }

    public void setTree(TreeNode root) {
        this.root = root;
    }

    public void progress(ArrayList<Integer> moves) {
        String line = "| ";
        for(int i = 0; i < moves.size(); i++) {
            line += moves.get(i) + " | ";
        }

        System.out.println("je dois avancer mon algo en : " + line);
    }

    public void locked(int indexInTreeGame, boolean moveFoward, Semaphore semaphore) {
    }

    public void locked(boolean moveFoward, int indexInTreeGame) {
    }

    public void printMessage(String message) {
        System.out.println("executor : " + message);
    }
}
