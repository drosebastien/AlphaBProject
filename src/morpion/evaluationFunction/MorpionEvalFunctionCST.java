package morpion.evaluationFunction;

import framework.*;
import explorer.*;
import morpion.*;
import java.util.Random;

public class MorpionEvalFunctionCST extends RandomEvalFunction {

    public int evalFunction() {
        //MorpionPiece piece = (Piece) player.getPiece();
        if(getGame().isFinish() && getGame().isVictory() &&
           getGame().getWinner().equals(getPlayer())) {
            return MAXVALUE;
        }
        else if(getGame().isFinish() && getGame().isVictory()) {
            return MINVALUE;
        }

        return 10;
    }

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event) {
        if(move == Movement.FORWARD)
            System.out.println("MorpionCST, joue : " + indexInTreeGame);
        else if(move == Movement.BACKWARD)
            System.out.println("MorpionCST, retire : " + indexInTreeGame);
    }

    public static String getDescription() {
        return "Morpion Constant";
    }

    public static String getName() {
        return "Morpion Constant";
    }
}
