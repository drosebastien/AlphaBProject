package explorer;

import gui.*;
import tree.*;
import framework.*;

import java.util.ArrayList;

public class Executor implements MinMaxListener {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private TreeNode root;
    private MinMaxAlgo minMaxAlgo;

    public Executor(Game game, GamePanel gamePanel,
                    TreePanel treePanel, MinMaxAlgo minMaxAlgo) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
        this.minMaxAlgo = minMaxAlgo;
    }

    public void setTree(TreeNode root) {
        this.root = root;
    }

    public void progress() {
        minMaxAlgo.unlock();
    }

    public void progress(ArrayList<Integer> moves) {
        //je ne fait qu'imprimer dans le terminal le chemin qu'il faut suivre.
        String line = "| ";
        for(int i = 0; i < moves.size(); i++) {
            line += moves.get(i) + " | ";
        }

        System.out.println("je dois avancer mon algo en : " + line);
    }

    public void locked(boolean moveFoward, int indexInTreeGame) {
        if(moveFoward) {
            System.out.println("avance");
        }
        else {
            System.out.println("revule");
        }
    }

    public void printMessage(String message) {
        System.out.println("executor : " + message);
    }
}
