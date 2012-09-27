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
        int size = 5;
        Color[] colors = new Color[]{new Color(255, 0, 0),
                                   new Color(0, 0, 0)};

        Color tmp = g.getColor();
        drawTriangle(colors, size, g);
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
