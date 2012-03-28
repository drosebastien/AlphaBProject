package gui;

import tree.*;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Cette classe permet la création d'un arbre graphique à partir d'un arbre
 * constitué de TreeNode.
 * @author Sebastien Drobisz.
 */
public class JTree{
    private int leftMargin;
    private int tmpLeftMargin;
    private int topMargin;
    private int heightOfNode;
    private int gap;
    private JNode root;

    /**
     * Ce constructeur permet d'initialiser les constantes caractérisant l'arbre
     * et ses noeuds.
     * @param leftMargin La marge gauche où commence l'arbre.
     * @param topMargin La marge du dessus où est placé la racine.
     * @param heightOfNode La différence de hauteur entre deux noeuds.
     * @param gap La distance minimum en largeur entre deux noeuds de même père.
     */
    public JTree(int leftMargin, int topMargin, int heightOfNode, int gap) {
        this.leftMargin = leftMargin;
        this.tmpLeftMargin = leftMargin;
        this.topMargin = topMargin;
        this.heightOfNode = heightOfNode;
        this.gap = gap;
    }

    /**
     * Permet d'initialiser l'arbre graphique à partir d'un arbre défini par
     * des TreeNode.
     * @param node La racine de l'arbre constitué de TreeNode.
     */
    public void initTree(TreeNode node) {
        tmpLeftMargin = leftMargin;
        createTree(node, null, topMargin);
    }

    /*
     * Méthode permettant vraiment de lancer la création de l'arbre.
     */
    private void createTree(TreeNode node, JNode parentNode, int depth) {

        JNode currentNode = createNode(node, parentNode);
        currentNode.setY(depth);
        if(parentNode == null) { // si pas de père
            root = currentNode;  // alors noeud courant = racine de l'arbre.
        }
        else {
            parentNode.addChildNode(currentNode);
        }

        if(node.getNbChild() > 0) { // si ce n'est pas une feuille.
            for(int i = 0; i < node.getNbChild(); i++) {
                 createTree(node.getChild(i), currentNode,
                            depth + heightOfNode);
            }

            // position en x est calculé par rapport à la position de son fils
            // le plus à gauche (g) avec celui le plus à droite (d).
            // (d - g) / 2 + g
            int nbChild = currentNode.getNbChild();
            int g = ((JNode) currentNode.getChild(0)).getX();
            int d = ((JNode) currentNode.getChild(nbChild - 1)).getX();

            currentNode.setX((int) ((d - g) / 2. + g));
        }
        else { // si c'est une feuille la position en x = la marge de gauche.
            currentNode.setX(tmpLeftMargin);
        }

        tmpLeftMargin += gap;
    }

    /**
     * Cette méthode retourne le chemin menant au noeud possédant les
     * coordonnées passées en paramètre. Si aucun noeud ne corresponde, une
     * Exception est lancée.
     * @param x L'abscisse du noeud potentiel à chercher.à rechercher.
     * @param y L'ordonnée du noeud potentiel 
     */
    public int[] getPathToCoordinate(int x, int y)
                                            throws NodeNotFoundException {
        ArrayList<Integer> path = new ArrayList<Integer>();

        boolean isNode = isPathToCoordinate(x, y, root, path);
        if(! isNode) {
            throw new NodeNotFoundException(x, y);
        }

        int[] tabPath = new int[path.size()];
        int i = 0;
        while(path.size() > 0) {
            tabPath[i] = path.remove(0);
            i++;
        }

        return tabPath;
    }

    private boolean isPathToCoordinate(int x, int y, JNode current,
                                       ArrayList<Integer> path) {

        if(x < current.getX() + 4 && x > current.getX() - 4 &&
           y < current.getY() + 4 && y > current.getY() - 4) {

           return true;
        }

        for(int i = 0; i < current.getNbChild(); i++) {
            if(isPathToCoordinate(x, y, (JNode) current.getChild(i), path)) {
                path.add(0, i);
                return true;
            }
        }

        return false;
    }

    /**
     * Cette méthode permet de connaitre la position du point le plus à droite
     * de l'arbre.
     * @return l'abscisse du point le plus à droite de l'arbre.
     */
    public int getRightMargin() {
        return tmpLeftMargin;
    }

    /*
     * Méthode permettant la création d'un noeud graphique.
     */
    private JNode createNode(TreeNode node, JNode parentNode) {
        JNode newNode = null;

        switch(node.getType()) {
            case VIEWED:
                newNode = new JViewedNode(parentNode);
                break;
            case CURRENT:
                newNode = new JCurrentNode(parentNode);
                break;
            case ANCESTOR_OF_CURRENT:
                newNode = new JAncestorNode(parentNode);
                break;
            case IMPORTANT:
                newNode = new JImportantNode(parentNode);
                break;
            case ANCESTOR_OF_IMPORTANT:
                newNode = new JAncestorImportantNode(parentNode);
                break;
            default:
                newNode = new JNode(parentNode);
        }

        newNode.copyState(node);

        return newNode;
    }

    /**
     * Cette méthode dessine un arbre initialisé sur un Graphics donné.
     * @param g Le Graphics sur lequel dessiner l'arbre.
     */
    public void paintComponent(Graphics g) throws Exception {
        if(root == null) {
            throw new Exception();
        }
        paintTree(g, root);
        root.paintNode(g);
        root.paintLabel(g);
    }

    private void paintTree(Graphics g, JNode current) {
        for(int i = 0; i < current.getNbChild(); i++) {
            paintTree(g, (JNode) current.getChild(i));
        }

        for(int i = 0; i < current.getNbChild(); i++) {
            ((JNode) current.getChild(i)).printEdgeToParent(g);
            ((JNode) current.getChild(i)).paintNode(g);
            ((JNode) current.getChild(i)).paintLabel(g);
        }
    }
}
