package explorer;

import framework.*;

import java.util.Random;

public class RandomEvalFunction extends EvalFunction {

    public static final int MAXVALUE = 50;
    public static final int MINVALUE = -50;
    private Random gen;

    public RandomEvalFunction() {
        gen = new Random();
    }

    public int evalFunction(Game game, Player player) {

        if(game.isFinish() && game.isVictory() &&
           game.getWinner().equals(player)) {
            return MAXVALUE;
        }
        else if(game.isFinish() && game.isVictory()) {
            return MINVALUE;
        }

        return gen.nextInt(99) - 49;
    }

    public static String getName() {
        return "Random";
    }
}
