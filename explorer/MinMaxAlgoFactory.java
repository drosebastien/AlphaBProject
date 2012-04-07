package explorer;

import framework.*;

public class MinMaxAlgoFactory {
    private static MinMaxAlgoFactory instance;
    private static String[] buidableMinMaxAlgo;

    private MinMaxAlgoFactory() {
    }

    public static MinMaxAlgoFactory getInstance() {
        if(instance == null) {
            instance = new MinMaxAlgoFactory();
            initBuildabelMinMaxAlgo();
        }

        return instance;
    }

    private static void initBuildabelMinMaxAlgo() {
        buidableMinMaxAlgo = new String[] {NormalMinMax.getAlgoName(),
                                           IterativeMinMax.getAlgoName(),
                                           AlphaBeta.getAlgoName(),
                                           IterativeAlphaBeta.getAlgoName()};
    }

    public String[] getBuildableMinMaxAlgoName() {
        return buidableMinMaxAlgo;
    }

    public MinMaxAlgo getMinMaxAlgo(String name, Game game, int maxDepth,
                                    EvalFunction fct) {
        if(name.equals(buidableMinMaxAlgo[0]))
            return new NormalMinMax(name, game, maxDepth, fct);
        else if(name.equals(buidableMinMaxAlgo[1]))
            return new IterativeMinMax(name, game, maxDepth,fct);
        else if(name.equals(buidableMinMaxAlgo[2]))
            return new AlphaBeta(name, game, maxDepth, fct);
        else if(name.equals(buidableMinMaxAlgo[3]))
            return new IterativeAlphaBeta(name, game, maxDepth, fct);
        return new NormalMinMax(name, game, maxDepth, fct);
    }
}
