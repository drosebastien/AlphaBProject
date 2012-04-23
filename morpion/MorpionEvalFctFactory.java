package morpion;

import framework.*;
import morpion.evaluationFunction.*;

public class MorpionEvalFctFactory {
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
        buildableMorpionEvalFct = new String[] {MorpionEvalFunction.getName()};
    }

    public String[] getBuildableMorpionEvalFct() {
        return buildableMorpionEvalFct;
    }

    public MorpionEvalFunction getMorpionEvalFct(String name) {
        if(name.equals(buildableMorpionEvalFct[0]))
            return new MorpionEvalFunction();
        return new MorpionEvalFunction();
    }
}
