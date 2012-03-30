package gui;

import tree.*;

import java.util.ArrayList;

import java.awt.Color;

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
