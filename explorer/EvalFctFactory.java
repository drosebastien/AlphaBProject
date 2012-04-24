package explorer;

import morpion.*;

public class EvalFctFactory {
    private static EvalFctFactory instance;
    private static String[] buildableEvalFctFactory;

    private EvalFctFactory() {
    }

    public static EvalFctFactory getInstance() {
        if(instance == null) {
            instance = new EvalFctFactory();
            initBuildabelEvalFctFactory();
        }

        return instance;
    }

    private static void initBuildabelEvalFctFactory() {
        buildableEvalFctFactory = new String[] {"Morpion"};
    }

    public String[] getBuildableEvalFctFactory() {
        return buildableEvalFctFactory;
    }

    public AbstractGameEvalFctFactory getGameEvalFctFactory(String name) {
        if(name.equals(buildableEvalFctFactory[0]))
            return MorpionEvalFctFactory.getInstance();
        return MorpionEvalFctFactory.getInstance();
    }
}
