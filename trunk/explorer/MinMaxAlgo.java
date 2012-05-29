package explorer;

import tree.*;
import framework.*;

import java.util.ArrayList;

import java.util.concurrent.Semaphore;

/**
 * Cette classe permet d'implementer n'importe qu'elle algorithme de type
 * MiniMax.
 * @author Sebastien Drobisz
 */
public abstract class MinMaxAlgo {
    private int maxValue = 999;
    private int minValue = -999;

    private ArrayList<MinMaxListener> listeners;
    private Semaphore semaphore;
    private String name;
    private int maxDepth;
    protected Game game;
    protected EvalFunction evalFct;

    /**
     * Ce constructeur permet de creer un nouvel algorithme MiniMax en lui
     * donnant les elements essentiels a son utilisation.
     * @param name Le nom de l'algorithme.
     * @param game Le jeu a explorer.
     * @param maxDepth La profondeur maximale d'exploration.
     * @param evalFct La fonction a utiliser lorsqu'un etat doit etre evalue.
     */
    public MinMaxAlgo(String name, Game game,
                      int maxDepth, EvalFunction evalFct) {

        this.name = name;
        this.game = game;
        this.maxDepth = maxDepth - 1;
        this.evalFct = evalFct;

        listeners = new ArrayList<MinMaxListener>();
        evalFct.setGame(game);
        evalFct.setPlayer(game.nextPlayer());
        listeners.add(evalFct);
    }

    /**
     * Cette methode retourne le nom de l'algorithme.
     * @return Le nom de l'algorithme.
     */
    public String getName() {
        return name;
    }

    /**
     * Cette methode retourne le jeu qui lui a ete passe lors de sa construction
     * @return Le jeu qui lui a ete donne lors de sa creation.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Cette methode permet de changer la fonction d'evaluation utiliser pour
     * evaluer les noeuds terminaux.
     * @param evalFct La nouvelle fonction d'evaluation a utiliser.
     */
    public void setEvalFunction(EvalFunction evalFct) {
        this.evalFct = evalFct;
        this.evalFct.setGame(game);
        this.evalFct.setPlayer(game.nextPlayer());
        listeners.set(0, evalFct);
    }

    /**
     * Permet d'avoir la fonction d'evaluation utilisee par l'algorithme.
     * @return La fonction d'evaluation utilisee par l'algorithme.
     */
    public EvalFunction getEvalFunction() {
        return evalFct;
    }

    /**
     * Cette methode permet de retirer les listeners de la liste de listeners.
     */
    public void removeListeners() {
        listeners = new ArrayList<MinMaxListener>();
        listeners.add(evalFct);
    }

    /**
     * Cette methode permet d'ajouter un listener a l'algorithme MiniMax.
     */
    public void addListener(MinMaxListener listener) {
        listeners.add(listener);
    }

    /**
     * Cette methode permet de debloquer l'algorithme lorsqu'il est bloque
     * apres avoir fait appel a la fonction lock.
     * @see #lock()
     */
    public void unlock() {
        if(semaphore != null && semaphore.availablePermits() == 0) {
            semaphore.release();
        }
    }

    /**
     * Cette methode permet de retourner la borne inferieure de la
     * fenetre de recherche.
     * @return La borne inferieure de la fenetre de recherche.
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Cette methode permet de retourner la bornde superieure de la fenetre
     * de recherche.
     * @return La borne superieure de la fenetre de recherche.
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Cette methode permet de modifier la valeur de la borne inferieure.
     * @param minValue La nouvelle borne inferieure.
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * Cette methode permet de modifier la valeur de la borne superieure.
     * @param maxValue La nouvelle borne superieure.
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Cette methode retourner la profondeur d'exploration de l'algorithme.
     * @return La profondeur d'exploration de l'algorithme.
     */
    public int maxDepth() {
        return maxDepth;
    }

    /**
     * Cette methode permet de modifier la profondeur d'exploration de
     * l'algorithme.
     * @param maxDepth La nouvelle profondeur d'exploration.
     */
    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth - 1;
    }

    /**
     * Cette methode permet de lancer l'algorithme MiniMax.
     * @return Le mouvement selectionne par l'exploration de l'algorithme.
     */
    public abstract Move launchMinMax();

    /**
     * Cette methode permet de realiser un mouvement.
     * @param move Le mouvement a realiser.
     * @param indexOfMove L'indice du mouvement dans l'arbre d'exploration.
     */
    public abstract void playMove(Move move, int indexOfMove);

    /**
     * Cette methode permet d'annuler un mouvement precedemment joue.
     * @param move Le mouvement a annuler.
     * @param indexOfMove L'indice du mouvement dans l'arbre d'exploration.
     * @param label Derniere valeur attribue au noeud correspondant a ce noeud
     * annule.
     */
    public abstract void removeMove(Move move, int indexOfMove, String label);

    /**
     * Cette methode permet de retourner la valeur de l'evaluation d'un etat
     * pour un joueur.
     * @param nodePlayer Le joueur "Max"
     * @return La valeur d'evaluation de l'etat courant.
     */
    public abstract int evalFunction(Player nodePlayer);

    /**
     * Cette methode permet de bloquer l'avancement de l'algorithme.
     * @see #unlock()
     */
    protected void lock() {
        semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        }
        catch(InterruptedException e) {
            System.out.println("Trouble for request of semaphore acquirement");
            e.printStackTrace();
        }
    }

    /**
     * Cette methode permet de donner la valeur minimax de l'etat courant
     * lorsque l'exploration est terminee a tous les listeners.
     * @param value La valeur minimax.
     * @param evt L'evenement associe.
     */
    protected void giveValueToListeners(String value, MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setValueOfNode(value, evt);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners qu'un nouveau meilleur
     * noeud est selectionne.
     * @param index L'indice du meilleur coup par rapport au noeud courant
     * a l'exploration.
     * @param evt L'evenement associe a l'action.
     */
    protected void warnListenersOfNewBestNode(int index, MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setNewBestNode(index, evt);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners que le meilleur
     * noeud par rapport au noeud courant a l'exploration 
     * n'en est plus un.
     * @param index L'index de l'ancien meilleur noeud.
     * @param evt L'evenement associe à cette action.
     */
    protected void warnListenersOfDropNode(int index, MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).setDropedNode(index, evt);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners que l'a recherche va
     * reprendre a partir du debut. (Cela se fait par exemple pour l'iterative
     * deepening depth-first search.)
     * @param evt L'evenement associé à l'action.
     */
    protected void refreshTreeOfListener(MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).refreshTree(evt);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners que l'algorithme commence
     * son exploration.
     */
    protected void started() {
        for(MinMaxListener listener : listeners) {
            listener.started();
        }
    }

    /**
     * Cette methode permet de prevenir les listeners qu'une action impliquant
     * un voyage dans l'arbre a ete realise.
     * @param move La direction du mouvement realise.
     * @param indexOfMove L'indice du mouvement dans l'arbre d'exploration.
     * @param evt L'evenement associe a l'action.
     */
    protected void warnListeners(Movement move, int indexOfMove,
                                                        MinMaxEvent evt) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).moved(move, indexOfMove, evt);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners que l'algorithme a
     * fini l'exploration pour l'etat initial.
     * @param evt L'action associee
     */
    protected void finished(MinMaxEvent evt) {
        for(MinMaxListener listener : listeners) {
            listener.finished(evt);
        }
    }

    /**
     * Cette methode permet de retourner un string qui caracterise
     * l'algorithme.
     */
    public String toString() {
        return name;
    }
}
