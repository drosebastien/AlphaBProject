package explorer;

import framework.*;
import morpion.*;
import tree.*;

import java.util.ArrayList;

public class AlphaBetaMorpion extends MinMaxAlgo {

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

        warnListeners(Movement.NEUTRAL, 0);
        giveValueToListeners("[-i, i]");
        this.lock();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            giveValueToListeners("[" + alpha + ", " + beta + "]");
            int tmpValue = minValue(maxDepth() - 1, nodePlayer, i, alpha, beta);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(alpha < tmpValue) {
                bestMoveIndex = i;
                alpha = tmpValue;
                giveValueToListeners("[" + alpha + ", i]");
            }
            this.lock();
        }

        return listOfPossibleMove.get(bestMoveIndex);
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
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            giveValueToListeners("[" + alpha + ", " + beta + "]"); // beta = bestvalue
            int tmpValue = maxValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(beta > tmpValue) {
                beta = tmpValue;
                giveValueToListeners("[" + alpha + ", " + beta + "]");
            }
            if(beta <= alpha) {
                this.lock();
                return beta;
            }
            this.lock();
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
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            giveValueToListeners("[" + alpha + ", " + beta + "]"); // alpha = bestValue
            int tmpValue = minValue(depth - 1, nodePlayer, i, alpha, beta);
            removeMove(listOfPossibleMove.get(i), i, "" + tmpValue);

            if(alpha < tmpValue) {
                alpha = tmpValue;
                giveValueToListeners("[" + alpha + ", " + beta + "]");
            }
            if(alpha >= beta) {
                this.lock();
                return alpha;
            }
            this.lock();
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
