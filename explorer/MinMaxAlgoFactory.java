package explorer;

import framework.*;

/**
 * Cette classe permet de retourner l'instance de l'algorithme selectionne pour
 * etre utilise.
 * @author Sebastien Drobisz.
 */
public class MinMaxAlgoFactory {
    private static MinMaxAlgoFactory instance;
    private static String[] buidableMinMaxAlgo;

    /**
     * Ce constructeur permet d'empecher la creation d'autre instance de cette
     * classe.
     */
    private MinMaxAlgoFactory() {
    }

    /**
     * Cette methode permet de retourner l'instance de cette classe.
     * @return MinMaxAlgoFactory Le factory des algorithmes MiniMax.
     */
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

    /**
     * Cette methode permet de retourner le nom de tous les algorithmes
     * qu'il est possible d'instancier.
     * @return Le nom de tous les algorithmes instanciable.
     */
    public String[] getBuildableMinMaxAlgoName() {
        return buidableMinMaxAlgo;
    }

    /**
     * Cette methode permet de retourner l'algorithme dont le nom est passe
     * en parametre. Les autres parametres servent a l'instanciation
     * de l'algorithme.
     * @param name Le nom de l'algorithme qu'il faut retourner.
     * @param game Le jeu qui sera explore par l'algorithme.
     * @param maxDepth La profondeur de recherche couramment selectionne.
     * @param fct La fonction d'evaluation couramment utilisee.
     * @return L'algorithme dont le nom est passe en parametre.
     */
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
