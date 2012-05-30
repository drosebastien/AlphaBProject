package gui;

import explorer.MinMaxAlgo;

/**
 * Cette interface permet d'optenir les actions realise par l'outil.
 * @author Sebastien Drobisz.
 */
public interface MinMaxEducativeToolsListener {

    /**
     * est appelee si l'algorithme doit avancer d'une action.
     * @param inExplorerMode Le mode dans lequel se trouve l'outil.
     */
    public void progress(boolean inExplorerMode);

    /**
     * Permet de revenir un coup en arriere.
     * @param inExplorerMode Le mode dans lequel se trouve l'outil.
     */
    public void removeLast(boolean inExplorerMode);

    /**
     * est appelee pour un changement de profondeur d'exploration
     * @param treeDepth La profondeur d'exploration
     */
    public void treeDepthChanged(int treeDepth);

    /**
     * est appelee pour un changement d'algorithme.
     * @param algoName Le nom de l'algo a utiliser.
     */
    public void algoHaveChanged(String algoName);

    /**
     * est appellee pour un changement de fonction d'evaluation
     * @param fctName Le nom de la fonction d'evaluation a changer.
     */
    public void fctHaveChanged(String fctName);

    /**
     * Est appelee pour un changement lors de la fenetre de recherche
     * @param minValue La valeur borne inferieur de la fenetre
     * @param maxValue La valeur borne superieur de la fenetre
     */
    public void windowValuesHaveChanged(int minValue, int maxValue);

    /**
     * Permet d'activer le mode execution automatique de l'algorithme
     * @param inExplorerMode Le mode dans lequel se trouve l'outil.
     */
    public void play(boolean inExplorerMode);

    /**
     * Permet de mettre sur pause l'avancement de l'algorithme.
     * @param inExplorerMode Le mode dans lequel se trouve l'outil.
     */
    public void pause(boolean inExplorerMode);

    /**
     * Permet de changer la vitesse de l'avancement automatique de l'algorithme
     * @param value L'interval de temps entre deux coups.
     */
    public void setSpeed(int value);

    /**
     * Permet de stopper l'avancement automatique que l'algorithme et de
     * remettre l'execution au début.
     * @param inExplorerMode Le mode dans lequel se trouve l'outil.
     */
    public void stop(boolean inExplorerMode);
}
