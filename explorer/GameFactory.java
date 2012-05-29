package explorer;

import morpion.MorpionEvalFctFactory;
import morpion.Morpion;
import connectFour.CFEvalFctFactory;
import connectFour.ConnectFour;
import framework.Game;

/**
 * Cette classe permet d'instancier tous les jeux uniquement lorsque ceux-
 * ci sont demandés. Il permet egalement de fournir le factory des fonctions
 * d'evaluation associe a un jeu donne.
 * @author Sebastien Drobisz.
 */
public class GameFactory {
    private static GameFactory instance;
    private static String[] buildableGame;

    private GameFactory() {
    }

    /**
     * Cette methode permet d'obtenir l'instance unique du gameFactory
     * @return GameFactory L'instance du gameFactory.
     */
    public static GameFactory getInstance() {
        if(instance == null) {
            instance = new GameFactory();
            initBuildableGame();
        }

        return instance;
    }

    private static void initBuildableGame() {
        buildableGame = new String[] {Morpion.getName(),
                                      ConnectFour.getName()};
    }

    /**
     * Cette methode permet d'avoir le nom de tous les noms instanciables
     * @return L'ensemble des noms des jeux instanciables.
     */
    public String[] getBuildableGameName() {
        return buildableGame;
    }

    /**
     * Cette methode retourne une instance du jeu demande.
     * @param name Le nom du jeu dont l'instance doit etre retournee
     * @return Le jeu instancie.
     */
    public Game getGame(String name) {
        if(name.equals(buildableGame[0]))
            return new Morpion();
        else if(name.equals(buildableGame[1]))
            return new ConnectFour();

        return new Morpion();
    }

    /**
     * Cette methode permet de retourner l'abstract factory des fonctions
     * d'evaluation associe au jeu donne.
     * @param name Le nom du jeu.
     * @return L'abstractFactory des fonctions d'evaluation associe au jeu.
     */
    public AbstractGameEvalFctFactory getGameEvalFctFactory(String name) {
        if(name.equals(buildableGame[0]))
            return MorpionEvalFctFactory.getInstance();
        else if(name.equals(buildableGame[1]))
            return CFEvalFctFactory.getInstance();

        return MorpionEvalFctFactory.getInstance();
    }
}
