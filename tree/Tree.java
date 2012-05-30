package tree;

import java.util.ArrayList;

/**
 * Cette classe permet d'obtenir des informations sur un arbre.
 * @author Sebastien Drobisz.
 */
public class Tree {
    /**
     * Cette methode permet de connaitre le nombre de noeud present dans un
     * arbre a une profondeur donnee.
     * @param depth La profondeur.
     * @param node La racine de l'arbre.
     */
    public static int getNbNodeToDepth(int depth, TreeNode node) {
        int nbNodes = 0;

        if(depth == 0 || node.getNbChild() == 0) {
            //System.out.println(((LeafNode) node).getValue());
            return 1;
        }
        else {
            for(int i = 0; i < node.getNbChild(); i++) {
                nbNodes += getNbNodeToDepth(depth - 1, node.getChild(i));
            }
        }

        return nbNodes;
    }

    /**
     * Cette methode permet de reinitialise tous les etats des noeuds d'un
     * arbre à noeud vu.
     * @param node La racine de l'arbre a reinitialiser.
     */
    public static void removeStatesOfTree(TreeNode node) {
        node.setType(NodeType.NEITHER);
        node.removeLabel();
        for(int i = 0; i < node.getNbChild(); i++) {
            removeStatesOfTree(node.getChild(i));
            
        }
    }

    /**
     * Cette methode permet d'obtenir le noeud qui est a une position dans
     * un arbre donne.
     * @param pos La position du noeud.
     * @param node La racine de l'arbre.
     * @return Le noeud voulu.
     */
    public static TreeNode getNode(ArrayList<Integer> pos, TreeNode node) {
        if(pos.size() == 0) {
            return node;
        }

        TreeNode childNode = node.getChild(pos.remove(0));
        return getNode(pos, childNode);
    }

    /**
     * Permet de connaitre la profondeur maximale d'un arbre donne.
     * @param node La racine de l'arbre.
     * @return La profondeur maximale
     */
    public static int getMaximumDepth(TreeNode node) {
        int max = 0;

        if(node.getNbChild() == 0) {
            return 1;
        }

        for(int i = 0; i < node.getNbChild(); i++) {
            int subTreeDepth = getMaximumDepth(node.getChild(i));
            if(max < subTreeDepth) {
                max = subTreeDepth;
            }
        }

        return max + 1;
    }

    /**
     * Cette methode permet de creer un arbre d'une profondeur donne et d'un
     * facteur de branchement donne.
     * @param depth La profondeur
     * @param breath Le facteur de branchement
     * @return La racine de l'arbre cree.
     */
    public static TreeNode makeTree(int depth, int breath) {
        if(depth == 1) {
            return new LeafNode(null, 42);
        }

        TreeNode root = new TreeNode(null);
        makeTree(depth, breath, root);
        return root;
    }

    private static void makeTree(int depth, int breath, TreeNode parent) {
        if(depth == 2) {
            for(int i = 0; i < breath; i++) {
                LeafNode node = new LeafNode(parent, i);
                parent.addChildNode(node);
            }
        }
        else {
            for(int i = 0; i < breath; i++) {
                TreeNode node = new TreeNode(parent);
                makeTree(depth - 1, breath, node);
                parent.addChildNode(node);
            }
        }
    }
}
