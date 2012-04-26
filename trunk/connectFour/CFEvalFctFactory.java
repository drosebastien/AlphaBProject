package morpion;

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
        buildableCFEvalFct = new String[] {
                                            CFEvalFunction.getName()};
    }

    public String[] getBuildableEvalFct() {
        return buildableCFEvalFct;
    }

    public EvalFunction getEvalFct(String name) {
        if(name.equals(buildableCFEvalFct[0]))
            return new CFEvalFunction();
        return new RandomEvalFunction();
    }
}
