package gui;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;

public class JLabel {

    private String label;
    private int x;
    private int y;

    /**
     * Ce constructeur permet d'initialiser la valeur du label ainsi que
     * les coodonnées du noeud à coté du quel ce label doit être affiché.
     * @param label Le label à afficher.
     * @param x L'abscisse de la position du noeud.
     * @param y L'ordonnée de la position du noeud.
     */
    public JLabel(String label, int x, int y) {
        this.label= label;
        this.x = x;
        this.y = y;
    }

    protected String getLabel() {
        return label;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected int getWidth(Graphics g, String word) {
        FontMetrics metrics = g.getFontMetrics();
        return metrics.stringWidth(word);
    }

    public void paintLabel(Graphics g) {
        int width = getWidth(g, " " + getLabel() + " ");
        int height = 20;
        int leftD = (int) (width * 4./5);

        Color tmp = g.getColor();
        g.setColor(new Color(255, 255, 255));
        g.fillRect(x - leftD, y - (height + 5),  width, height);
        g.setColor(new Color(0, 0, 0));
        g.drawString(" " + getLabel(), x - leftD, y - height + 10);
        g.drawRect(x - leftD, y - (height + 5), width, height);
        g.setColor(tmp);
    }
}