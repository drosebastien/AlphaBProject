package gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Cette classe permet de dessiner les noeuds qui constituent le meilleur choix
 * sur un panel.
 * @author Sebastien Drobisz.
 */
public class JImportantNode extends JNode {

    /**
     * Ce constructeur permet de creer le noeud en lui fournissant son noeud
     * pere.
     * @param parent Le noeud pere au noeud courant.
     */
    public JImportantNode(JNode parent) {
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
        g.setColor(new Color(200, 200, 255));
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
            g.setColor(new Color(200, 200, 255));
            g.drawLine(x, y,
                    ((JNode) getParent()).getX(), ((JNode) getParent()).getY());
            g.setColor(tmp);
        }
    }
}
