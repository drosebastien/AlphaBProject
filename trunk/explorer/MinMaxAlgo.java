package explorer;

import tree.*;
import framework.*;

import java.util.ArrayList;

public abstract class MinMaxAlgo {
    private ArrayList<MinMaxListener> listeners;
    private int maxDepth;
    private TreeNode treeRoot;
    private Game game;

    public MinMaxAlgo(Game game, int maxDepth) {
        initMinMax(game, maxDepth, null);
    }

    public MinMaxAlgo(Game game, int maxDepth, TreeNode treeRoot) {
        initMinMax(game, maxDepth, treeRoot);
    }

    private void initMinMax(Game game, int maxDepth, TreeNode treeRoot) {
        this.game = game;
        this.maxDepth = maxDepth;
        this.treeRoot = treeRoot;

        listeners = new ArrayList<MinMaxListener>();
    }

    public abstract Move launchMinMax();

    public void addListener(MinMaxListener listener) {
        listeners.add(listener);
    }
}
