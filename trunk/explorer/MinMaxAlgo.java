package explorer;

import tree.*;
import framework.*;

import java.util.ArrayList;

import java.util.concurrent.Semaphore;

public abstract class MinMaxAlgo {
    private int maxValue = 999;
    private int minValue = -999;

    private ArrayList<MinMaxListener> listeners;
    private Semaphore semaphore;
    private String name;
    private int maxDepth;
    protected Game game;
    protected EvalFunction evalFct;

    public MinMaxAlgo(String name, Game game,
                      int maxDepth, EvalFunction evalFct) {

        this.name = name;
        this.game = game;
        this.maxDepth = maxDepth - 1;
        this.evalFct = evalFct;

        listeners = new ArrayList<MinMaxListener>();
        evalFct.setGame(game);
        evalFct.setPlayer(game.nextPlayer());
        listeners.add(evalFct);
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public void setEvalFunction(EvalFunction evalFct) {
        this.evalFct = evalFct;
        this.evalFct.setGame(game);
        this.evalFct.setPlayer(game.nextPlayer());
        listeners.set(0, evalFct);
    }

    public EvalFunction getEvalFunction() {
        return evalFct;
    }

    public void removeListeners() {
        listeners = new ArrayList<MinMaxListener>();
        listeners.add(evalFct);
    }

    public void addListener(MinMaxListener listener) {
        listeners.add(listener);
    }

    public void unlock() {
        if(semaphore != null && semaphore.availablePermits() == 0) {
            semaphore.release();
        }
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int maxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth - 1;
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

    protected void giveValueToListeners(String value, MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setValueOfNode(value, evt);
        }
    }

    protected void warnListenersOfNewBestNode(int index, MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setNewBestNode(index, evt);
        }
    }

    protected void warnListenersOfDropNode(int index, MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setDropedNode(index, evt);
        }
    }

    protected void refreshTreeOfListener(MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).refreshTree(evt);
        }
    }

    protected void started() {
        for(MinMaxListener listener : listeners) {
            listener.started();
        }
    }

    protected void warnListeners(Movement move, int indexOfMove,
                                                        MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).moved(move, indexOfMove, evt);
        }
    }

    public String toString() {
        return name;
    }
}
