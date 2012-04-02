package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.util.ArrayList;

public class NormalTreePanel extends TreePanel {
    private static final int RADIUSNODE = 4;

    private JTree jTreeRoot;

    public NormalTreePanel() {
        super();

        addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent e) {
                mouseClickedEvent(e);
            }
        });

        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(700, 800));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        if(root != null) {
            jTreeRoot = new JTree(55, 30, 100, 8);
            jTreeRoot.initTree(root);
            try {
                jTreeRoot.paintComponent(g);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            int rightMargin = jTreeRoot.getRightMargin();
            setPreferredSize(new Dimension(rightMargin, 800));
            setMaximumSize(new Dimension(rightMargin, 800));
        }
    }

    public void mouseClickedEvent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if(jTreeRoot != null) {
            try {
                int[] path = jTreeRoot.getPathToCoordinate(x, y);
                this.clickOnNode(path);
            }
            catch(NodeNotFoundException error) {
            }
        }
    }
}
