package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Cette classe permet de dessiner sur un panel le noeud qui est en train d'etre
 * evalue par l'algorithme.
 * @author Sebastien Drobisz
 */
public class JCurrentNode extends JAncestorNode {

    /**
     * Ce constructeur permet de creer le noeud en lui fournissant son noeud
     * pere.
     * @param parent Le noeud pere au noeud courant.
     */
    public JCurrentNode(JNode parent) {
        super(parent);
    }

    /**
     * Cette méthode permet de dessiner le noeud sur un Graphics.
     * @param g Le Graphics où dessiner le noeud.
     */
    public void paintNode(Graphics g) {
        int circonf = 10;

        Color tmp = g.getColor();

        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(0, 0, 255));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(0, 0, 0));
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
    }

    /**
     * Cette methode permet de dessiner le label associe a un noeud sur un
     * graphics.
     * @param g Le graphics sur lequel dessiner le label.
     */
    public void paintLabel(Graphics g) {
        if(isLeaf()) {
            if(getLabel() != null) {
                JLeafLabel label = new JLeafLabel(getLabel(), x, y);
                label.paintLabel(g);
            }
        }
        else {
            super.paintLabel(g);
        }
    }
}
