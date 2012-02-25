package gui;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class TreePanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int grayLvl = 120;
        setBackground(new Color(grayLvl, grayLvl, grayLvl));
    }
}
