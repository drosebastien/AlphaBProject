package explorer;

import java.util.ArrayList;

public class Controller {
    private String name;
    private Explorer explorer;
    private Executor executor;

    public Controller(String name) {
        this.name = name;
    }

    public void clickOnNode(boolean inExplorerMode, int[] treePosition) {

        if(inExplorerMode) {
            explorer.moveForward(treePosition);
        }
        else {
            executor.progress(treePosition);
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

    public void addExplorer(Explorer explorer) {
        this.explorer = explorer;
    }

    public void addExecutor(Executor executor) {
        this.executor = executor;
    }
}