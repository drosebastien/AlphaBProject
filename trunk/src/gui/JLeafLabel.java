package gui;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Cette classe permet de dessiner des labels pour les noeuds qui sont des
 * feuilles.
 * @param Sebastien Drobisz.
 */
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

    /**
     * Cette methode permet de dessiner le label sur un graphics donne.
     * @param g Le graphics sur lequel dessiner le label.
     */
    public void paintLabel(Graphics g) {
        int width = getWidth(g, getLabel());

        Color tmp = g.getColor();
        g.setColor(new Color(0, 0, 155));
        g.drawString(getLabel(), getX() - width / 2, getY() + 20);
        g.setColor(tmp);
    }
}
