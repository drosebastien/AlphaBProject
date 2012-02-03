package morpion;

import framework.*;
import java.util.ArrayList;

public class AIMorpion extends AI {
    public AIMorpion(String name, int id) {
        super(name, id);
    }

    public void run() {
        setPlayingMove(minimax());
        setIsFinalDecision();
    }

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

    public Player clone() {
        Player playerCopy = new AIMorpion(name, id);
        playerCopy.piece = piece.clone();
        return playerCopy;
    }
}
