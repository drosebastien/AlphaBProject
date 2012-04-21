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
            int bestValue = getMinValue();
            bestMove = null;
            MoveIterator iterator = game.getPossibleMoves();

            warnListeners(Movement.NEUTRAL, 0, new MinMaxEvent());
            giveValueToListeners("" + getMinValue(), new MinMaxEvent());
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
                    giveValueToListeners("" + bestValue, new MinMaxEvent());
                    warnListenersOfNewBestNode(i, new MinMaxEvent());
                }
                else {
                    warnListenersOfDropNode(i, new MinMaxEvent());
                }
                this.lock();

                i++;
            }
            refreshTreeOfListener(new MinMaxEvent());
        }

        return bestMove;
    }

    public int minValue(int depth, Player nodePlayer, int index) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            giveValueToListeners("" + value, new MinMaxEvent());
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
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue, new MinMaxEvent());
                warnListenersOfNewBestNode(i, new MinMaxEvent());
            }
            else {
                warnListenersOfDropNode(i, new MinMaxEvent());
            }
            this.lock();

            i++;
        }

        return bestValue;
    }

    public int maxValue(int depth, Player nodePlayer, int index) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            giveValueToListeners("" + value, new MinMaxEvent());
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
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue, new MinMaxEvent());
                warnListenersOfNewBestNode(i, new MinMaxEvent());
            }
            else {
                warnListenersOfDropNode(i, new MinMaxEvent());
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
        warnListeners(Movement.FORWARD, indexOfMove, new MinMaxEvent());
    }

    public void removeMove(Move move, int indexOfMove, String label) {
        warnListeners(Movement.BACKWARD, indexOfMove, new MinMaxEvent());
        game.removeMove(move);
    }

    public int evalFunction(Player player) {
        return evalFct.evalFunction(game, player);
    }

    public static String getAlgoName() {
        return "IDDFS MinMax";
    }
}
