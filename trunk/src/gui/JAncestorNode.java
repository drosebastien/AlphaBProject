package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Cette classe permet de representer sur un panel un noeud ancetre au noeud
 * en cours d'exploration.
 * @author Sebastien Drobisz
 */
public class JAncestorNode extends JNode {

    /**
     * Ce constructeur permet de creer un noeud ancetre au noeud courant en
     * lui fournissant sont noeud pere.
     * @param parent Le noeud pere.
     */
    public JAncestorNode(JNode parent) {
        super(parent);
    }

    /**
     * Cette méthode permet de dessiner le noeud sur un Graphics.
     * @param g Le Graphics où dessiner le noeud.
     */
    public void paintNode(Graphics g) {
        int circonf = 8;

        Color tmp = g.getColor();

        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(0, 0, 255));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(0, 0, 0));
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
    }

    /**
     * Cette méthode permet de dessiner l'arête du noeud vers son père (s'il
     * existe) sur un Graphics.
     * @param g Le graphics sur lequel dessiner l'arête.
     */
    public void printEdgeToParent(Graphics g) {
        if(getParent() != null) {
            Color tmp = g.getColor();
            g.setColor(new Color(0, 0, 255));
            g.drawLine(x, y,
                    ((JNode) getParent()).getX(), ((JNode) getParent()).getY());
            g.setColor(tmp);
        }
    }

    /**
     * Cette methode permet de dessiner le label associe a un noeud sur un
     * graphics.
     * @param g Le graphics sur lequel dessiner le label.
     */
    public void paintLabel(Graphics g) {
        if(getLabel() == null) {
            System.out.println("No label");
        }

        JLabel label = new JLabel(getLabel(), x, y);
        label.paintLabel(g);
    }
}
