package explorer;

import framework.*;
import morpion.*;
import java.util.Random;

public class MorpionEvalFunction implements EvalFunction {

    public int evalFunction(Game game, Player player) {
        if(! (game instanceof Morpion)) {
            // mauvais jeu.
        }

        if(game.isFinish() && game.isVictory() && game.getWinner().equals(player)) {
            return 50;
        }
        else if(game.isFinish() && game.isVictory()) {
            return -50;
        }
        MorpionPiece playerPiece = (MorpionPiece) player.getPiece();
        MorpionBoard board = (MorpionBoard) game.getBoard();
        int cpt = 0;

        for(int i = 0; i < board.getWidth(); i++) {
            for(int j = 0; j < board.getWidth(); j++) {
                MorpionPosition pos = new MorpionPosition(i, j);

                if(board.getPiece(pos) == playerPiece) {
                    cpt++;
                }
            }
        }

        Random gen = new Random();
        return gen.nextInt(100) - 50;
    }
}
