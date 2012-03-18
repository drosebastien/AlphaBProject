package explorer;

import gui.*;
import framework.*;
import tree.*;
import morpion.*;

import java.util.ArrayList;

public class Explorer {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private Executor executor;
    private ArrayList<Move> lastMoves;
    private int maxDepth;

    public Explorer(Game game, GamePanel gamePanel,
                    TreePanel treePanel, int maxDepth) {
        lastMoves = new ArrayList<Move>();
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
        this.maxDepth = maxDepth;
    }

    public void start() {
        makeTreePanel();
        executor.start();
    }

    public void makeTreePanel() {
        TreeNode root = makeTree(maxDepth);

        treePanel.setTreeRootNode(root);
        treePanel.repaint();
        gamePanel.repaint();
    }

    public TreeNode makeTree(int height) {
        game.getListOfPossibleMove();
        if(game.getListOfPossibleMove().size() != 0) {
            TreeNode root = new TreeNode(null);
            makeTree(height - 1, root);
            return root.getChild(0);
        }
        return new LeafNode(null, 100);
    }

    private void makeTree(int height, TreeNode parent) {
        if(height == 0 || game.isFinish()) {
            LeafNode child = new LeafNode(parent, 100);
            parent.addChildNode(child);
        }
        else {
            TreeNode child = new TreeNode(parent);
            parent.addChildNode(child);
            ArrayList<Move> listOfPossibleMove =
                                    game.getListOfPossibleMove();
            for(int i = 0; i < listOfPossibleMove.size(); i++) {
                game.play(listOfPossibleMove.get(i));
                makeTree(height - 1, child);
                game.removeMove(listOfPossibleMove.get(i));
            }
        }
    }

    public void moveForward(ArrayList<Integer> moves) {
        game.loadSavedState(); //load the last game state where explorer stop;

        String line = "| ";
        for(int i = 0; i < moves.size(); i++) {
            line += moves.get(i) + " | ";
        }

        int size = moves.size();
        for(int i = 0; i < size; i++) {
            lastMoves.add(0, game.getListOfPossibleMove().get(moves.remove(0)));
            game.play(lastMoves.get(0));
        }

        makeTreePanel();

        executor.restart(); //restart the executor and the MinMax algorithme
        game.saveStateOfGame(); //save the new state of game.
    }

    public void removeLast() {
        game.loadSavedState(); //load the last game state where explorer stop;

        if(lastMoves.size() > 0) {
            game.removeMove(lastMoves.remove(0));
            makeTreePanel();
        }

        executor.restart(); //restart the executor and the MinMax algorithme
        game.saveStateOfGame(); //save the new state of game.
    }

    public void addExecutor(Executor executor) {
        this.executor = executor;
    }
}