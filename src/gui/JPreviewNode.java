package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Cette classe permet de representer des noeuds qui peuvent etre dessine sur
 * un panel. Ce noeud est celui qui est selectionne pour l'aperçu.
 * @author Sebastien Drobisz.
 */
public class JPreviewNode extends JPreviewAncestorNode {

    /**
     * Ce constructeur permet de creer un noeud en fournissant leur noeud pere.
     * @param parent Le noeud pere.
     */
    public JPreviewNode(JNode parent) {
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
        g.setColor(new Color(255, 0, 0));
        g.fillOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(new Color(0, 0, 0));
        g.drawOval(x - circonf / 2, y - circonf / 2, circonf, circonf);
        g.setColor(tmp);
    }

    /**
     * Cette methode permet de dessiner le label associe a un noeud sur un
     * graphic.
     * @param g Le Graphics où dessiner le label.
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
