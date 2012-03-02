package gui;

import tree.*;

import java.awt.Graphics;

public class JTree {
    public static int[] drawTree(int leftMargin, TreeNode node, Graphics g) {
        return drawTree(leftMargin, node, 1, g);
    }

    private static int[] drawTree(int leftMargin, TreeNode node, int depth, Graphics g) {
        int space = 3;
        int height = 40;
        int nbChild = node.getNbChild();
        int[] positions = new int[nbChild + 1];
        int[] center = new int[nbChild];
        positions[0] = leftMargin;
        //determination de la position des noeuds.
        for(int i = 0; i < nbChild; i++) {
            int[] tab = drawTree(positions[i], node.getChild(i), depth + 1, g);
            positions[i + 1] = tab[0];
            center[i] = tab[1];
        }
        //dessin des arêtes.
        //si le noeud courant est une feuille.
        if(nbChild == 0) {
            return new int[]{positions[0] + space, leftMargin};
        }
        //sinon
        int x = (center[nbChild - 1] - center[0]) / 2 + center[0];
        for(int i = 0; i < nbChild; i++) {
            g.drawLine(center[i], (depth + 1) * height, x, depth * height);
        }

        return new int[]{positions[nbChild] + space, x};
    }
}
