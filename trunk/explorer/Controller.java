package explorer;

import java.util.ArrayList;

public class Controller {
    private String name;
    private Explorer explorer;
    private Executor executor;

    public Controller(String name) {
        this.name = name;
    }

    public void printMessage(String message, boolean mode) {
        System.out.println(name + " : " + message);
        if(mode) {
            explorer.printMessage(message);
        }
        else {
            executor.printMessage(message);
        }
    }

    public void clickOnNode(boolean explorerMode,
                            ArrayList<Integer> treePosition) {

        if(explorerMode) {
            explorer.moveForward(treePosition);
        }
        else {
            executor.progress(treePosition);
        }
    }

    public void moveForward(ArrayList<Integer> moves) {
        explorer.moveForward(moves);
    }

    public void addExplorer(Explorer explorer) {
        this.explorer = explorer;
    }

    public void addExecutor(Executor executor) {
        this.executor = executor;
    }

    public void removeLast() {
        explorer.removeLast();
    }
}
