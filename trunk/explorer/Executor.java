package explorer;

import gui.*;
import tree.*;
import framework.*;

import java.util.ArrayList;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Cette methode permet de creer un executor qui s'occupe du suivi des
 * algorithmes et modifie l'arbre d'exploration.
 * @author Sebastien Drobisz
 */
public class Executor implements MinMaxListener {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private TreeNode currentNode;
    private MinMaxAlgo minMaxAlgo;
    private EvalFunction evalFunction;
    private Thread thread;
    private Timer timer;
    private AbstractGameEvalFctFactory gameEvalFctFactory;
    private ArrayList<MinMaxListener> minMaxListeners;
    private boolean isFirstActionPerformedOfTimer;

    /**
     * Ce constructeur permet de creer un Executor avec different composant
     * qu'il est charge d'avertir des modifications qu'il effectue et d'un
     * algorithme MiniMax qu'il est chargé de suivre.
     * @param game Permet de le donner a l'algorithme si celui-ci est change.
     * @param gamePanel Le panel du jeu choisi. Cela permet a l'executor de
     * le refraichir lorsque l'algorithme a realise un mouvement.
     * @param treePanel Permet de rafraichir le panel de l'arbre d'exploration
     * pour permettre le suivit de l'algorithme.
     * @param minMaxAlgo L'algorithme MiniMax choisi lors du demarrage de
     * l'outil
     */
    public Executor(Game game, GamePanel gamePanel,
                    TreePanel treePanel, MinMaxAlgo minMaxAlgo) {
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
        this.minMaxAlgo = minMaxAlgo;
        timer = new Timer(100, new TimerListener());
        minMaxListeners = new ArrayList<MinMaxListener>();
        isFirstActionPerformedOfTimer = true;
        setListeners();
    }

    private void setListeners() {
        minMaxAlgo.removeListeners();
        minMaxAlgo.addListener(this);
        for(int i = 0; i < minMaxListeners.size(); i++) {
            minMaxAlgo.addListener(minMaxListeners.get(i));
        }
    }

    /**
     * Permet d'ajouter un listener pour l'algorithme MiniMax.
     * Permet de fournir les listeners d'un ancien algorithme MiniMax lorsqu'il
     * est modifié lors de l'utilisation de l'outil.
     * @param listener Le nouveau listener a ajouter a l'algorithme.
     */
    public void addMinMaxListener(MinMaxListener listener) {
        minMaxListeners.add(listener);
        setListeners();
    }

    /**
     * Cette methode permet de changer l'algorithme MiniMax a suivre
     * @param algoName Le nouvel algorithme qu'il faut suivre.
     */
    public void changeAlgo(String algoName) {
        int minValue = this.minMaxAlgo.getMinValue();
        int maxValue = this.minMaxAlgo.getMaxValue();

        this.minMaxAlgo = MinMaxAlgoFactory.getInstance().getMinMaxAlgo(
                              algoName, minMaxAlgo.getGame(),
                              minMaxAlgo.maxDepth(),
                              minMaxAlgo.getEvalFunction());
        setListeners();
        this.minMaxAlgo.setMinValue(minValue); // ajout de la valeur min courant
        this.minMaxAlgo.setMaxValue(maxValue); // idem pour la valeur max
    }

    /**
     * Permet de demarrer le suivi de l'algorithme.
     */
    public void start() {
        this.thread = new Thread(new launcher());
        thread.start();
    }

    /**
     * Permet de redemarrer le suivi de l'algorithme.
     */
    public void restart() {
        this.thread.stop();                                                     // à changer stop est déprécié.
        this.thread = new Thread(new launcher());
        thread.start();
    }

    /**
     * Permet de changer la profondeur d'exploration de l'algorithme.
     * @param maxDepth La nouvelle profondeur d'exploration.
     */
    public void setMaxDepth(int maxDepth) {
        minMaxAlgo.setMaxDepth(maxDepth);
    }

    /**
     * Permet de selectionner l'arbre d'exploration qu'il faut modifier au
     * cours du suivi de l'algorithme.
     * @param root La racine de l'arbre d'exploration.
     */
    public void setTree(TreeNode root) {
        this.currentNode = root;
    }

    /**
     * Permet de faire avancer l'algorithme d'une action.
     */
    public void progress() {
        minMaxAlgo.unlock();
    }

    /**
     * Permet de modifier les valeurs de la fenetre de recherche de l'algorithme
     * @param minValue La borne inferieure de la fenetre de recherche.
     * @param maxValue La borne superieure de la fenetre de recherche.
     */
    public void windowValuesHaveChanged(int minValue, int maxValue) {
        minMaxAlgo.setMinValue(minValue);
        minMaxAlgo.setMaxValue(maxValue);
    }

    /**
     * Permet d'avancer l'algorithme jusqu'a un noeud voulu.
     * @param moves Le chemin menant au noeud voulu.
     */
    public void progress(int[] moves) {
        //je ne fait qu'imprimer dans le terminal le chemin qu'il faut suivre.
        String line = "| ";
        for(int i = 0; i < moves.length; i++) {
            line += moves[i] + " | ";
        }

        System.out.println("je dois avancer mon algo en : " + line);
    }

    /**
     * Cette methode permet de donner un label a un noeud.
     * @param value La valeur du label
     * @param evt L'evenement associe.
     */
    public void setValueOfNode(String value, MinMaxEvent evt) {
        currentNode.setLabel(value);
        repaintPanels();
    }

    /**
     * Permet de donner l'abstract factory des fonctions d'evaluation associe
     * au jeu selectionne.
     * @param factory L'abstract factory.
     */
    public void setEvalFctFactory(AbstractGameEvalFctFactory factory) {
        this.gameEvalFctFactory = factory;
    }

    /**
     * Permet de changer la fonction d'evaluation utilise par l'algorithme
     * @param fctName Le nom de la nouvelle fonction d'evaluation a utiliser.
     */
    public void setEvalFunction(String fctName) {
        evalFunction = gameEvalFctFactory.getEvalFct(fctName);
        minMaxAlgo.setEvalFunction(evalFunction);
    }

    /**
     * Permet de changer le meilleur noeud pour l'exploration courante.
     * @param indexOfChild l'indice par rapport a l'evaluation courante du
     * nouveau meilleur noeud.
     * @param evt L'evenement associe.
     */
    public void setNewBestNode(int indexOfChild, MinMaxEvent evt) {
        removeStateOfPastImportantNode(currentNode);

        TreeNode newImportantNode = currentNode.getChild(indexOfChild);
        newImportantNode.setType(NodeType.IMPORTANT);
        //setImportantAncestor(currentNode);
        repaintPanels();
    }

    /*
     * donne le statut "vue" au précédent noeud important et fait de même
     * pour tous les noeuds ANCESTOR_OF_IMPORTANT
     */
    private void removeStateOfPastImportantNode(TreeNode node) {
        for(int i = 0; i < node.getNbChild(); i++) {
            TreeNode child = node.getChild(i);
            if(child.getType() == NodeType.IMPORTANT) {
                child.setType(NodeType.VIEWED);
                removeStateOfPastImportantNode(child);
            }
        }
    }

    private void setImportantAncestor(TreeNode node) {
        if(node != null) {
            node.setType(NodeType.ANCESTOR_OF_IMPORTANT);
            setImportantAncestor(node.getParent());
        }
    }

    /**
     * Permet de dire qu'un meilleur noeud ne l'est plus.
     * @param indexOfChild l'indice de l'ancien meilleur noeud.
     * @param evt l'evenement qui est associe.
     */
    public void setDropedNode(int indexOfChild, MinMaxEvent evt) {
        removeStateOfPastImportantNode(currentNode.getChild(indexOfChild));
    }

    /**
     * Permet de dire que l'algorithme a demarre son execution. Elle n'est pas
     * utile dans cette classe etant donne que c'est elle meme qui demarre
     * l'algorithme.
     */
    public void started() {
    }

    /**
     * Cette methode permet lorsqu'elle est appelle de modifier l'arbre
     * d'exploration en avancant dans celui-ci grace aux differentes
     * informations qui sont fournies.
     * @param move La direction du mouvement realise par l'algorithme.
     * @param indexInTreeGame L'indice de mouvement realise dans l'arbre.
     * @param evt L'evenement qui est associe.
     */
    public void moved(Movement move, int indexInTreeGame, MinMaxEvent evt) {
        switch(move) {
            case FORWARD :
                currentNode.setType(NodeType.ANCESTOR_OF_CURRENT);
                currentNode = currentNode.getChild(indexInTreeGame);
                break;
            case BACKWARD :
                currentNode.setType(NodeType.VIEWED);
                currentNode = currentNode.getParent();
                break;
            default :
                break;
        }

        currentNode.setType(NodeType.CURRENT);

        repaintPanels();
    }

    /**
     * Cette methode permet de reinitialiser l'arbre d'exploration tel que
     * l'explorer lui avait donné.
     * @param evt L'evenement qui est associe a cette action.
     */
    public void refreshTree(MinMaxEvent evt) {
        Tree.removeStatesOfTree(currentNode);
    }

    /**
     * Cette methode permet de prevenir que l'algorithme a termine son
     * evaluation.
     * @param evt l'evaluation associe a l'evenement.
     */
    public void finished(MinMaxEvent evt) {
    }

    private void repaintPanels() {
        gamePanel.repaint();
        treePanel.repaint();
    }

    /**
     * Cette methode permet d'activer le mode d'exploration automatique.
     */
    public void play() {
        isFirstActionPerformedOfTimer = false;
        timer.restart();
    }

    /**
     * Cette methode permet de mettre pause le mode d'exploration automatique.
     */
    public void pause() {
        timer.stop();
    }

    /**
     * Cette methode permet de modifier l'intervale de temps entre deux
     * coup joues automatiquement. (Modifier la vitesse d'exploration).
     * @param value L'interval de temps.
     */
    public void setSpeed(int value) {
        isFirstActionPerformedOfTimer = true;
        if(timer.isRunning()) {
            timer.restart();
        }
        timer.setDelay(value);
    }

    /*
     * A thread have to be used otherwise everything is blocked by the
     * lock methode from a MinMaxAlgo.
     */
    private class launcher implements Runnable {
        public void run() {
            minMaxAlgo.launchMinMax();
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if(!isFirstActionPerformedOfTimer) {
                progress();
            }
            isFirstActionPerformedOfTimer = false;
        }
    }
}
