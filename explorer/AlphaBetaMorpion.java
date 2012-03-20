package explorer;

import framework.*;
import morpion.*;
import tree.*;

import java.util.ArrayList;

public class AlphaBetaMorpion extends MinMaxAlgo {
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static final int MIN_VALUE = - Integer.MIN_VALUE;

    public AlphaBetaMorpion(Game game, int maxDepth, EvalFunction evalFct) {
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

        int alpha = MIN_VALUE;
        int beta = MAX_VALUE;
        int bestMoveIndex = 0;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        this.lock();
        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            int tmpValue = minValue(maxDepth() - 1, nodePlayer, i, alpha, beta);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(alpha < tmpValue) {
                bestMoveIndex = i;
                alpha = tmpValue;
                giveValueToListeners("" + alpha);
            }
        }

        return listOfPossibleMove.get(bestMoveIndex);
    }

    public int minValue(int depth, Player nodePlayer, int index,
                        int alpha, int beta) {
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
            int tmpValue = maxValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(beta > tmpValue) {
                beta = tmpValue;
            }
            giveValueToListeners("" + beta);
            if(beta <= alpha) {
                giveValueToListeners("" + beta);
                return beta;
            }
        }

        return beta;
    }

    public int maxValue(int depth, Player nodePlayer, int index,
                        int alpha, int beta) {
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
            int tmpValue = minValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(alpha < tmpValue) {
                alpha = tmpValue;
            }
            giveValueToListeners("" + alpha);
            if(alpha >= beta) {
                giveValueToListeners("" + alpha);
                return alpha;
            }
        }

        return alpha;
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
