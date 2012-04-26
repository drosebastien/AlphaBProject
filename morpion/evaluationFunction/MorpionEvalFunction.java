package morpion.evaluationFunction;

import framework.*;
import explorer.*;
import morpion.*;
import java.util.Random;

public class MorpionEvalFunction extends RandomEvalFunction {

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event) {
        if(move == Movement.FORWARD)
            System.out.println("Morpion, joue : " + indexInTreeGame);
        else if(move == Movement.BACKWARD)
            System.out.println("Morpion, retire : " + indexInTreeGame);
    }

    public static String getName() {
        return "Morpion Random";
    }
}
