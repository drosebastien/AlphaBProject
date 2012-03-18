package explorer;

import framework.*;
import morpion.*;
import tree.*;

import java.util.ArrayList;

public class NormalMorpionMinMax extends MinMaxAlgo {
    private int maxValue = Integer.MAX_VALUE;

    public NormalMorpionMinMax(Game game, int maxDepth, EvalFunction evalFct) {
        super(game, maxDepth, evalFct);

        if(!(game instanceof Morpion)) {
            // lancer une exception
        }

        System.out.println("initialisation de l'ia morpion : check");
    }

    public Move launchMinMax() {
        int bestMoveIndex = 0;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();

        this.lock();
        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            playMove(listOfPossibleMove.get(i), i);
            removeMove(listOfPossibleMove.get(i),i);
        }
        return null;
    }

/*
        public Move minimax() {
        int maxValue = -200;
        int bestMoveIndex = 0;
        ArrayList<Move> listOfPossibleMove =
                                game.getListOfPossibleMove();
        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            game.play(listOfPossibleMove.get(i));
            int value = minValue();
            game.removeMove(listOfPossibleMove.get(i));
            if(value > maxValue) {
                maxValue = value;
                bestMoveIndex = i;
            }
        }
        System.out.println("maxValue " + maxValue);

        return listOfPossibleMove.get(bestMoveIndex);
    }

    public int minValue() {
        if(game.isFinish()) {
            if(game.isVictory()) {
                return 100;
            }
            else {
                return 0;
            }
        }
        int minValue = 200;
        ArrayList<Move> listOfPossibleMove = game.getListOfPossibleMove();
        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            game.play(listOfPossibleMove.get(i));
            int value = maxValue();
            game.removeMove(listOfPossibleMove.get(i));
            if(value < minValue) {
                minValue = value;
            }
        }

        return minValue;
    }

    public int maxValue() {
        if(game.isFinish()) {
            if(game.isVictory()) {
                return -100;
            }
            else {
                return 0;
            }
        }
        int maxValue = -200;
        ArrayList<Move> listOfPossibleMove = game.getListOfPossibleMove();

        for(int i = 0; i < listOfPossibleMove.size(); i++) {
            game.play(listOfPossibleMove.get(i));
            int value = minValue();
            game.removeMove(listOfPossibleMove.get(i));
            if(value > maxValue) {
                maxValue = value;
            }
        }

        return maxValue;
    }
*/

    public void playMove(Move move, int indexOfMove) {
        System.out.println("playMove");
        game.play(move);

        warnListener(true, indexOfMove);
        this.lock();
    }

    public void removeMove(Move move, int indexOfMove) {
        System.out.println("removeMove");
        game.removeMove(move);

        warnListener(false, indexOfMove);
        this.lock();
    }

    public int evalFunction() {
        return 0; //je ne sert encore à rien
    }
}
