package explorer;

import framework.*;
import tree.*;

import java.util.ArrayList;

public class IterativeMinMax extends MinMaxAlgo {

    public IterativeMinMax(String name, Game game,
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
            bestMove = null;
            warnListeners(Movement.NEUTRAL, 0);
            giveValueToListeners("" + getMinValue());
            MoveIterator iterator = game.getPossibleMoves();
            int bestValue = getMinValue();
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

        int bestValue = getMaxValue();
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

        int bestValue = getMinValue();
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
        try {
            game.play(move);
        }
        catch(MoveException e) {
            e.printStackTrace();
        }
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

    public static String getAlgoName() {
        return "IDDFS MinMax";
    }
}
