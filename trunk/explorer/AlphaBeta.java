package explorer;

import framework.*;
import tree.*;

import java.util.ArrayList;

public class AlphaBeta extends MinMaxAlgo {

    public AlphaBeta(Game game, int maxDepth, EvalFunction evalFct) {
        super(game, maxDepth, evalFct);
    }

    public Move launchMinMax() {
        if(game.isFinish() || maxDepth() == 0) {
            return null;
        }
        Player nodePlayer = game.nextPlayer();

        int alpha = MIN_VALUE;
        int beta = MAX_VALUE;
        Move bestMove = null;
        MoveIterator iterator = game.getPossibleMoves();

        warnListeners(Movement.NEUTRAL, 0);
        giveValueToListeners("" + MIN_VALUE);
        this.lock();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            giveValueToListeners("[" + alpha + ", " + beta + "]");
            int tmpValue = minValue(maxDepth() - 1, nodePlayer, i, alpha, beta);
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

        int bestValue = MAX_VALUE;
        MoveIterator iterator = game.getPossibleMoves();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            giveValueToListeners("[" + alpha + ", " + beta + "]");
            int tmpValue = maxValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(tmp, i, "" + tmpValue);

            if(beta > tmpValue) {
                beta = tmpValue;
                giveValueToListeners("[" + alpha + ", " + beta + "]");
                warnListenersOfNewBestNode(i);
            }
            else{
                warnListenersOfDropNode(i);
            }
            if(beta <= alpha) {
                this.lock();
                return beta;
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

        int bestValue = MIN_VALUE;
        MoveIterator iterator = game.getPossibleMoves();

        int i = 0;
        while(iterator.hasNext()) {
            Move tmp = iterator.next();
            playMove(tmp, i);
            giveValueToListeners("[" + alpha + ", " + beta + "]");
            int tmpValue = minValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(tmp, i, "" + tmpValue);

            if(alpha < tmpValue) {
                alpha = tmpValue;
                giveValueToListeners("[" + alpha + ", " + beta + "]");
                warnListenersOfNewBestNode(i);
            }
            else{
                warnListenersOfDropNode(i);
            }
            if(alpha >= beta) {
                this.lock();
                return alpha;
            }

            this.lock();

            i++;
        }

        return alpha;
    }

    public void playMove(Move move, int indexOfMove) {
        game.play(move);
        warnListeners(Movement.FORWARD, indexOfMove);
    }

    public void removeMove(Move move, int indexOfMove, String label) {
        warnListeners(Movement.BACKWARD, indexOfMove);
        game.removeMove(move);
    }

    public int evalFunction(Player player) {
        return evalFct.evalFunction(game, player);
    }
}
