package gui;

import tree.*;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Cette classe représente le nœud normal d'un arbre graphique.
 * @author Sebastien Drobisz
 */
public class JNode extends TreeNode{
    protected int x;
    protected int y;

    /**
     * Le constructeur d'un JNode permet de définir le père du noeud créé.
     * null si pas de père.
     * @param parent Le noeud père du noeud à créer.
     */
    public JNode(JNode parent) {
        super(parent);
    }

    /**
     * Cette méthode permet de copier le contenu d'un TreeNode, i.e., son type
     * et son label dans le noeud graphique.
     * @param node Le TreeNode dont les données doivent être copiée.
     */
    public void copyState(TreeNode node) {
        setType(node.getType());
        setLabel(node.getLabel());
    }

    /**
     * Cette méthode permet de donner la position en abscisse du noeud.
     * @param x La position en abscisse du noeud.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Cette méthode permet de donner la position en ordonnée du noeud.
     * @param y La position en ordonnée du noeud.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Cette méthode permet de retourner la position en abscisse du noeud.
     * @return L'abscisse du noeud.
     */
    public int getX() {
        return x;
    }

    /**
     * Cette méthode permet de retourner la position en ordonnée du noeud.
     * @return L'ordonnée du noeud.
     */
    public int getY() {
        return y;
    }

    /**
     * Cette méthode permet de dessiner le noeud sur un Graphics.
     * @param g Le Graphics où dessiner le noeud.
     */
    public void paintNode(Graphics g) {
        int circonf = 6;

        Color tmp = g.getColor();
        int grayLvl = 150;
        g.setColor(new Color(grayLvl, grayLvl, grayLvl + 10));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(255, 255, 255, 70));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(0, 0, 0, 70));
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
    }

    /**
     * Cette méthode permet de dessiner l'arête du noeud vers son père (s'il
     * existe) sur un Graphics.
     * @param g Le graphics où dessiner l'arête.
     */
    public void printEdgeToParent(Graphics g) {
        if(getParent() != null) {
            Color tmp = g.getColor();
            g.setColor(new Color(0, 0, 0, 70));
            g.drawLine(x, y,
                    ((JNode) getParent()).getX(), ((JNode) getParent()).getY());
            g.setColor(tmp);
        }
    }

    /**
     * Cette méthode permet de dessiner le label associé à un noeud sur un
     * Graphics.
     * @param g Le Graphics où dessiner le label.
     */
    public void paintLabel(Graphics g) {
        //JLabel label = new NormalLabel(getLabel());
        //label.paint(g);
    }
}
