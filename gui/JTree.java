package gui;

import tree.*;

import java.awt.Graphics;
import java.awt.Color;

import java.util.ArrayList;

public class JTree {
    public static int drawTree(int leftMargin, TreeNode node, int height,
                     int space, Graphics g, ArrayList<JNodePosition> nodesPos) {
        ArrayList<Integer> treePos = new ArrayList<Integer>();
        if(node.getNbChild() == 0) {
            JNode.addNode(node, leftMargin, height, g, nodesPos, treePos);

            return leftMargin;
        }
        else {
            JTreeMargin treeM = drawTree(leftMargin, node, 1, height, space,
                                         g, nodesPos, treePos);

            return treeM.getRightMargin();
        }
    }

    private static JTreeMargin drawTree(int leftMargin, TreeNode node,
                                int depth, int height, int space, Graphics g,
                                ArrayList<JNodePosition> nodesPos,
                                ArrayList<Integer> treePos) {

        int nbChild = node.getNbChild();
        int[] rightMargins = new int[nbChild + 1];
        rightMargins[0] = leftMargin;
        int[] childPositions = new int[nbChild];

        //determination de la position des noeuds fils.
        for(int i = 0; i < nbChild; i++) {
            // on ajoute l'entrée du fils. En fait elle sera effacée juste après
            // et ne sera plus présente dans le noeud courant. toutefois, elle 
            // le restera pour les fils du noeuds courant. Ainsi, on aura les
            // entrée jusqu'au père.
            treePos.add(i);
            JTreeMargin treeM = drawTree(rightMargins[i], node.getChild(i),
                                         depth + 1, height, space, g, nodesPos,
                                         treePos);
            treePos.remove(treePos.size() - 1); // efface la dernière entrée.
            rightMargins[i + 1] = treeM.getRightMargin();
            childPositions[i] = treeM.getCenterOfRoot();
        }

        // si le noeud courant est une feuille, on ne dessine rien
        if(nbChild == 0) {
            return new JTreeMargin(leftMargin, rightMargins[0] + space);
        }
        // sinon on dessine un ligne vers chacun de ses fils.
        int nodePos = (childPositions[nbChild - 1] -
                       childPositions[0]) / 2 + childPositions[0];

        for(int i = 0; i < nbChild; i++) {
            g.drawLine(childPositions[i], (depth + 1) * height, nodePos,
                       depth * height);

            // à partir d'ici, les seules entrées présente dans treePos sont 
            // celle jusqu'au père. Il faut donc rajoutere celle du fils.
            treePos.add(i);

            JNode.addNode(node.getChild(i), childPositions[i],
                     (depth + 1) * height, g, nodesPos, treePos);

            treePos.remove(treePos.size() - 1); //on supprime la dernière entrée de la liste.
        }
        /*
         * si depth == 1 alors, on dessine la racine. Normalement c'est au
         * noeud père de dessiner le fils, mais la raçine n'en a pas.
         * elle dessine donc son noeud quand toutes les arêtes sont
         * dessinées
         */
        if(depth == 1) {
            JNode.addNode(node, nodePos, depth * height, g, nodesPos, treePos);
        }

        return new JTreeMargin(nodePos, rightMargins[nbChild] + space);
    }
}
