package gui;

import tree.*;

import java.awt.Graphics;
import java.awt.Color;

public class JTree {
    public static int drawTree(int leftMargin, TreeNode node, int height,
                                                        int space, Graphics g) {
        if(node.getNbChild() == 0) {
            drawNode(node, leftMargin, height, g);

            return leftMargin;
        }
        else {
            JTreeMargin treeM = drawTree(leftMargin, node, 1, height, space, g);

            return treeM.getRightMargin();
        }
    }

    private static JTreeMargin drawTree(int leftMargin, TreeNode node,
                                 int depth, int height, int space, Graphics g) {
        int nbChild = node.getNbChild();
        int[] rightMargins = new int[nbChild + 1];
        rightMargins[0] = leftMargin;
        int[] childPositions = new int[nbChild];

        //determination de la position des noeuds fils.
        for(int i = 0; i < nbChild; i++) {
            JTreeMargin treeM = drawTree(rightMargins[i], node.getChild(i),
                                                   depth + 1, height, space, g);
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

            // si le noeud fils est une feuille on dessine un noeud feuille.
            if(node.getChild(i) instanceof LeafNode) {
                drawLeaf(node.getChild(i), childPositions[i],
                         (depth + 1) * height, g);
            }
            // sinon on dessin un noeud normal.
            else {
                drawNode(node.getChild(i), childPositions[i],
                         (depth + 1) * height, g);
            }
        }
        /*
         * si depth == 1 alors, on dessine la racine. Normalement c'est au
         * noeud père de dessiner le fils, mais la raçine n'en a pas.
         * elle dessine donc son noeud quand toutes les arêtes sont
         * dessinées
         */
        if(depth == 1) {
            drawNode(node, nodePos, depth * height, g);
        }

        return new JTreeMargin(nodePos, rightMargins[nbChild] + space);
    }

    public static void drawLeaf(TreeNode node, int x, int y, Graphics g) {
        if(node.getLabel() != null) {
            drawImportantNode(x, y, g);
            drawLeafLabel(x, y, node.getLabel(), g);
        }
        else {
            drawNode(x, y, g);
        }
    }

    public static void drawNode(TreeNode node, int x, int y, Graphics g) {
        drawNode(x, y, g);

        if(node.getLabel() != null) {
            drawLabel(x, y, node.getLabel(), g);
        }
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
        g.setColor(new Color(255, 50, 50));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
    }

    public static void drawLabel(int x, int y, String label, Graphics g) {
        int width = 40;
        int height = 20;
        int margin = 5;

        Color tmp = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(x - width - margin, y - height / 2, width, height);
        g.setColor(tmp);
        g.drawString(label, x - width, y + height / 2 - 5);
        g.drawRect(x - width - margin, y - height / 2, width, height);
    }

    public static void drawLeafLabel(int x, int y, String label, Graphics g) {
        int width = 40;
        int height = 20;
        int margin = 5;

        g.drawString(label, x, y + height);
    }
}
