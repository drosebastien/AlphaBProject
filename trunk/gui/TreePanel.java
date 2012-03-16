package gui;

import explorer.Controller;
import tree.*;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class TreePanel extends JPanel {
    private boolean inExplorerMode;
    protected Controller controller;
    protected TreeNode root;

    public TreePanel() {
        super();

        int grayLvl = 150;
        setBackground(new Color(grayLvl, grayLvl, grayLvl + 10));
    }

    public void setController(Controller controller) {
        this.controller = controller;
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

    public abstract void nextEvent();

    public abstract void previousEvent();
}
