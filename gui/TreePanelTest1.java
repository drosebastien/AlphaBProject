package gui;

import tools.Line;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TreePanelTest1 extends TreePanel {
    private static final int RECT_WIDTH = 30;
    private static final int RECT_HEIGHT = 26;

    public TreePanelTest1() {
        super();

        addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent e) {
                mouseClickedEvent(e);
            }
        });

        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(900, 650));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //
        g.drawRect(370, 270, RECT_WIDTH, RECT_HEIGHT);
        Font font = new Font("TeXGyreTermes", Font.PLAIN, 15);
        g.setFont(font);
        g.drawString("33", 375, 275 + RECT_HEIGHT / 2);
        g.drawLine(285, 475, 300, 535);
        g.drawLine(285, 475, 295, 535);
        g.drawLine(285, 475, 290, 535);
        g.drawLine(285, 475, 285, 535);
        g.drawLine(285, 475, 280, 535);
        g.drawLine(285, 475, 275, 535);
        g.drawLine(285, 475, 270, 535);

        g.drawLine(320, 475, 305, 535);
        g.drawLine(320, 475, 310, 535);
        g.drawLine(320, 475, 315, 535);
        g.drawLine(320, 475, 320, 535);
        g.drawLine(320, 475, 325, 535);
        g.drawLine(320, 475, 330, 535);
        g.drawLine(320, 475, 335, 535);

        g.drawLine(302, 425, 320, 475);
        g.drawLine(302, 425, 285, 475);
        //

        addEdge(g, getWidth() / 2, 30, getWidth() / 2 - 40, 120);
        addEdge(g, 30, 40, 110, 150);
        addEdge(g, 300, 400, 100, 450);
        addEdge(g, 300, 250, 200, 350, false, 0);
        addEdge(g, 400, 400, 600, 520);
        addEdge(g, 500, 400, 600, 250);
        addEdge(g, 850, 40, 750, 150, true, 0);
        addEdge(g, 850, 40, 851, 150, false, 0);
        addEdge(g, 850, 40, 951, 150, false, 0);
        addEdge(g, 750, 150, 710, 260, false, 0);
        addEdge(g, 750, 150, 790, 260, false, 0);
        addEdgeWithLabel(g, 890, 250, 750, 350, 30);
        addEdge(g, 750, 350, 790, 460, false, 20);
        addEdge(g, 750, 350, 710, 560, false, 30);
        addEdge(g, 200, 50, 300, 150, true, 20);
    }

    public void addEdge(Graphics g, int xC1, int yC1, int xC2, int yC2) {
        addEdge(g, xC1, yC1, xC2, yC2, true, 0);
    }

    public void addEdgeWithLabel(Graphics g, int xC1, int yC1, int xC2, int yC2, int label) {
        addEdge(g, xC1, yC1, xC2, yC2, true, label);
    }

    public void addEdge(Graphics g, int xC1, int yC1, int xC2, int yC2, boolean test, int label) { // si faux, c'est la composante la plus elevée qui n'est pas affichée.
        if(yC1 > yC2) {
            int tmp = xC1;
            xC1 = xC2;
            xC2 = tmp;
            tmp = yC1;
            yC1 = yC2;
            yC2 = tmp;
        }
        if(test) {
            addRectFromCenter(g, xC1, yC1);
        }
        addRectFromCenter(g, xC2, yC2);

        drawLine(g, xC1, yC1, xC2, yC2, label);
    }

    private void drawLine(Graphics g, int xC1, int yC1, int xC2, int yC2) {
        drawLine(g, xC1, yC1, xC2, yC2, 0);
    }

    private void drawLine(Graphics g, int xC1, int yC1, int xC2, int yC2, int space) {
        Line line = new Line();
        line.setLine(xC1, yC1, xC2, yC2);
        int y1 = yC1 + RECT_HEIGHT / 2;
        int x1 = (int) Math.round(line.getX(y1));
        int y2 = yC2 - RECT_HEIGHT / 2;
        int x2 = (int) Math.round(line.getX(y2));
        if(x1 < xC1 - RECT_WIDTH / 2 || x1 > xC1 + RECT_WIDTH / 2) {
            int sense = 1;
            if(xC2 > xC1) {
                sense = -1;
            }
            x1 = xC1 - (sense * RECT_WIDTH / 2);
            y1 = (int) Math.round(line.getY(x1));
            x2 = xC2 + (sense * RECT_WIDTH / 2);
            y2 = (int) Math.round(line.getY(x2));
        }
        if(space == 0) {
            g.drawLine(x1, y1, x2, y2);
        }
        else {
            int height = Math.abs(y1 - y2);
            int ordOfLabel = height * 3 / 4;
            g.drawLine(x1, y1, (int) Math.round(line.getX(y1 + ordOfLabel - space/2)), y1 + ordOfLabel - space/2);
            g.drawLine((int) Math.round(line.getX(y1 + ordOfLabel + space/2)), y1 + ordOfLabel + space/2, x2, y2);
        }
    }

    public void addRectFromCenter(Graphics g, int xCenter, int yCenter) {
        addRectFromCenter(g, xCenter, yCenter, RECT_WIDTH, RECT_HEIGHT);
    }

    public void addRectFromCenter(Graphics g, int xCenter,
                                  int yCenter, int width, int height) {
        addRect(g, xCenter - width / 2, yCenter - height/2, width, height);
    }

    public void addRect(Graphics g, int x, int y, int width, int height) {
        int grayLvl = 200;
        g.setColor(new Color(grayLvl, grayLvl, grayLvl));
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    public void mouseClickedEvent(MouseEvent e) {
        System.out.println("x : " + e.getX() + "; y : " + e.getY());
    }
}
