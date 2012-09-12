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
        Move bestMove = null;
        MoveIterator possibleMoves = game.getPossibleMoves();
        for(int i = 0; possibleMoves.hasNext(); i++) {
            Move move = possibleMoves.next();
            try {
                game.play(move);
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
            int value = minValue();
            game.removeMove(move);
            if(value > maxValue) {
                maxValue = value;
                bestMove = move;
            }
        }
        System.out.println("maxValue " + maxValue);

        return bestMove;
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
        MoveIterator possibleMoves = game.getPossibleMoves();
        for(int i = 0; possibleMoves.hasNext(); i++) {
            Move move = possibleMoves.next();
            try {
                game.play(move);
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
            int value = maxValue();
            game.removeMove(move);
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
        MoveIterator possibleMoves = game.getPossibleMoves();

        for(int i = 0; possibleMoves.hasNext(); i++) {
            Move move = possibleMoves.next();
            try {
                game.play(move);
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
            int value = minValue();
            game.removeMove(move);
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
