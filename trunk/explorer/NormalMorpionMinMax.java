package explorer;

import framework.*;
import morpion.*;
import tree.*;

public class NormalMorpionMinMax extends MinMaxAlgo {

    public NormalMorpionMinMax(Game game, int maxDepth, EvalFunction evalFct) {
        super(game, maxDepth, evalFct);

        if(!(game instanceof Morpion)) {
            // lancer une exception
        }
    }

    public Move launchMinMax() {
        return null;
    }

    public void playMove(Move move) {
        //moi je ne sert encore à rien
    }

    public void removeMove(Move move) {
        //moi je ne sert encore à rien
    }

    public int evalFunction() {
        return 0; //moi je ne sert encore à rien
    }
}
