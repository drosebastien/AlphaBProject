package gui;

import tree.*;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Cette classe permet de creer un panel pour l'arbre d'exploration
 * @author Sebastien Drobisz
 */
public abstract class TreePanel extends JPanel {
    protected boolean inExplorerMode;
    protected TreeNode root;
    protected ArrayList<TreePanelListener> listeners;

    /**
     * Constructeur pour creer le panel pour l'arbre d'exploration
     */
    public TreePanel() {
        super();

        listeners = new ArrayList<TreePanelListener>();

        int grayLvl = 150;
        setBackground(new Color(grayLvl, grayLvl, grayLvl + 10));
    }

    /**
     * Cette methode permet d'ajouter un listener au panel de l'arbre.
     * @param listener Le listener a ajouter.
     */
    public void addListener(TreePanelListener listener) {
        listeners.add(listener);
    }

    /**
     * Cette methode permet de prevenir les listeners qu'un click a ete realise
     * sur un noeud.
     * @param path Le chemin menant de l'etat initial au noeud clicke.
     */
    protected void clickOnNode(int[] path) {
        for(TreePanelListener listener : listeners) {
            listener.clickOnNode(isInExplorerMode(), path);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners qu'un noeud a ete demande
     * pour etre evalue en premier.
     * @param path Le chemin menant de l'etat initial au noeud clicke.
     */
    protected void bestNodeSelected(int[] path) {
        for(TreePanelListener listener : listeners) {
            listener.bestNodeSelected(isInExplorerMode(), path);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners que l'apercu d'un etat
     * a ete demande.
     * @param path Le chemin menant de l'etat initial au noeud clicke.
     */
    protected void preview(int[] path) {
        for(TreePanelListener listener : listeners) {
            listener.preview(path, isInExplorerMode());
        }
    }

    /**
     * Cette methode permet de prevenir les listeners que l'apercu est annule.
     */
    protected void quitPreview() {
        for(TreePanelListener listener : listeners) {
            listener.quitPreview();
        }
    }

    /**
     * Cette methode permet de prevenir les listeners que l'apercu est annule.
     */
    protected void treeIsRepaint() {
        for(TreePanelListener listener : listeners) {
            listener.quitPreview();
        }
    }

    /**
     * Cette methode permet d'activer le mode apercu.
     * @param inPreviewMode active si true, desactive sinon.
     */
    public void previewMode(boolean inPreviewMode) {
        BufferedImage cursorImg = new BufferedImage(16, 16,
        BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        if(inPreviewMode) {
            setCursor(blankCursor);
        }
        else {
            setCursor(null);
        }
    }

    /**
     * Permet de dire si l'outil est dans le mode explorer ou pas.
     * @param mode Dans le mode explorer si true, sinon dans le mode execution
     * de l'algorithme.
     */
    public void setInExplorerMode(boolean mode) {
        this.inExplorerMode = mode;
    }

    /**
     * Cette methode permet de donner la structure de l'arbre a afficher sur
     * le panel.
     * @param root La racine de l'arbre.
     */
    public void setTreeRootNode(TreeNode root) {
        this.root = root;
    }

    /**
     * Cette methode permet de retourner le mode de l'outil.
     * @return true si dans le mode explorer, false sinon.
     */
    protected boolean isInExplorerMode() {
        return inExplorerMode;
    }
}
