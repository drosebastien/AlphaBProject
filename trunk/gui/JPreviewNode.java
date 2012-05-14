package gui;

import java.awt.Color;
import java.awt.Graphics;

public class JPreviewNode extends JPreviewAncestorNode {

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
