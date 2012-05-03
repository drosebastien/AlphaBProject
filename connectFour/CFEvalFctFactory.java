package connectFour;

import explorer.AbstractGameEvalFctFactory;
import explorer.RandomEvalFunction;
import explorer.EvalFunction;

import connectFour.evaluationFunction.*;

public class CFEvalFctFactory extends AbstractGameEvalFctFactory {
    private static CFEvalFctFactory instance;
    private static String[] buildableCFEvalFct;

    private CFEvalFctFactory() {
    }

    public static CFEvalFctFactory getInstance() {
        if(instance == null) {
            instance = new CFEvalFctFactory();
            initBuildabelEvalFct();
        }

        return instance;
    }

    private static void initBuildabelEvalFct() {
        buildableCFEvalFct = new String[] {CFEvalFunction.getName(),
                                          CFRemainingEvalFunction.getName(),
                                          CFRemainingDiffEvalFunction.getName(),
                                          CFExpEvalFunction.getName()};
    }

    public String[] getBuildableEvalFct() {
        return buildableCFEvalFct;
    }

    public EvalFunction getEvalFct(String name) {
        if(name.equals(buildableCFEvalFct[0]))
            return new CFEvalFunction();
        else if(name.equals(buildableCFEvalFct[1]))
            return new CFRemainingEvalFunction();
        else if(name.equals(buildableCFEvalFct[2]))
            return new CFRemainingDiffEvalFunction();
        else if(name.equals(buildableCFEvalFct[3]))
            return new CFExpEvalFunction();

        return new RandomEvalFunction();
    }

    public String getDescription(String name) {
        if(name.equals(buildableCFEvalFct[0]))
            return CFEvalFunction.getDescription();
        else if(name.equals(buildableCFEvalFct[1]))
            return CFRemainingEvalFunction.getDescription();
        else if(name.equals(buildableCFEvalFct[2]))
            return CFRemainingDiffEvalFunction.getDescription();
        else if(name.equals(buildableCFEvalFct[3]))
            return CFExpEvalFunction.getDescription();

        return RandomEvalFunction.getDescription();
    }
}
