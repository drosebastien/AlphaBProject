package explorer;

import framework.*;
import tree.*;

import java.util.ArrayList;

public class AlphaBeta extends MinMaxAlgo {

    public AlphaBeta(String name, Game game,
                                           int maxDepth, EvalFunction evalFct) {
        super(name, game, maxDepth, evalFct);
    }

    public Move launchMinMax() {
        if(game.isFinish() || maxDepth() == 0) {
            return null;
        }
        Player nodePlayer = game.nextPlayer();

        int alpha = getMinValue();
        int beta = getMaxValue();
        Move bestMove = null;
        MoveIterator iterator = game.getPossibleMoves();

        started();
        warnListeners(Movement.NEUTRAL, 0, new MinMaxEvent());
        giveValueToListeners("" + alpha, new MinMaxEvent());
        this.lock();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            giveValueToListeners("[" + alpha + ", " + beta + "]",
                                                            new MinMaxEvent());
            int tmpValue = minValue(maxDepth() - 1, nodePlayer, i, alpha, beta);
            removeMove(tmp, i, "" + tmpValue);

            if(alpha < tmpValue) {
                bestMove = tmp;
                MinMaxEvent evt = new MinMaxEvent("" + alpha + " < " +
                    tmpValue + ", the new bestValue is " + tmpValue);
                alpha = tmpValue;
                giveValueToListeners("" + alpha, evt);
                warnListenersOfNewBestNode(i, new MinMaxEvent());
            }
            else{
                warnListenersOfDropNode(i, new MinMaxEvent());
            }
            this.lock();

            i++;
        }

        MinMaxEvent evt = new MinMaxEvent();
        evt.setMessage("Root MinMax value is " + alpha);
        evt.setMove(bestMove);
        finished(evt);

        return bestMove;
    }

    public int minValue(int depth, Player nodePlayer, int index,
                        int alpha, int beta) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            MinMaxEvent evt = new MinMaxEvent("The leaf is evaluate to " +
                                                                        value);
            giveValueToListeners("" + value, evt);
            this.lock();
            return value;
        }

        this.lock();

        int bestValue = getMaxValue();
        MoveIterator iterator = game.getPossibleMoves();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            giveValueToListeners("[" + alpha + ", " + beta + "]",
                                                            new MinMaxEvent());
            int tmpValue = maxValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(tmp, i, "" + tmpValue);

            if(alpha >= tmpValue) {
                MinMaxEvent evt = new MinMaxEvent("MAX(" + alpha + ", MIN(" +
                            tmpValue + ", ...)) = " + alpha +
                            ", the search will be cut");
                giveValueToListeners(" " + alpha + " ≥ " + tmpValue, evt);
                this.lock();
                return tmpValue;
            }
            else {
                if(beta > tmpValue) {
                    MinMaxEvent evt = new MinMaxEvent("" + beta + " > " +
                        tmpValue +", the new value of beta is : " + tmpValue);

                    beta = tmpValue;
                    giveValueToListeners("[" + alpha + ", " + beta + "]",
                                                            new MinMaxEvent());
                    warnListenersOfNewBestNode(i, evt);
                }
                else {
                    MinMaxEvent evt = new MinMaxEvent("" + beta + " ≤ " +
                        tmpValue + ", the value of beta doesn't change");

                    warnListenersOfDropNode(i, evt);
                }
            }

            this.lock();

            i++;
        }

        return beta;
    }

    public int maxValue(int depth, Player nodePlayer, int index,
                        int alpha, int beta) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            MinMaxEvent evt = new MinMaxEvent("The leaf is evaluate to " +
                                                                        value);
            giveValueToListeners("" + value, evt);
            this.lock();
            return value;
        }

        this.lock();

        int bestValue = getMinValue();
        MoveIterator iterator = game.getPossibleMoves();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            giveValueToListeners("[" + alpha + ", " + beta + "]",
                                                            new MinMaxEvent());
            int tmpValue = minValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(tmp, i, "" + tmpValue);


            if(beta <= tmpValue) {
                MinMaxEvent evt = new MinMaxEvent("MIN(" + beta + ", MAX(" +
                            tmpValue + ", ...)) = " + beta +
                            ", the search will be cut");
                giveValueToListeners(" " + tmpValue + " ≥ " + beta, evt);
                this.lock();
                return tmpValue;
            }
            else {
                if(alpha < tmpValue) {
                    MinMaxEvent evt = new MinMaxEvent("" + alpha + " < " +
                        tmpValue +", the new value of alpha is : " + tmpValue);
                    alpha = tmpValue;
                    giveValueToListeners("[" + alpha + ", " + beta + "]", evt);
                    warnListenersOfNewBestNode(i, new MinMaxEvent());
                }
                else {
                    MinMaxEvent evt = new MinMaxEvent("" + alpha + " ≥ " +
                        tmpValue + ", the value of alpha doesn't change");
                    warnListenersOfDropNode(i, evt);
                }
            }

            this.lock();

            i++;
        }

        return alpha;
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
        return "Alpha Beta";
    }
}
