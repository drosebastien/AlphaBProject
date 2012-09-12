package explorer;

import gui.*;
import framework.*;
import tree.*;

import java.util.ArrayList;

/**
 * Cette classe permet de creer l'arbre d'exploration suivant l'etat de jeu
 * selectionne.
 * @author Sebastien Drobisz
 */
public class Explorer {
    private Game game;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private TreeNode root;
    private Executor executor;
    private ArrayList<Move> lastMoves;
    private int treeDepth;
    private boolean haveToRemovePreferedMove = true;
    private GameMemento explorationMemento;
    private GameMemento previewMemento;
    private boolean previewMade;
    private NodeType[] previewPathType;
    private int[] previewPath;

    /**
     * Ce constructeur permet d'initialiser l'explorer avec les composants
     * qui doivent etre modifie à chaque changement d'etat.
     * @param game Le jeu à partir du quel l'arbre d'exploration doit etre cree.
     * @param gamePanel Le panel de jeu qui doit etre rafraichi lors d'un
     * changement d'etat.
     * @param treePanel Le panel qui permet la visualisation de l'arbre.
     * @param treeDepth La profondeur choisie par defaut lorsqu'un explorer est
     * cree.
     */
    public Explorer(Game game, GamePanel gamePanel,
                    TreePanel treePanel, int treeDepth) {

        lastMoves = new ArrayList<Move>();
        this.previewMade = false;
        this.game = game;
        this.gamePanel = gamePanel;
        this.treePanel = treePanel;
        this.treeDepth = treeDepth;
    }

    /**
     * Permet de passer l'arbre de jeu a l'executor et de le demarrer une fois
     * que toute l'initialisation de l'explorer est faite.
     */
    public void start() {
        explorationMemento = game.saveToMemento();
        makeTreePanel();
        executor.setTree(root);
        executor.start();
    }

    /**
     * Methode appellee lorsqu'un changement d'etat est fait. Elle permet
     * de repasser le nouvel arbre d'exploration a l'executor et de redemarrer
     * ce dernier.
     */
    public void restart() {
        if(haveToRemovePreferedMove) {
            game.resetFirstMovesOfPossibleMove();
        }
        game.resetFromMemento(explorationMemento);
        previewMade = false;

        makeTreePanel();
        executor.setMaxDepth(treeDepth);
        executor.setTree(root);
        executor.restart();

        haveToRemovePreferedMove = true;
    }

    /**
     * Cette methode permet de donner le nouvel arbre d'exploration au
     * panel qui l'affiche.
     */
    public void makeTreePanel() {
        root = makeTree(treeDepth);
        treePanel.previewMode(false);
        gamePanel.previewMode(false);

        treePanel.setTreeRootNode(root);
        treePanel.repaint();
        gamePanel.repaint();
    }

    /**
     * Cette methode permet de selectionner le premier noeud a evaluer.
     * @param path Le chemin du premier noeud à évaluer.
     */
    public void selectFirstNode(int[] path) {
        game.resetFromMemento(explorationMemento);

        game.setFirstMovesOfPossibleMoves(path);
        haveToRemovePreferedMove = false;
        restart();
    }

    /**
     * Cette methode permet de modifier la profondeur de l'arbre d'exploration.
     * @param treeDepth La nouvelle profondeur de l'arbre d'evaluation.
     */
    public void setTreeDepth(int treeDepth) {
        this.treeDepth = treeDepth;

        restart();
    }

    private TreeNode makeTree(int height) {
        MoveIterator iterator = game.getPossibleMoves();
        if(iterator.hasNext()) {
            TreeNode root = new TreeNode(null);
            makeTree(height - 1, root);
            return root.getChild(0);
        }
        return new LeafNode(null, 100);
    }

    private void makeTree(int height, TreeNode parent) {
        if(height == 0 || game.isFinish()) {
            LeafNode child = new LeafNode(parent, 100);
            parent.addChildNode(child);
        }
        else {
            TreeNode child = new TreeNode(parent);
            parent.addChildNode(child);
            MoveIterator iterator = game.getPossibleMoves();
            while(iterator.hasNext()) {
                Move move = iterator.next();
                try {
                    game.play(move);
                }
                catch(MoveException e) {
                    e.printStackTrace();
                }
                makeTree(height - 1, child);
                game.removeMove(move);
            }
        }
    }

    /**
     * Permet de changer l'etat initial en RESULT(etat initial, move).
     * @param move le movement a effectuer pour changer l'etat initial.
     */
    public void moveForward(Move move) {
        game.resetFromMemento(explorationMemento);

        if(!game.isFinish()) {
            // permet de donner la bonne piece au mouvement
            Move completeMove = game.completeMove(move);
            if(game.isPossibleMove(completeMove)) {
                try {
                    game.play(completeMove);
                }
                catch(MoveException e) {
                    e.printStackTrace();
                }
                lastMoves.add(0, completeMove);

                explorationMemento = game.saveToMemento();
            }

        restart();
        }
    }

    /**
     * Cette methode permet de changer l'etat initial en executant une serie
     * d'action dont le chemin du nouvel etat est donne par moves.
     * @param moves Le chemin menant au nouvel etat initial.
     */
    public void moveForward(int[] moves) {
        //load the last game state where explorer stop;
        game.resetFromMemento(explorationMemento);

        int size = moves.length;
        for(int i = 0; i < size; i++) {
            MoveIterator iterator = game.getPossibleMoves();
            Move tmp = null;
            try {
                tmp = iterator.getMove(moves[i]);
            }
            catch(MoveException exception) {
                exception.printStackTrace();
            }
            lastMoves.add(0, tmp);
            try {
                game.play(lastMoves.get(0));
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
        }

        //save the new state of game.
        explorationMemento = game.saveToMemento();

        restart();
    }

    /**
     * Cette methode permet d'activer le mode d'apercu suivant le chemin menant
     * a l'etat dont l'apercu est demande.
     * @param moves Le chemin menant au noeud dont l'apercu est demande.
     */
    public void preview(int[] moves) {
        previewMade = true;
        previewMemento = game.saveToMemento();
        game.resetFromMemento(explorationMemento);
        gamePanel.previewMode(true);
        treePanel.previewMode(true);
        TreeNode tmpNode = root;
        previewPath = moves;
        previewPathType = new NodeType[moves.length + 1];
        for(int i = 0; i <= moves.length; i++) {
            if(i == moves.length) {
                previewPathType[i] = tmpNode.getType();
                tmpNode.setType(NodeType.PREVIEW);
            }
            else {
                previewPathType[i] = tmpNode.getType();
                tmpNode.setType(NodeType.ANCESTOR_OF_PREVIEW);
                tmpNode = tmpNode.getChild(moves[i]);
            }
        }

        int size = moves.length;
        for(int i = 0; i < size; i++) {
            MoveIterator iterator = game.getPossibleMoves();
            Move tmp = null;
            try {
                tmp = iterator.getMove(moves[i]);
            }
            catch(MoveException exception) {
                exception.printStackTrace();
            }
            try {
                game.play(tmp);
            }
            catch(MoveException e) {
                e.printStackTrace();
            }
        }

        gamePanel.repaint();
        treePanel.repaint();
    }

    /**
     * Cette methode permet de quitter l'apercu pour revenir au mode normal.
     */
    public void quitPreview() {
        if(previewMemento != null && previewMade) {
            treePanel.previewMode(false);
            previewMade = false;
            TreeNode tmpNode = root;
            for(int i = 0; i <= previewPath.length; i++) {
                tmpNode.setType(previewPathType[i]);
                if(i < previewPath.length) {
                    tmpNode = tmpNode.getChild(previewPath[i]);
                }
            }
            game.resetFromMemento(previewMemento);
            gamePanel.previewMode(false);

            gamePanel.repaint();
            treePanel.repaint();
        }
    }

    /**
     * Cette methode permet d'annuler un mouvement pour que le nouvel etat
     * initial soit l'etat qui precede l'etat initial actuel.
     */
    public void removeLast() {
        //load the last game state where explorer stop;
        game.resetFromMemento(explorationMemento);

        if(lastMoves.size() > 0) {
            game.removeMove(lastMoves.remove(0));
        }

        //save the new state of game.
        explorationMemento = game.saveToMemento();

        restart();
    }

    /**
     * Cette methode permet d'ajouter l'executor qui sera charger de modifier
     * l'arbre d'exploration.
     * @param executor L'executor a ajouter.
     */
    public void addExecutor(Executor executor) {
        this.executor = executor;
    }
}
