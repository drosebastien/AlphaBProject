package explorer;

import tree.*;
import framework.*;

import java.util.ArrayList;

public abstract class MinMaxAlgo {
    private ArrayList<MinMaxListener> listeners;
    protected int maxDepth;
    protected Game game;
    protected EvalFunction evalFct;

    public MinMaxAlgo(Game game, int maxDepth, EvalFunction evalFct) {
        this.game = game;
        this.maxDepth = maxDepth;
        this.evalFct = evalFct;

        listeners = new ArrayList<MinMaxListener>();
    }

    public abstract Move launchMinMax();

    public void addListener(MinMaxListener listener) {
        listeners.add(listener);
    }

    public abstract void playMove(Move move);

    public abstract void removeMove(Move move);

    public abstract int evalFunction();
}
