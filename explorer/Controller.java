package explorer;

import gui.*;
import framework.*;

import java.util.ArrayList;

/**
 * Cette classe permet de controler les actions effectuees sur les vues.
 * @author Sebastien Drobisz
 */
public class Controller implements GamePanelListener, TreePanelListener,
                                   MinMaxEducativeToolsListener {
    private String name;
    private Explorer explorer;
    private Executor executor;
    private boolean firstChangeAlgo;

    /**
     * Ce constructeur permet de créer un controller et de lui donner un nom.
     * @param name le nom du controller.
     */
    public Controller(String name) {
        this.name = name;
        firstChangeAlgo = true;
    }

    /**
     * Cette méthode permet de signaler qu'un changement d'algorithme
     * @param algoName Le nom du nouvel algorithme a utiliser.
     */
    public void algoHaveChanged(String algoName) {
        executor.changeAlgo(algoName);
        executor.pause();
        explorer.restart();
    }

    /**
     * Cette methode permet de signaler un changement de fonction d'evaluation
     * @param fctName Le nom de la nouvelle fonction d'évaluation a utiliser
     */
    public void fctHaveChanged(String fctName) {
        executor.setEvalFunction(fctName);
        executor.pause();
        if(!firstChangeAlgo) {
            explorer.restart();
        }
        firstChangeAlgo = false;
    }

    /**
     * Cette methode permet de signaler qu'un changement de la fenetre de
     * recherche a été effectue.
     * @param minValue La valeur minimale de la fenetre de recherche
     * @param maxValue La valeur maximale de la fenetre de recherche
     */
    public void windowValuesHaveChanged(int minValue, int maxValue) {
        executor.windowValuesHaveChanged(minValue, maxValue);
        explorer.restart();
    }

    /**
     * Cette methode permet de signaler qu'un click a été fait sur un noeud.
     * L'action dépendant du mode exploration/execution, le mode est passé en
     * parametre.
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     * @param pathToNode Le chemin menant de la racine au noeud sur lequel le
     * click a été fait.
     */
    public void clickOnNode(boolean inExplorerMode, int[] pathToNode) {

        if(inExplorerMode) {
            explorer.moveForward(pathToNode);
        }
        else {
            executor.progress(pathToNode);
        }
    }

    /**
     * Cette methode permet de signaler qu'un noeud prefere a ete choisi.
     * Le noeud prefere est modifie s'il est dans le mode execution
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     * @param pathToNode Le chemin menant de la racine au noeud sur lequel le
     * click a ete fait.
     */
    public void bestNodeSelected(boolean inExplorerMode, int[] pathToNode) {
        //quitPreview();
        pause(inExplorerMode);
        if(!inExplorerMode) {
            explorer.selectFirstNode(pathToNode);
        }
    }

    /**
     * Permet de signaler qu'un coup a ete realise sur le panel de la grille de
     * jeu.
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     * @param move le coup joue
     */
    public void hitFired(Move move, boolean inExplorerMode) {
        if(inExplorerMode) {
            explorer.moveForward(move);
        }
    }

    /**
     * Permet de signaler qu'il faut avancer d'un evenement dans l'algorithme
     * (dans le mode execution)
     * L'action a réaliser dans le mode explorer n'est pas defini.
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     */
    public void progress(boolean inExplorerMode) {
        if(inExplorerMode) {
        }
        else {
            executor.progress();
        }
    }

    /**
     * Permet de signaler qu'il faut revenir dans un etat precedent a celui
     * selectionne (si dans le mode explorer)
     * L'action a réaliser dans le mode execution n'est pas defini.
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     */
    public void removeLast(boolean inExplorerMode) {
        if(inExplorerMode) {
            explorer.removeLast();
        }
    }

    /**
     * Permet de signaler qu'un apercu a ete demande
     * @param moves le chemin qui a partir de la racine qui determine les
     * coup a jouer.
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     */
    public void preview(int[] moves, boolean inExplorerMode) {
        pause(inExplorerMode);
        explorer.preview(moves);
    }

    /**
     * Permet de signaler qu'il faut revenir a l'etat precedent la preview
     */
    public void quitPreview() {
        explorer.quitPreview();
    }

    /**
     * Permet de signaler qu'un changemetn de profondeur de l'arbre de jeu
     * a ete selectionne
     * @param treeDepth La profondeur voulue.
     */
    public void treeDepthChanged(int treeDepth) {
        explorer.setTreeDepth(treeDepth);
    }

    /**
     * Permet d'ajouter un Explorer au controller.
     * @param explorer L'explorer qu'il faut ajouter.
     */
    public void addExplorer(Explorer explorer) {
        this.explorer = explorer;
    }

    /**
     * Permet d'ajouter un executor au controller
     * @param executor L'executor qu'il faut ajouter.
     */
    public void addExecutor(Executor executor) {
        this.executor = executor;
    }
    /**
     * Permet de signaler que le mode play a ete active.
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     */
    public void play(boolean inExplorerMode) {
        if(!inExplorerMode) {
            executor.play();
        }
    }

    /**
     * Permet de specifier que le mode play est mis sur pause
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     */
    public void pause(boolean inExplorerMode) {
        if(!inExplorerMode) {
            executor.pause();
        }
    }

    /**
     * Permet de specifier une nouvelle vitesse d'avancement pour le mode play
     * @param value La valeur du temps a attendre entre deux coups joue
     */
    public void setSpeed(int value) {
        executor.setSpeed(value);
    }

    /**
     * Permet de stopper le mode play et reinitialiser l'execution de l'algo.
     * @param inExplorerMode True si dans le mode explorer, false dans le mode
     * execution de l'algorithme
     */
    public void stop(boolean inExplorerMode) {
        if(!inExplorerMode) {
            executor.pause();
            explorer.restart();
        }
    }
}
