package explorer;

import framework.*;
import tree.*;

import java.util.ArrayList;

public class NormalMinMax extends MinMaxAlgo {

    public NormalMinMax(String name, Game game,
                                           int maxDepth, EvalFunction evalFct) {
        super(name, game, maxDepth, evalFct);
    }

    public Move launchMinMax() {
        if(game.isFinish() || maxDepth() == 0) {
            return null;
        }
        Player nodePlayer = game.nextPlayer();

        int bestValue = getMinValue();
        Move bestMove = null;
        MoveIterator iterator = game.getPossibleMoves();

        started();
        warnListeners(Movement.NEUTRAL, 0, new MinMaxEvent());
        giveValueToListeners("" + getMinValue(), new MinMaxEvent());
        this.lock();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            int tmpValue = minValue(maxDepth() - 1, nodePlayer, i);
            removeMove(tmp, i, "" + tmpValue);

            if(tmpValue > bestValue) {
                MinMaxEvent evt = new MinMaxEvent(tmpValue + " > " + bestValue +
                    ", The new best value of this MAX Node is " + tmpValue);
                bestMove = tmp;
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue, new MinMaxEvent());
                warnListenersOfNewBestNode(i, evt);
            }
            else {
                MinMaxEvent evt = new MinMaxEvent(tmpValue + " ≤ " + bestValue +
                    ", The best Value doesn't change");
                warnListenersOfDropNode(i, evt);
            }
            this.lock();

            i++;
        }

        return bestMove;
    }

    public int minValue(int depth, Player nodePlayer, int index) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            MinMaxEvent evt = new MinMaxEvent("The node is evaluate to " +
                                                                        value);
            giveValueToListeners("" + value, evt);
            this.lock();
            return value;
        }

        int bestValue = getMaxValue();
        giveValueToListeners("" + bestValue, new MinMaxEvent());

        this.lock();

        MoveIterator iterator = game.getPossibleMoves();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            int tmpValue = maxValue(depth - 1, nodePlayer, i);
            removeMove(tmp, i, "" + tmpValue);

            if(tmpValue < bestValue) {
                MinMaxEvent evt = new MinMaxEvent(tmpValue + " < " + bestValue +
                    ", The new best value of this Min Node is " + tmpValue);
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue, new MinMaxEvent());
                warnListenersOfNewBestNode(i, evt);
            }
            else {
                MinMaxEvent evt = new MinMaxEvent(tmpValue + " ≥ " + bestValue +
                    ", The best Value doesn't change");
                warnListenersOfDropNode(i, evt);
            }
            this.lock();

            i++;
        }

        return bestValue;
    }

    public int maxValue(int depth, Player nodePlayer, int index) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            MinMaxEvent evt = new MinMaxEvent("The node is evaluate to " +
                                                                        value);
            giveValueToListeners("" + value, evt);
            this.lock();
            return value;
        }

        int bestValue = getMinValue();
        giveValueToListeners("" + bestValue, new MinMaxEvent());

        this.lock();

        MoveIterator iterator = game.getPossibleMoves();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            int tmpValue = minValue(depth - 1, nodePlayer, i);
            removeMove(tmp, i, "" + tmpValue);

            if(tmpValue > bestValue) {
                MinMaxEvent evt = new MinMaxEvent(tmpValue + " > " + bestValue +
                    ", The new best value of this MAX Node is " + tmpValue);
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue, new MinMaxEvent());
                warnListenersOfNewBestNode(i, evt);
            }
            else {
                MinMaxEvent evt = new MinMaxEvent(tmpValue + " ≤ " + bestValue +
                    ", The best Value doesn't change");
                warnListenersOfDropNode(i, evt);
            }
            this.lock();

            i++;
        }

        return bestValue;
    }

    public void playMove(Move move, int indexOfMove) {
        try {
            game.play(move);
        }
        catch(MoveException e) {
            e.printStackTrace();
        }
        MinMaxEvent evt = new MinMaxEvent();
        evt.setMove(move);
        warnListeners(Movement.FORWARD, indexOfMove, evt);
    }

    public void removeMove(Move move, int indexOfMove, String label) {
        MinMaxEvent evt = new MinMaxEvent();
        evt.setMove(move);
        warnListeners(Movement.BACKWARD, indexOfMove, evt);
        game.removeMove(move);
    }

    public int evalFunction(Player player) {
        evalFct.setPlayer(player);
        return evalFct.evalFunction();
    }

    public static String getAlgoName() {
        return "MinMax";
    }
}
