package explorer;

import tree.*;
import framework.*;

import java.util.ArrayList;

import java.util.concurrent.Semaphore;

public abstract class MinMaxAlgo {
    protected static final int MAX_VALUE = 100;
    protected static final int MIN_VALUE = -100;

    private ArrayList<MinMaxListener> listeners;
    private Semaphore semaphore;
    private int maxDepth;
    protected Game game;
    protected EvalFunction evalFct;

    public MinMaxAlgo(Game game, int maxDepth, EvalFunction evalFct) {
        this.game = game;
        this.maxDepth = maxDepth - 1;
        this.evalFct = evalFct;

        listeners = new ArrayList<MinMaxListener>();
    }

    public void addListener(MinMaxListener listener) {
        listeners.add(listener);
    }

    public void unlock() {
        if(semaphore != null && semaphore.availablePermits() == 0) {
            semaphore.release();
        }
    }

    public int maxDepth() {
        return maxDepth;
    }

    public abstract Move launchMinMax();

    public abstract void playMove(Move move, int indexOfMove);

    public abstract void removeMove(Move move, int indexOfMove, String label);

    public abstract int evalFunction(Player nodePlayer);

    protected void lock() {
        semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        }
        catch(InterruptedException e) {
            System.out.println("Trouble for request of semaphore acquirement");
            e.printStackTrace();
        }
    }

    protected void giveValueToListeners(String value) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setValueOfNode(value);
        }
    }

    protected void warnListenersOfNewBestNode(int index) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setImportantNode(index);
        }
    }

    protected void warnListenersOfDropNode(int index) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setDropedNode(index);
        }
    }

    protected void refreshTreeOfListener() {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).refreshTree();
        }
    }

    protected void warnListeners(Movement move, int indexOfMove) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).moved(move, indexOfMove);
        }
    }
}