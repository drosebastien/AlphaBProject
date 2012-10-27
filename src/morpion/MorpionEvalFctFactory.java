package morpion;

import explorer.AbstractGameEvalFctFactory;
import explorer.RandomEvalFunction;
import explorer.EvalFunction;
import morpion.evaluationFunction.*;

public class MorpionEvalFctFactory extends AbstractGameEvalFctFactory {
    private static MorpionEvalFctFactory instance;
    private static String[] buildableMorpionEvalFct;

    private MorpionEvalFctFactory() {
    }

    public static MorpionEvalFctFactory getInstance() {
        if(instance == null) {
            instance = new MorpionEvalFctFactory();
            initBuildabelEvalFct();
        }

        return instance;
    }

    private static void initBuildabelEvalFct() {
        buildableMorpionEvalFct = new String[] {
                                            RandomEvalFunction.getName(),
                                            MorpionEvalFunctionCST.getName()};
    }

    public String[] getBuildableEvalFct() {
        return buildableMorpionEvalFct;
    }

    public EvalFunction getEvalFct(String name) {
        if(name.equals(buildableMorpionEvalFct[0]))
            return new RandomEvalFunction();
        else if(name.equals(buildableMorpionEvalFct[1]))
            return new MorpionEvalFunctionCST();
        return new RandomEvalFunction();
    }

    public String getDescription(String name) {
        if(name.equals(buildableMorpionEvalFct[0]))
            return RandomEvalFunction.getDescription();
        else if(name.equals(buildableMorpionEvalFct[1]))
            return MorpionEvalFunctionCST.getDescription();

        return RandomEvalFunction.getDescription();
    }
}
