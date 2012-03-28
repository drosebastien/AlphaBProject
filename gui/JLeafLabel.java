package gui;

import java.awt.Graphics;
import java.awt.Color;

public class JLeafLabel extends JLabel{
    /**
     * Ce constructeur permet d'initialiser la valeur du label ainsi que
     * les coodonnées du noeud à coté du quel ce label doit être affiché.
     * @param label Le label à afficher.
     * @param x L'abscisse de la position du noeud.
     * @param y L'ordonnée de la position du noeud.
     */
    public JLeafLabel(String label, int x, int y) {
        super(label, x, y);
    }

    public void paintLabel(Graphics g) {
        Color tmp = g.getColor();
        g.setColor(new Color(0, 0, 155));
        g.drawString(getLabel(), getX() - 5, getY() + 20);
        g.setColor(tmp);
    }
}
