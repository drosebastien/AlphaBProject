package gui;

import tree.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class TreePanelTest2 extends TreePanel {
    public TreePanelTest2() {
        super();

        addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent e) {
                mouseClickedEvent(e);
            }
        });

        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(100, 800));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        TreeNode root = Tree.makeTree(8, 2);
        int[] tab = JTree.drawTree(20, root, g);
        setPreferredSize(new Dimension(tab[0], 800));
    }

    public void mouseClickedEvent(MouseEvent e) {
        System.out.println("x : " + e.getX() + "; y : " + e.getY());
    }
}
