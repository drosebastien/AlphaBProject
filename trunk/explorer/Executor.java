package explorer;

import gui.*;
import tree.*;
import framework.*;

import java.util.ArrayList;

public class Executor implements MinMaxListener {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private TreeNode currentNode;
    private MinMaxAlgo minMaxAlgo;
    private Thread thread;

    public Executor(Game game, GamePanel gamePanel,
                    TreePanel treePanel, MinMaxAlgo minMaxAlgo) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
        this.minMaxAlgo = minMaxAlgo;
    }

    public void start() {
        this.thread = new Thread(new launcher());
        thread.start();
    }

    public void restart() {
        this.thread.stop();                                                     // à changer stop est déprécié.
        this.thread = new Thread(new launcher());
        thread.start();
    }

    public void setTree(TreeNode root) {
        this.currentNode = root;
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

    public void setValueOfNode(String value) {
        currentNode.setLabel(value);
        repaintPanels();
    }

    public void moved(Movement move, int indexInTreeGame) {
        switch(move) {
            case FORWARD :
                currentNode.setType(NodeType.ANCESTOR_OF_CURRENT);
                currentNode = currentNode.getChild(indexInTreeGame);
                break;
            case BACKWARD :
                currentNode.setType(NodeType.VIEWED);
                currentNode = currentNode.getParent();
                break;
            default :
                break;
        }

        currentNode.setType(NodeType.CURRENT);

        repaintPanels();
    }

    public void refreshTree() {
        Tree.removeStatesOfTree(currentNode);
    }

    public void repaintPanels() {
        gamePanel.repaint();
        treePanel.repaint();
    }

    /*
     * A thread have to be used otherwise everything is blocked by the
     * lock methode from a MinMaxAlgo.
     */
    private class launcher implements Runnable {
        public void run() {
            minMaxAlgo.launchMinMax();
        }
    }
}
