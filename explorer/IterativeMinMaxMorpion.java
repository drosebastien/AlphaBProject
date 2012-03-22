package explorer;

import framework.*;
import morpion.*;
import tree.*;

import java.util.ArrayList;

public class IterativeMinMaxMorpion extends MinMaxAlgo {
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static final int MIN_VALUE = - Integer.MIN_VALUE;

    public IterativeMinMaxMorpion(Game game, int maxDepth,
                                  EvalFunction evalFct) {
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

        ArrayList<Move> listOfPossibleMove =
                                    game.getListOfPossibleMove();

        int bestMoveIndex = 0;

        for(int j = 1; j <= maxDepth(); j++) {
            int bestValue = MIN_VALUE;
            bestMoveIndex = 0;

            this.lock();
            for(int i = 0; i < listOfPossibleMove.size(); i++) {
                playMove(listOfPossibleMove.get(i), i);
                int tmpValue = minValue(j - 1, nodePlayer, i);
                removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

                if(tmpValue > bestValue) {
                    bestMoveIndex = i;
                    bestValue = tmpValue;
                    giveValueToListeners("" + bestValue);
                }
            }
            refreshTreeOfListener();
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
        warnListeners(Movement.FORWARD, indexOfMove);
        giveValueToListeners("x");

        this.lock();
    }

    public void removeMove(Move move, int indexOfMove, String label) {
        game.removeMove(move);

        this.lock();
        warnListeners(Movement.BACKWARD, indexOfMove);
    }

    public int evalFunction(Player player) {
        return evalFct.evalFunction(game, player);
    }
}
