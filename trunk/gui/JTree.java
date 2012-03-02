package gui;

import tree.*;

import java.awt.Graphics;
import java.awt.Color;

public class JTree {
    public static int drawTree(int leftMargin, TreeNode node, int height, int space, Graphics g) {
        if(node.getNbChild() == 0) {
            drawNode(leftMargin, height, g);
            return leftMargin;
        }

        return drawTree(leftMargin, node, 1, height, space, g)[0];
    }

    private static int[] drawTree(int leftMargin, TreeNode node, int depth, int height, int space, Graphics g) {
        int nbChild = node.getNbChild();
        int[] positions = new int[nbChild + 1];
        positions[0] = leftMargin;
        int[] center = new int[nbChild];

        //determination de la position des noeuds.
        for(int i = 0; i < nbChild; i++) {
            int[] tab = drawTree(positions[i], node.getChild(i), depth + 1, height, space, g);
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
            if(node.getChild(i) instanceof LeafNode && ((LeafNode) node.getChild(i)).getValue() == 4) {
                drawImportantNode(center[i], (depth + 1) * height, g);
            }
            else{
                drawNode(center[i], (depth + 1) * height, g);
            }
        }
        //dessine le noeud pour la racine.
        if(depth == 1) {
            drawNode(x, depth * height, g);
        }

        return new int[]{positions[nbChild] + space, x};
    }

    public static void drawNode(int x, int y, Graphics g) {
        int circonf = 6;
        Color tmp = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
    }

    public static void drawImportantNode(int x, int y, Graphics g) {
        int circonf = 8;
        Color tmp = g.getColor();
        g.setColor(new Color(255, 255, 0));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
    }
}
