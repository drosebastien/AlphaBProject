package connectFour.evaluationFunction;

import framework.*;
import explorer.*;
import connectFour.*;

public class CFEvalFunction extends RandomEvalFunction {

    public static String getDescription() {
        return "* -50 for oppenent victory\n" +
               "* 50 for root player victory\n" +
               "* otherwise random value in ]-50, 50[";
    }

    public static String getName() {
        return "Random CF Fct";
    }
}
