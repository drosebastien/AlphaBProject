package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Cette classe permet de representer des noeuds qui peuvent etre dessine sur
 * un panel. Les noeuds sont des noeuds deja explore mais  qui n'ont rien
 * de special.
 * @author Sebastien Drobisz
 */
public class JViewedNode extends JNode {
    /**
     * Ce constructeur permet de creer un noeud en lui donnant son pere.
     * @param parent Le noeud pere.
     */
    public JViewedNode(JNode parent) {
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
        g.setColor(new Color(255, 255, 255));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(0, 0, 0));
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
            g.setColor(new Color(0, 0, 0));
            g.drawLine(x, y,
                    ((JNode) getParent()).getX(), ((JNode) getParent()).getY());
            g.setColor(tmp);
        }
    }

    /**
     * Cette methode permet de dessiner le label associe a un noeud.
     * En l'occurence, le label n'est pas dessine pour un simple noeud.
     * @param g Le graphics sur lequel le label devrait etre dessine.
     */
    public void paintLabel(Graphics g) {
    }
}
