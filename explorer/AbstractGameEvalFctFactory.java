package explorer;

/**
 *Cette classe permet de creer des factory pour chacun des jeux implementes
 *@author Sebastien Drobisz
 */
public abstract class AbstractGameEvalFctFactory {

    /**
     * cette methode permet de retourner le nom de toutes les fonctions
     * d'evaluation qu'il est possible d'instancier.
     * @return Les noms des fonctions d'evaluation pouvant etre instanciees
     */
    public abstract String[] getBuildableEvalFct();

    /**
     * Cette methode permet d'instancier et de retourner la fonction
     * d'evaluation correspondant au nom entre.
     * @return La fonction d'evaluation voulue.
     */
    public abstract EvalFunction getEvalFct(String name);

    /**
     * Cette methode permet de retourner un descriptif de la fonction
     * d'evaluation dont le nom est passe en parametre et ce, sans instancier
     * la fonction d'evaluation.
     * @param name Le nom de la fonction d'evaluation dont le descriptif est
     * voulu.
     * @return La description de la fonction d'evaluation.
     */
    public abstract String getDescription(String name);
}
