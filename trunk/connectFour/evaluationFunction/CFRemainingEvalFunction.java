package connectFour.evaluationFunction;

import connectFour.evaluationFunction.tools.C4Analyser;
import connectFour.*;
import framework.*;
import explorer.*;

import java.util.ArrayList;

public class CFRemainingEvalFunction extends CFAbstractEvalFunction {

    public int evalFunction() {
        int count = 0;
        if(getGame().getListOfPlayer().get(0).equals(getPlayer())) {
            for(int i = 0; i < 69; i++) {
                if(analyser.getPlayer1Value(i) >= 4) {
                    return MAX;
                }
                else if(analyser.getPlayer2Value(i) >= 4) {
                    return -MAX;
                }
                else if(analyser.getPlayer1Value(i) >= 0) {
                    count++;
                }
            }
        }
        else{
            for(int i = 0; i < 69; i++) {
                if(analyser.getPlayer1Value(i) >= 4) {
                    return -MAX;
                }
                else if(analyser.getPlayer2Value(i) >= 4) {
                    return MAX;
                }
                else if(analyser.getPlayer2Value(i) >= 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static String getName() {
        return "# remaining CF";
    }
}
