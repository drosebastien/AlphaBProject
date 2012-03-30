package morpion;

import framework.*;
import java.util.ArrayList;

public class MorpionAI extends AI {
    public MorpionAI(String name, int id) {
        super(name, id);
    }

    public void run() {
        setPlayingMove(minimax());
        setIsFinalDecision();
        sem.release();
    }

    public Move minimax() {
        int maxValue = -200;
        int bestMoveIndex = 0;
        ArrayList<Move> listOfPossibleMoves =
                                game.getListOfPossibleMoves();
        for(int i = 0; i < listOfPossibleMoves.size(); i++) {
            try {
                game.play(listOfPossibleMoves.get(i));
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
            int value = minValue();
            game.removeMove(listOfPossibleMoves.get(i));
            if(value > maxValue) {
                maxValue = value;
                bestMoveIndex = i;
            }
        }
        System.out.println("maxValue " + maxValue);

        return listOfPossibleMoves.get(bestMoveIndex);
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
        ArrayList<Move> listOfPossibleMoves = game.getListOfPossibleMoves();
        for(int i = 0; i < listOfPossibleMoves.size(); i++) {
            try {
                game.play(listOfPossibleMoves.get(i));
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
            int value = maxValue();
            game.removeMove(listOfPossibleMoves.get(i));
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
        ArrayList<Move> listOfPossibleMoves = game.getListOfPossibleMoves();

        for(int i = 0; i < listOfPossibleMoves.size(); i++) {
            try {
                game.play(listOfPossibleMoves.get(i));
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
            int value = minValue();
            game.removeMove(listOfPossibleMoves.get(i));
            if(value > maxValue) {
                maxValue = value;
            }
        }

        return maxValue;
    }

    public Player clone() {
        Player playerCopy = new MorpionAI(getName(), getId());
        playerCopy.piece = piece.clone();
        return playerCopy;
    }
}
