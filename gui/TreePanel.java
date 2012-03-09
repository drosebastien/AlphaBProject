package gui;

import explorer.Controller;

import java.awt.Color;

import javax.swing.JPanel;

public class TreePanel extends JPanel {
    private boolean inExplorerMode;
    protected Controller controller;

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
        controller.printMessage("TreePanel, mode : " + mode, mode);
    }

    public boolean isInExplorerMode() {
        return inExplorerMode;
    }
}
