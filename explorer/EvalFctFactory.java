package explorer;

import morpion.MorpionEvalFctFactory;
import connectFour.CFEvalFctFactory;

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
        buildableEvalFctFactory = new String[] {"Morpion",
                                                "ConnectFour"};
    }

    public String[] getBuildableEvalFctFactory() {
        return buildableEvalFctFactory;
    }

    public AbstractGameEvalFctFactory getGameEvalFctFactory(String name) {
        if(name.equals(buildableEvalFctFactory[0]))
            return MorpionEvalFctFactory.getInstance();
        else if(name.equals(buildableEvalFctFactory[1]))
            return CFEvalFctFactory.getInstance();
        return MorpionEvalFctFactory.getInstance();
    }
}
