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

        int bestValue = MIN_VALUE;
        int bestMoveIndex = 0;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        this.lock();
        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            int tmpValue = minValue(maxDepth() - 1);
            removeMove(listOfPossibleMove.get(i),i);

            if(tmpValue > bestValue) {
                bestMoveIndex = i;
                bestValue = tmpValue;
            }
        }

        return listOfPossibleMove.get(bestMoveIndex);
    }

    public int minValue(int depth) {
        if(game.isFinish() || depth == 0) {
            return evalFunction();
        }

        int bestValue = MAX_VALUE;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            int tmpValue = maxValue(depth - 1);
            removeMove(listOfPossibleMove.get(i),i);

            if(tmpValue < bestValue) {
                bestValue = tmpValue;
            }
        }

        return bestValue;
    }

    public int maxValue(int depth) {
        if(game.isFinish() || depth == 0) {
            return evalFunction();
        }

        int bestValue = MIN_VALUE;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            int tmpValue = minValue(depth - 1);
            removeMove(listOfPossibleMove.get(i),i);

            if(tmpValue > bestValue) {
                bestValue = tmpValue;
            }
        }

        return bestValue;
    }

    public void playMove(Move move, int indexOfMove) {
        game.play(move);

        warnListener(true, indexOfMove);
        this.lock();
    }

    public void removeMove(Move move, int indexOfMove) {
        game.removeMove(move);

        warnListener(false, indexOfMove);
        this.lock();
    }

    public int evalFunction() {
        return evalFct.evalFunction(game);
    }
}
