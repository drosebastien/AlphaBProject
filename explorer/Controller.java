package explorer;

import gui.*;
import framework.*;

import java.util.ArrayList;

public class Controller implements GamePanelListener, TreePanelListener,
                                   MinMaxEducativeToolsListener {
    private String name;
    private Explorer explorer;
    private Executor executor;

    public Controller(String name) {
        this.name = name;
    }

    public void algoHaveChanged(MinMaxAlgo algo) {
        executor.setMinMaxAlgo(algo);
        explorer.restart();
    }

    public void windowValuesHaveChanged(int minValue, int maxValue) {
        executor.windowValuesHaveChanged(minValue, maxValue);
        explorer.restart();
    }

    public void clickOnNode(boolean inExplorerMode, int[] treePosition) {

        if(inExplorerMode) {
            explorer.moveForward(treePosition);
        }
        else {
            executor.progress(treePosition);
        }
    }

    public void hitFired(int x, int y) {
        System.out.printf("x : %d; y : %d\n", x, y);
    }

    public void hitFired(Move move, boolean inExplorerMode) {
        if(inExplorerMode) {
            explorer.moveForward(move);
        }
    }

    public void progress(boolean inExplorerMode) {
        if(inExplorerMode) {
        }
        else {
            executor.progress();
        }
    }

    public void removeLast(boolean inExplorerMode) {
        if(inExplorerMode) {
            explorer.removeLast();
        }
        else {
        }
    }

    public void treeDepthChanged(int treeDepth) {
        explorer.setTreeDepth(treeDepth);
    }

    public void addExplorer(Explorer explorer) {
        this.explorer = explorer;
    }

    public void addExecutor(Executor executor) {
        this.executor = executor;
    }
}
