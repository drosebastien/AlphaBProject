package connectFour.evaluationFunction;

import connectFour.evaluationFunction.tools.C4Analyser;
import connectFour.*;
import framework.*;
import explorer.*;

import java.util.ArrayList;

public class CFRemainingDiffEvalFunction extends CFAbstractEvalFunction {

    public int evalFunction() {
        int count = 0;

        for(int i = 0; i < 69; i++) {
            if(analyser.getPlayer1Value(i) >= 4) {
                if(getGame().getListOfPlayer().get(0).equals(getPlayer()))
                    return MAX;
                else
                    return -MAX;
            }
            else if(analyser.getPlayer2Value(i) >= 4) {
                if(getGame().getListOfPlayer().get(0).equals(getPlayer()))
                    return -MAX;
                else
                    return MAX;
            }
            if(analyser.getPlayer1Value(i) >= 0) {
                count++;
            }
            if(analyser.getPlayer2Value(i) >= 0) {
                count--;
            }
        }

        if(getGame().getListOfPlayer().get(0).equals(getPlayer()))
            return count;
        else
            return -count;
    }

    public static String getDescription() {
        return "diff # Remaining CF";
    }

    public static String getName() {
        return "diff # Remaining CF";
    }
}
