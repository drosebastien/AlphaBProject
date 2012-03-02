package gui;

import java.awt.Color;

import javax.swing.JPanel;

public class TreePanel extends JPanel {
    public TreePanel() {
        super();

        int grayLvl = 150;
        setBackground(new Color(grayLvl, grayLvl, grayLvl + 10));
    }
}
