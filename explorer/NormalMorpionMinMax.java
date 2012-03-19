package explorer;

import framework.*;
import morpion.*;
import tree.*;

import java.util.ArrayList;

public class NormalMorpionMinMax extends MinMaxAlgo {
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static final int MIN_VALUE = - Integer.MIN_VALUE;

    public NormalMorpionMinMax(Game game, int maxDepth, EvalFunction evalFct) {
        super(game, maxDepth, evalFct);

        if(!(game instanceof Morpion)) {
            // lancer une exception
        }
    }

    public Move launchMinMax() {
        if(game.isFinish() || maxDepth() == 0) {
            return null;
        }
        Player nodePlayer = game.nextPlayer();

        int bestValue = MIN_VALUE;
        int bestMoveIndex = 0;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        this.lock();
        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            int tmpValue = minValue(maxDepth() - 1, nodePlayer, i);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(tmpValue > bestValue) {
                bestMoveIndex = i;
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue);
            }
        }

        return listOfPossibleMove.get(bestMoveIndex);
    }

    public int minValue(int depth, Player nodePlayer, int index) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            giveValueToListeners("" + value);
            return value;
        }

        int bestValue = MAX_VALUE;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            int tmpValue = maxValue(depth - 1, nodePlayer, i);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(tmpValue < bestValue) {
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue);
            }
        }

        return bestValue;
    }

    public int maxValue(int depth, Player nodePlayer, int index) {
        if(game.isFinish() || depth == 0) {
            int value = evalFunction(nodePlayer);
            giveValueToListeners("" + value);
            return value;
        }

        int bestValue = MIN_VALUE;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            int tmpValue = minValue(depth - 1, nodePlayer, i);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(tmpValue > bestValue) {
                bestValue = tmpValue;
                giveValueToListeners("" + bestValue);
            }
        }

        return bestValue;
    }

    public void playMove(Move move, int indexOfMove) {
        game.play(move);
        warnListeners(true, indexOfMove);
        giveValueToListeners("x");

        this.lock();
    }

    public void removeMove(Move move, int indexOfMove, String label) {
        game.removeMove(move);

        this.lock();
        warnListeners(false, indexOfMove);
    }

    public int evalFunction(Player player) {
        return evalFct.evalFunction(game, player);
    }
}
