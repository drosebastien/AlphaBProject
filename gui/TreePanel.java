package gui;

import tree.*;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public abstract class TreePanel extends JPanel {
    private boolean inExplorerMode;
    protected TreeNode root;
    protected ArrayList<TreePanelListener> listeners;

    public TreePanel() {
        super();

        listeners = new ArrayList<TreePanelListener>();

        int grayLvl = 150;
        setBackground(new Color(grayLvl, grayLvl, grayLvl + 10));
    }

    public void addListener(TreePanelListener listener) {
        listeners.add(listener);
    }

    protected void clickOnNode(int[] path) {
        for(TreePanelListener listener : listeners) {
            listener.clickOnNode(isInExplorerMode(), path);
        }
    }

    protected void bestNodeSelected(int[] path) {
        for(TreePanelListener listener : listeners) {
            listener.bestNodeSelected(isInExplorerMode(), path);
        }
    }

    protected void preview(int[] path) {
        for(TreePanelListener listener : listeners) {
            listener.preview(path, isInExplorerMode());
        }
    }

    protected void quitPreview() {
        for(TreePanelListener listener : listeners) {
            listener.quitPreview();
        }
    }

    protected void treeIsRepaint() {
        for(TreePanelListener listener : listeners) {
            listener.quitPreview();
        }
    }

    public void previewMode(boolean inPreviewMode) {
        System.out.println("NormalTreePanel");
        BufferedImage cursorImg = new BufferedImage(16, 16,
        BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        if(inPreviewMode) {
            setCursor(blankCursor);
        }
        else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public void setInExplorerMode(boolean mode) {
        this.inExplorerMode = mode;
    }

    public void setTreeRootNode(TreeNode root) {
        this.root = root;
    }

    protected boolean isInExplorerMode() {
        return inExplorerMode;
    }
}
