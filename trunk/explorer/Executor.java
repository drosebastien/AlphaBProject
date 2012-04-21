package explorer;

import gui.*;
import tree.*;
import framework.*;

import java.util.ArrayList;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Executor implements MinMaxListener {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private TreeNode currentNode;
    private MinMaxAlgo minMaxAlgo;
    private Thread thread;
    private Timer timer;

    public Executor(Game game, GamePanel gamePanel,
                    TreePanel treePanel, MinMaxAlgo minMaxAlgo) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
        this.minMaxAlgo = minMaxAlgo;
        timer = new Timer(100, new TimerListener());
        minMaxAlgo.addListener(this);
        //minMaxAlgo.addListener(new MinMaxListenerTest());
    }

    public void changeAlgo(String algoName) {
        int minValue = this.minMaxAlgo.getMinValue();
        int maxValue = this.minMaxAlgo.getMaxValue();

        this.minMaxAlgo = MinMaxAlgoFactory.getInstance().getMinMaxAlgo(
                              algoName, minMaxAlgo.getGame(),
                              minMaxAlgo.maxDepth(),
                              minMaxAlgo.getEvalFunction());
        this.minMaxAlgo.addListener(this); // ajout du listener
        this.minMaxAlgo.setMinValue(minValue); // ajout de la valeur min courant
        this.minMaxAlgo.setMaxValue(maxValue); // idem pour la valeur max
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

    public void setMaxDepth(int maxDepth) {
        minMaxAlgo.setMaxDepth(maxDepth);
    }

    public void setTree(TreeNode root) {
        this.currentNode = root;
    }

    public void progress() {
        minMaxAlgo.unlock();
    }

    public void windowValuesHaveChanged(int minValue, int maxValue) {
        minMaxAlgo.setMinValue(minValue);
        minMaxAlgo.setMaxValue(maxValue);
    }

    public void progress(int[] moves) {
        //je ne fait qu'imprimer dans le terminal le chemin qu'il faut suivre.
        String line = "| ";
        for(int i = 0; i < moves.length; i++) {
            line += moves[i] + " | ";
        }

        System.out.println("je dois avancer mon algo en : " + line);
    }

    public void setValueOfNode(String value) {
        currentNode.setLabel(value);
        repaintPanels();
    }

    public void setNewBestNode(int indexOfChild) {
        removeStateOfPastImportantNode(currentNode);

        TreeNode newImportantNode = currentNode.getChild(indexOfChild);
        newImportantNode.setType(NodeType.IMPORTANT);
        //setImportantAncestor(currentNode);
        repaintPanels();
    }

    /*
     * donne le statut "vue" au précédent noeud important et fait de même
     * pour tous les noeuds ANCESTOR_OF_IMPORTANT
     */
    private void removeStateOfPastImportantNode(TreeNode node) {
        for(int i = 0; i < node.getNbChild(); i++) {
            TreeNode child = node.getChild(i);
            if(child.getType() == NodeType.IMPORTANT) {
                child.setType(NodeType.VIEWED);
                removeStateOfPastImportantNode(child);
            }
        }
    }

    private void setImportantAncestor(TreeNode node) {
        if(node != null) {
            node.setType(NodeType.ANCESTOR_OF_IMPORTANT);
            setImportantAncestor(node.getParent());
        }
    }

    public void setDropedNode(int indexOfChild) {
        removeStateOfPastImportantNode(currentNode.getChild(indexOfChild));
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

    public void play() {
        timer.restart();
    }

    public void pause() {
        timer.stop();
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

    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            progress();
        }
    }
}