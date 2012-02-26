package gui;

import tools.Line;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TreePanel extends JPanel {
    private static final int RECT_WIDTH = 46;
    private static final int RECT_HEIGHT = 26;

    public TreePanel() {
        super();

        int grayLvl = 120;
        setBackground(new Color(grayLvl, grayLvl, grayLvl));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        addEdge(g, getWidth() / 2, 30, getWidth() / 2 - 40, 120);
        addEdge(g, 30, 40, 110, 150);
        addEdge(g, 300, 400, 100, 450);
        addEdge(g, 300, 400, 100, 450);
        addEdge(g, 300, 250, 200, 350, false);
        addEdge(g, 400, 400, 600, 520);
        addEdge(g, 500, 400, 600, 250);
        addEdge(g, 850, 40, 750, 150);
        addEdge(g, 850, 40, 851, 150, false);
        addEdge(g, 850, 40, 951, 150, false);
        addEdge(g, 750, 150, 710, 260, false);
        addEdge(g, 750, 150, 790, 260, false);
    }

    public void addEdge(Graphics g, int xC1, int yC1, int xC2, int yC2) {
        addEdge(g, xC1, yC1, xC2, yC2, true);
    }

    public void addEdge(Graphics g, int xC1, int yC1, int xC2, int yC2, boolean test) {
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
        Line line = new Line();
        line.setLine(xC1, yC1, xC2, yC2);
        int y1 = yC1 + RECT_HEIGHT / 2;
        int x1 = (int) Math.round(line.getX(y1));
        int y2 = yC2 - RECT_HEIGHT / 2;
        int x2 = (int) Math.round(line.getX(y2));
        if(x1 < xC1 - RECT_WIDTH / 2 || x1 > xC1 + RECT_WIDTH) {
            int sense = 1;
            if(xC2 > xC1) {
                sense = -1;
            }
            x1 = xC1 - (sense * RECT_WIDTH / 2);
            y1 = (int) Math.round(line.getY(x1));
            x2 = xC2 + (sense * RECT_WIDTH / 2);
            y2 = (int) Math.round(line.getY(x2));
        }
        g.drawLine(x1, y1, x2, y2);
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
}
