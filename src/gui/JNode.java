package gui;

import tree.*;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Cette classe represente le nœud normal d'un arbre graphique.
 * @author Sebastien Drobisz
 */
public class JNode extends TreeNode{
    protected int x;
    protected int y;
    private boolean isLeaf;

    /**
     * Le constructeur d'un JNode permet de definir le pere du noeud cree.
     * null si pas de pere.
     * @param parent Le noeud pere du noeud a creer.
     */
    public JNode(JNode parent) {
        super(parent);
    }

    /**
     * Cette methode permet de copier le contenu d'un TreeNode, i.e., son type
     * et son label dans le noeud graphique.
     * @param node Le TreeNode dont les donnees doivent etre copiee.
     */
    public void copyState(TreeNode node) {
        setType(node.getType());
        setLabel(node.getLabel());
        isLeaf = node instanceof LeafNode;
        setMinMaxType(node.getMinMaxType());
    }

    /**
     * Cette methode permet de donner la position en abscisse du noeud.
     * @param x La position en abscisse du noeud.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Cette methode permet de donner la position en ordonnee du noeud.
     * @param y La position en ordonnee du noeud.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Cette methode permet de retourner la position en abscisse du noeud.
     * @return L'abscisse du noeud.
     */
    public int getX() {
        return x;
    }

    /**
     * Cette methode permet de retourner la position en ordonnee du noeud.
     * @return L'ordonnee du noeud.
     */
    public int getY() {
        return y;
    }

    /**
     * Cette methode permet de savoir si le noeud est une feuille.
     * @return true si le noeud est une feuille, false sinon.
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Cette methode permet de dessiner le noeud sur un Graphics.
     * @param g Le Graphics où dessiner le noeud.
     */
    public void paintNode(Graphics g) {
        int size = 4;
        Color[] colors = new Color[]{new Color(255, 255, 255, 70),
                                     new Color(0, 0, 0, 70)};

        Color tmp = g.getColor();
        int grayLvl = 150;

        g.setColor(new Color(grayLvl, grayLvl, grayLvl + 10));
        drawTriangle(colors, size, g);
        g.setColor(tmp);
    }

    /**
     * Cette methode permet de dessiner des triangles pour les noeuds.
     * @param colors La couleurs des noeuds. La première couleurs est pour
     * le fond, la seconde pour les bords.
     * @param size Permet de changer la taille des triangles.
     * @param g Le graphics sur lequel dessiner
     */
    protected void drawTriangle(Color[] colors, int size, Graphics g) {
        switch(getMinMaxType()) {
            case MIN:
                g.fillPolygon(new int[]{x - size, x + size, x},
                              new int[]{y - size, y - size, y + size},
                              3);
                g.setColor(colors[0]);
                g.fillPolygon(new int[]{x - size, x + size, x},
                              new int[]{y - size, y - size, y + size},
                              3);
                g.setColor(colors[1]);
                g.drawPolygon(new int[]{x - size, x + size, x},
                              new int[]{y - size, y - size, y + size},
                              3);
                break;
            case MAX:
                g.fillPolygon(new int[]{x - size, x + size, x},
                              new int[]{y + size, y + size, y - size},
                              3);
                g.setColor(colors[0]);
                g.fillPolygon(new int[]{x - size, x + size, x},
                              new int[]{y + size, y + size, y - size},
                              3);
                g.setColor(colors[1]);
                g.drawPolygon(new int[]{x - size, x + size, x},
                              new int[]{y + size, y + size, y - size},
                              3);
                break;
        }
    }

    /**
     * Cette methode permet de dessiner l'arete du noeud vers son pere (s'il
     * existe) sur un Graphics.
     * @param g Le graphics où dessiner l'arete.
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
     * Cette methode permet de dessiner le label associe a un noeud sur un
     * graphics.
     * @param g Le Graphics où dessiner le label.
     */
    public void paintLabel(Graphics g) {
    }
}
