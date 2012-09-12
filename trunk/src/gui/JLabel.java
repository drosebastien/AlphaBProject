package gui;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;

/**
 * Cette classe permet de creer diffent label qui sont associe a l'evaluation
 * des noeuds.
 * @author Sebastien  Drobisz.
 */
public class JLabel {
    private String label;
    private int x;
    private int y;

    /**
     * Ce constructeur permet d'initialiser la valeur du label ainsi que
     * les coodonnées du noeud à coté du quel ce label doit être affiché.
     * @param label Le label à afficher.
     * @param x L'abscisse de la position superieure gauche du noeud.
     * @param y L'ordonnée de la position superieure gauchedu noeud.
     */
    public JLabel(String label, int x, int y) {
        this.label= label;
        this.x = x;
        this.y = y;
    }

    /**
     * Cette methode permet de retourner le label sous forme de chaine de
     * caracteres.
     * @return La chaine de caractere du label.
     */
    protected String getLabel() {
        return label;
    }

    /**
     * Cette methode permet de retourner l'abscisse de la position superieure
     * gauche donne au label.
     * @return L'abscisse de la position superieur gauche du noeud
     */
    protected int getX() {
        return x;
    }

    /**
     * Cette methode permet de retourner l'ordonnee de la position superieure
     * gauche donne au label.
     * @return L'ordonnee de la position superieur gauche du noeud
     */
    protected int getY() {
        return y;
    }

    /**
     * Cette methode permet de retourner la largeur que prend une chaine
     * de caracteres sur un graphics.
     * @param g Le graphics sur lequel la chaine de caratere est dessine
     * @param word La chaine de caractere
     * @return La largeur que prend la chaine de caratere.
     */
    protected int getWidth(Graphics g, String word) {
        FontMetrics metrics = g.getFontMetrics();
        return metrics.stringWidth(word);
    }

    /**
     * Cette methode permet de dessiner le label sur un graphics donne.
     * @param g Le graphics sur lequel dessiner le label.
     */
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
