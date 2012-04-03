package explorer;

import framework.*;
import tree.*;

import java.util.ArrayList;

public class IterativeAlphaBeta extends MinMaxAlgo {

    public IterativeAlphaBeta(String name, Game game,
                                           int maxDepth, EvalFunction evalFct) {
        super(name, game, maxDepth, evalFct);
    }

    public Move launchMinMax() {
        if(game.isFinish() || maxDepth() == 0) {
            return null;
        }
        Player nodePlayer = game.nextPlayer();

        Move bestMove = null;
        for(int j = 1; j <= maxDepth(); j++) {
            int alpha = getMinValue();
            int beta = getMaxValue();
            bestMove = null;
            MoveIterator iterator = game.getPossibleMoves();

            warnListeners(Movement.NEUTRAL, 0);
            giveValueToListeners("" + alpha);
            this.lock();

            int i = 0;
            while(iterator.hasNext()) {
                Move tmp = iterator.next();
                playMove(tmp, i);
                giveValueToListeners("[" + alpha + ", " + beta + "]");
                int tmpValue = minValue(j - 1, nodePlayer, i,
                                                               alpha, beta);
                removeMove(tmp, i, "" + tmpValue);

                if(alpha < tmpValue) {
                    bestMove = tmp;
                    alpha = tmpValue;
                    giveValueToListeners("" + alpha);
                    warnListenersOfNewBestNode(i);
                }
                else{
                    warnListenersOfDropNode(i);
                }
                this.lock();

                i++;
            }

            refreshTreeOfListener();
        }

        return bestMove;
    }

    public int minValue(int depth, Player nodePlayer, int index,
                        int alpha, int beta) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            giveValueToListeners("" + value);
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
            giveValueToListeners("[" + alpha + ", " + beta + "]");
            int tmpValue = maxValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(tmp, i, "" + tmpValue);

            if(alpha >= tmpValue) {
                giveValueToListeners(" " + alpha + " ≥ " + tmpValue);
                this.lock();
                return tmpValue;
            }
            else {
                if(beta > tmpValue) {
                    beta = tmpValue;
                    giveValueToListeners("[" + alpha + ", " + beta + "]");
                    warnListenersOfNewBestNode(i);
                }
                else {
                    warnListenersOfDropNode(i);
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
            giveValueToListeners("" + value);
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
            giveValueToListeners("[" + alpha + ", " + beta + "]");
            int tmpValue = minValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(tmp, i, "" + tmpValue);


            if(beta <= tmpValue) {
                giveValueToListeners(" " + tmpValue + " ≥ " + beta);
                this.lock();
                return tmpValue;
            }
            else {
                if(alpha < tmpValue) {
                    alpha = tmpValue;
                    giveValueToListeners("[" + alpha + ", " + beta + "]");
                    warnListenersOfNewBestNode(i);
                }
                else {
                    warnListenersOfDropNode(i);
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
        warnListeners(Movement.FORWARD, indexOfMove);
    }

    public void removeMove(Move move, int indexOfMove, String label) {
        warnListeners(Movement.BACKWARD, indexOfMove);
        game.removeMove(move);
    }

    public int evalFunction(Player player) {
        return evalFct.evalFunction(game, player);
    }

    public static String getAlgoName() {
        return "IDDFS Alpha Beta";
    }
}
