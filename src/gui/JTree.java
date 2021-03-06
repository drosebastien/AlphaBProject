package gui;

import tree.*;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Cette classe permet la creation d'un arbre graphique a partir d'un arbre
 * constitue de TreeNode.
 * @author Sebastien Drobisz.
 */
public class JTree{
    private int maxDepth;
    private int leftMargin;
    private int tmpLeftMargin;
    private int topMargin;
    private int heightOfNode;
    private int gap;
    private JNode root;

    /**
     * Ce constructeur permet d'initialiser les constantes caracterisant l'arbre
     * et ses noeuds.
     * @param leftMargin La marge gauche où commence l'arbre.
     * @param topMargin La marge du dessus où est place la racine.
     * @param heightOfNode La difference de hauteur entre deux noeuds.
     * @param gap La distance minimum en largeur entre deux noeuds de meme pere.
     */
    public JTree(int leftMargin, int topMargin, int heightOfNode, int gap) {
        maxDepth = 100;
        this.leftMargin = leftMargin;
        this.tmpLeftMargin = leftMargin;
        this.topMargin = topMargin;
        this.heightOfNode = heightOfNode;
        this.gap = gap;
    }

    /**
     * Permet d'initialiser l'arbre graphique a partir d'un arbre defini par
     * des TreeNode.
     * @param node La racine de l'arbre constitue de TreeNode.
     */
    public void initTree(TreeNode node) {
        tmpLeftMargin = leftMargin;
        createTree(node, null, topMargin);
    }

    /*
     * Methode permettant vraiment de lancer la creation de l'arbre.
     */
    private void createTree(TreeNode node, JNode parentNode, int depth) {

        JNode currentNode = createNode(node, parentNode);
        currentNode.setY(depth);
        if(parentNode == null) { // si pas de pere
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

            // position en x est calcule par rapport a la position de son fils
            // le plus a gauche (g) avec celui le plus a droite (d).
            // (d - g) / 2 + g
            int nbChild = currentNode.getNbChild();
            int g = ((JNode) currentNode.getChild(0)).getX();
            int d = ((JNode) currentNode.getChild(nbChild - 1)).getX();

            currentNode.setX((int) ((d - g) / 2. + g));
        }
        else { // si c'est une feuille la position en x = la marge de gauche.
            currentNode.setX(tmpLeftMargin);
            if(depth > maxDepth) {
                maxDepth = depth;
            }
        }

        tmpLeftMargin += gap;
    }

    /**
     * Cette methode retourne le chemin menant au noeud possedant les
     * coordonnees passees en parametre. Si aucun noeud ne corresponde, une
     * Exception est lancee.
     * @param x L'abscisse du noeud potentiel a chercher.a rechercher.
     * @param y L'ordonnee du noeud potentiel 
     * @return Le chemin correspondant au noeud ayant pour les coordonnees
     * passee en parametre.
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

        int dist = distance(x, current.getX(), y, current.getY());
        if(dist <= 5) {
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

    private int distance(int x1, int x2, int y1, int y2) {
        double width = Math.abs(x1 - x2);
        double height = Math.abs(y1 - y2);

        int dist = (int) Math.round(Math.sqrt(width * width + height * height));

        return dist;
    }

    /**
     * Cette methode permet de connaitre la position du point le plus a droite
     * de l'arbre.
     * @return l'abscisse du point le plus a droite de l'arbre.
     */
    public int getRightMargin() {
        return tmpLeftMargin;
    }

    /**
     * Cette methode permet de retourner l'ordonnee du point le plus profond
     * profond dans l'arbre.
     * @return L'ordonne du point le plus bas dans l'arbre.
     */
    public int getBottomMargin() {
        return maxDepth + 50;
    }

    /*
     * Methode permettant la creation d'un noeud graphique.
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
            case PREVIEW:
                newNode = new JPreviewNode(parentNode);
                break;
            case ANCESTOR_OF_PREVIEW:
                newNode = new JPreviewAncestorNode(parentNode);
                break;
            default:
                newNode = new JNode(parentNode);
        }

        newNode.copyState(node);

        return newNode;
    }

    /**
     * Cette methode dessine un arbre initialise sur un Graphics donne.
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
