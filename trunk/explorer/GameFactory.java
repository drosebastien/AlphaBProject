package explorer;

import morpion.MorpionEvalFctFactory;
import morpion.Morpion;
import connectFour.CFEvalFctFactory;
import connectFour.ConnectFour;
import framework.Game;

public class GameFactory {
    private static GameFactory instance;
    private static String[] buildableGame;

    private GameFactory() {
    }

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

    public String[] getBuildableGameName() {
        return buildableGame;
    }

    public Game getGame(String name) {
        if(name.equals(buildableGame[0]))
            return new Morpion();
        else if(name.equals(buildableGame[1]))
            return new ConnectFour();

        return new Morpion();
    }

    public AbstractGameEvalFctFactory getGameEvalFctFactory(String name) {
        if(name.equals(buildableGame[0]))
            return MorpionEvalFctFactory.getInstance();
        else if(name.equals(buildableGame[1]))
            return CFEvalFctFactory.getInstance();

        return MorpionEvalFctFactory.getInstance();
    }
}
