package explorer;

import framework.*;
import morpion.*;
import tree.*;

import java.util.ArrayList;

public class NormalMorpionMinMax extends MinMaxAlgo {

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

        warnListeners(Movement.NEUTRAL, 0);
        giveValueToListeners("x");
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
            this.lock();
        }

        return listOfPossibleMove.get(bestMoveIndex);
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
            this.lock();
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
            this.lock();
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
