package explorer;

import gui.*;
import framework.*;

import java.util.ArrayList;

public class Controller implements GamePanelListener, TreePanelListener,
                                   MinMaxEducativeToolsListener {
    private String name;
    private Explorer explorer;
    private Executor executor;
    private boolean firstChangeAlgo;

    public Controller(String name) {
        this.name = name;
        firstChangeAlgo = true;
    }

    public void algoHaveChanged(String algoName) {
        executor.changeAlgo(algoName);
        executor.pause();
        explorer.restart();
    }

    public void fctHaveChanged(String fctName) {
        executor.setEvalFunction(fctName);
        executor.pause();
        if(!firstChangeAlgo) {
            explorer.restart();
        }
        firstChangeAlgo = false;
    }

    public void windowValuesHaveChanged(int minValue, int maxValue) {
        executor.windowValuesHaveChanged(minValue, maxValue);
        explorer.restart();
    }

    public void clickOnNode(boolean inExplorerMode, int[] pathToNode) {

        if(inExplorerMode) {
            explorer.moveForward(pathToNode);
        }
        else {
            executor.progress(pathToNode);
        }
    }

    public void bestNodeSelected(boolean inExplorerMode, int[] pathToNode) {
        //quitPreview();
        pause(inExplorerMode);
        if(!inExplorerMode) {
            explorer.selectFirstNode(pathToNode);
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

    public void preview(int[] moves, boolean inExplorerMode) {
        pause(inExplorerMode);
        explorer.preview(moves);
    }

    public void quitPreview() {
        explorer.quitPreview();
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

    public void play(boolean inExplorerMode) {
        if(!inExplorerMode) {
            executor.play();
        }
    }

    public void pause(boolean inExplorerMode) {
        if(!inExplorerMode) {
            executor.pause();
        }
    }

    public void setSpeed(int value) {
        executor.setSpeed(value);
    }

    public void stop(boolean inExplorerMode) {
        if(!inExplorerMode) {
            executor.pause();
            explorer.restart();
        }
    }
}
