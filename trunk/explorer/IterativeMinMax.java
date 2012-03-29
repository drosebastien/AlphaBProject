package explorer;

import framework.*;
import tree.*;

import java.util.ArrayList;

public class IterativeMinMax extends MinMaxAlgo {

    public IterativeMinMax(Game game, int maxDepth, EvalFunction evalFct) {
        super(game, maxDepth, evalFct);
    }

    public Move launchMinMax() {
        if(game.isFinish() || maxDepth() == 0) {
            return null;
        }
        Player nodePlayer = game.nextPlayer();

        Move bestMove = null;

        for(int j = 1; j <= maxDepth(); j++) {
            bestMove = null;
            warnListeners(Movement.NEUTRAL, 0);
            giveValueToListeners("" + MIN_VALUE);
            MoveIterator iterator = game.getPossibleMoves();
            int bestValue = MIN_VALUE;
            this.lock();

            int i = 0;
            while(iterator.hasNext()) {
                Move tmp = iterator.next();
                playMove(tmp, i);
                int tmpValue = minValue(j - 1, nodePlayer, i);
                removeMove(tmp, i, "" + tmpValue);

                if(tmpValue > bestValue) {
                    bestMove = tmp;
                    bestValue = tmpValue;
                    giveValueToListeners("" + bestValue);
                }
                this.lock();

                i++;
            }

            refreshTreeOfListener();
        }

        return bestMove;
    }

    public int minValue(int depth, Player nodePlayer, int index) {
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
            int tmpValue = maxValue(depth - 1, nodePlayer, i);
            removeMove(tmp, i, "" + tmpValue);

            if(tmpValue < bestValue) {
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue);
            }
            this.lock();

            i++;
        }

        return bestValue;
    }

    public int maxValue(int depth, Player nodePlayer, int index) {
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
            int tmpValue = minValue(depth - 1, nodePlayer, i);
            removeMove(tmp, i, "" + tmpValue);

            if(tmpValue > bestValue) {
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue);
            }
            this.lock();

            i++;
        }

        return bestValue;
    }

    public void playMove(Move move, int indexOfMove) {
        game.play(move);
        warnListeners(Movement.FORWARD, indexOfMove);
        giveValueToListeners("x");
    }

    public void removeMove(Move move, int indexOfMove, String label) {
        warnListeners(Movement.BACKWARD, indexOfMove);
        game.removeMove(move);
    }

    public int evalFunction(Player player) {
        return evalFct.evalFunction(game, player);
    }
}
