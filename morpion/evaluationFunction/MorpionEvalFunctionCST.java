package morpion.evaluationFunction;

import framework.*;
import explorer.*;
import morpion.*;
import java.util.Random;

public class MorpionEvalFunctionCST extends RandomEvalFunction {

    public int evalFunction(Game game, Player player) {
        return 10;
    }

    public static String getName() {
        return "Morpion Constante";
    }
}
