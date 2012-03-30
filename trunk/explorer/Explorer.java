package explorer;

import gui.*;
import framework.*;
import tree.*;

import java.util.ArrayList;

public class Explorer {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private TreeNode root;
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
        executor.setTree(root);
        executor.start();
    }

    public void makeTreePanel() {
        root = makeTree(maxDepth);

        treePanel.setTreeRootNode(root);
        treePanel.repaint();
        gamePanel.repaint();
    }

    public TreeNode makeTree(int height) {
//        game.getListOfPossibleMove();
//        if(game.getListOfPossibleMove().size() != 0) {
//            TreeNode root = new TreeNode(null);
//            makeTree(height - 1, root);
//            return root.getChild(0);
//        }
//        return new LeafNode(null, 100);

        MoveIterator iterator = game.getPossibleMoves();
        if(iterator.hasNext()) {
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
            MoveIterator iterator = game.getPossibleMoves();
            while(iterator.hasNext()) {
                Move move = iterator.next();
                try {
                    game.play(move);
                }
                catch(MoveException e) {
                    e.printStackTrace();
                }
                makeTree(height - 1, child);
                game.removeMove(move);
            }
        }
    }

    public void moveForward(Move move) {
        game.loadSavedState();
        if(!game.isFinish()) {
            // permet de donner la bonne piece au mouvement
            Move completeMove = game.completeMove(move);
            if(game.isPossibleMove(completeMove)) {
                try {
                    game.play(completeMove);
                }
                catch(MoveException e) {
                    e.printStackTrace();
                }
                lastMoves.add(0, completeMove);
                game.saveStateOfGame();
            }

            makeTreePanel();

            executor.setTree(root);
            executor.restart();
        }
    }

    public void moveForward(int[] moves) {
        game.loadSavedState(); //load the last game state where explorer stop;

        int size = moves.length;
        for(int i = 0; i < size; i++) {
            Move tmp = game.getListOfPossibleMoves().get(moves[i]);
            lastMoves.add(0, tmp);
            try {
                game.play(lastMoves.get(0));
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
        }

        makeTreePanel();

        executor.setTree(root);
        executor.restart(); //restart the executor and the MinMax algorithme
        game.saveStateOfGame(); //save the new state of game.
    }

    public void removeLast() {
        game.loadSavedState(); //load the last game state where explorer stop;

        if(lastMoves.size() > 0) {
            game.removeMove(lastMoves.remove(0));
        }
        makeTreePanel();

        executor.setTree(root);
        executor.restart(); //restart the executor and the MinMax algorithme
        game.saveStateOfGame(); //save the new state of game.
    }

    public void addExecutor(Executor executor) {
        this.executor = executor;
    }
}
