package gui;

import tree.*;

import java.awt.Graphics;
import java.awt.Color;

import java.util.ArrayList;

public class JNode {
        public static void addNode(TreeNode node, int x, int y, Graphics g,
                                            ArrayList<JNodePosition> nodesPos,
                                            ArrayList<Integer> treePos) {
        //*
        if(node.getType() == NodeType.CURRENTNODE) {
            JNode.addCurrent(node, x, y, g, nodesPos, treePos);
        }
        else if(node.getType() == NodeType.ANCESTOR_OF_CURRENT_NODE) {
            JNode.addAncestor(node, x, y, g, nodesPos, treePos);
        }
        // sinon on dessin un noeud normal.
        else if(node.getType() == NodeType.VIEWED_NODE) {
            JNode.addViewedNode(node, x, y, g, nodesPos, treePos);
        }
        else {
            JNode.addNormalNode(node, x, y, g, nodesPos, treePos);
        }
        //*/
    }

    public static void addAncestor(TreeNode node, int x, int y, Graphics g,
                                            ArrayList<JNodePosition> nodesPos,
                                            ArrayList<Integer> treePos) {
        if(node.getLabel() != null) {
            drawAncestorNode(x, y, g);
            drawLabel(x, y, node.getLabel(), g);
        }
        else {
            drawAncestorNode(x, y, g);
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < treePos.size(); i++) {
            list.add(treePos.get(i));
        }

        nodesPos.add(new JNodePosition(x, y, list));
    }

    public static void addCurrent(TreeNode node, int x, int y, Graphics g,
                                            ArrayList<JNodePosition> nodesPos,
                                            ArrayList<Integer> treePos) {
        if(node.getLabel() != null) {
            drawImportantNode(x, y, g);
            drawLeafLabel(x, y, node.getLabel(), g);
        }
        else {
            drawImportantNode(x, y, g);
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < treePos.size(); i++) {
            list.add(treePos.get(i));
        }

        nodesPos.add(new JNodePosition(x, y, list));
    }

    public static void addViewedNode(TreeNode node, int x, int y, Graphics g,
                                            ArrayList<JNodePosition> nodesPos,
                                            ArrayList<Integer> treePos) {
/**
        if(node.getLabel() != null) {
            drawViewedNode(x, y, g);
            drawLabel(x, y, node.getLabel(), g);
        }
*/
        drawViewedNode(x, y, g);

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < treePos.size(); i++) {
            list.add(treePos.get(i));
        }

        nodesPos.add(new JNodePosition(x, y, list));
    }

    public static void addNormalNode(TreeNode node, int x, int y, Graphics g,
                                            ArrayList<JNodePosition> nodesPos,
                                            ArrayList<Integer> treePos) {
        drawNode(x, y, g);

        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < treePos.size(); i++) {
            list.add(treePos.get(i));
        }

        nodesPos.add(new JNodePosition(x, y, list));
    }

    public static void drawNode(int x, int y, Graphics g) {
        int circonf = 6;
        Color tmp = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
    }

    public static void drawAncestorNode(int x, int y, Graphics g) {
        int circonf = 6;
        Color tmp = g.getColor();
        g.setColor(new Color(255, 255, 0));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
    }

    public static void drawViewedNode(int x, int y, Graphics g) {
        int circonf = 6;
        Color tmp = g.getColor();
        g.setColor(new Color(0, 0, 255));
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
