package explorer;

import framework.*;

import java.util.Random;

/**
 * Cette classe represente une fonction d'evaluation qui attribue des valeurs
 * aleatoire si les etats sont differents d'une situation de perte ou de
 * victoire. Sinon, une victoire retourne une valeur max tandis qu'une defaite
 * retourne une valeur minimale.
 * @author Sebastien Drobisz.
 */
public class RandomEvalFunction implements EvalFunction {

    /**
     * Valeur attribue pour une victoire. Elle represente la plus grande valeur
     * que la fonction puisse retourner.
     */
    public static final int MAXVALUE = 50;
    /**
     * Valeur attribue pour une defaite. Elle represente la plus grande petite
     * que la fonction puisse retourner.
     */
    public static final int MINVALUE = -50;
    private Random gen;
    private Game game;
    private Player player;

    /**
     * Ce constructeur permet d'initialiser la fonction d'evaluation en
     * creant un generateur de nombre aleatoire.
     */
    public RandomEvalFunction() {
        gen = new Random();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Cette methode permet de retourner le game qui lui a ete passe
     * precedemment.
     * @return Le jeu.
     * @see #setGame(Game game)
     */
    protected Game getGame() {
        return game;
    }

    /**
     * Cette methode permet de retourner le joueur qui lui a aete passe
     * precedemment. (Joueur Max).
     * @return Le joueur.
     * @see #setPlayer(Player player)
     */
    protected Player getPlayer() {
        return player;
    }

    /**
     * Cette fonction attribue des valeurs
     * aleatoire si les etats sont differents d'une situation de defaite ou de
     * victoire. Sinon, une victoire retourne une valeur max tandis qu'une defaite
     * retourne une valeur minimale.
     */
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

    /**
     * Cette methode permet de retourner une description de ce que fait
     * la fonction d'evaluation.
     * @return La descritpion de ce que fait la fonction d'evaluation.
     */
    public static String getDescription() {
        return "* -50 for oppenent victory\n" +
               "* 50 for root player victory\n" +
               "* otherwise random value in ]-50, 50[";
    }

    /**
     * Cette methode permet de retourner un nom qui est attribue a la classe.
     * @return Le nom attribue a la fonction d'evaluation.
     */
    public static String getName() {
        return "Random";
    }

    /*
     * Methodes de l'interface.
     */
    public void started() {}

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event) {}

    public void setValueOfNode(String value, MinMaxEvent event) {}

    public void refreshTree(MinMaxEvent event) {}

    public void setNewBestNode(int indexOfChild, MinMaxEvent event) {}

    public void setDropedNode(int indexOfChild, MinMaxEvent event) {}

    public void finished(MinMaxEvent evt) {}
}
