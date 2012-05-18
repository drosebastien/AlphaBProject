package explorer;

import framework.*;

import java.util.Random;

public class RandomEvalFunction implements EvalFunction {

    public static final int MAXVALUE = 50;
    public static final int MINVALUE = -50;
    private Random gen;
    private Game game;
    private Player player;

    public RandomEvalFunction() {
        gen = new Random();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    protected Game getGame() {
        return game;
    }

    protected Player getPlayer() {
        return player;
    }

    public int evalFunction() {

        if(game.isFinish() && game.isVictory() &&
           game.getWinner().equals(player)) {
            return MAXVALUE;
        }
        else if(game.isFinish() && game.isVictory()) {
            return MINVALUE;
        }

        return gen.nextInt(99) - 49;
    }

    public static String getDescription() {
        return "* -50 for oppenent victory\n" +
               "* 50 for root player victory\n" +
               "* otherwise random value in ]-50, 50[";
    }

    public static String getName() {
        return "Random";
    }

    public void started() {}

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event) {}

    public void setValueOfNode(String value, MinMaxEvent event) {}

    public void refreshTree(MinMaxEvent event) {}

    public void setNewBestNode(int indexOfChild, MinMaxEvent event) {}

    public void setDropedNode(int indexOfChild, MinMaxEvent event) {}

    public void finished(MinMaxEvent evt) {}
}
