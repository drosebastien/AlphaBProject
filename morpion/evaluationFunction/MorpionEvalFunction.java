package morpion.evaluationFunction;

import framework.*;
import explorer.*;
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

        Random gen = new Random();
        return gen.nextInt(99) - 49;
    }
}
