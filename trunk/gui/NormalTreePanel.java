package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.util.ArrayList;

public class NormalTreePanel extends TreePanel {
    private ArrayList<JNodePosition> list;

    public NormalTreePanel() {
        super();
        list = new ArrayList<JNodePosition>();

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

        list = new ArrayList<JNodePosition>();
        if(root != null) {
            int rightMargin = JTree.drawTree(50, root, 80, 8, g, list);
            setPreferredSize(new Dimension(rightMargin, 800));
            setMaximumSize(new Dimension(rightMargin, 800));
        }
    }

    public void mouseClickedEvent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //System.out.println("x : " + x + "; y : " + y);
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).isIn(x - 5, y - 5, x + 5, y + 5)) {
                //System.out.println(list.get(i));
                controller.clickOnNode(isInExplorerMode(),
                                       list.get(i).getTreePos());
            }
        }
    }

    public void nextEvent() {
        controller.progress(isInExplorerMode());
    }

    public void previousEvent() {
        controller.removeLast(isInExplorerMode());
    }
}
