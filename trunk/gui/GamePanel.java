package gui;

import framework.Board;
import explorer.Controller;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

import java.util.ArrayList;

public class GamePanel extends JPanel {
    protected ArrayList<GamePanelListener> listeners;
    protected Board board;
    private boolean inExplorerMode;
    protected Controller controller;

    public GamePanel(Board board) {
        this.board = board;

        listeners = new ArrayList<GamePanelListener>();

        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));

        int grayLvl = 180;
        setBackground(new Color(grayLvl, grayLvl, grayLvl));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setInExplorerMode(boolean mode) {
        this.inExplorerMode = mode;
        controller.printMessage("GamePanel, mode : " + mode, mode);
    }

    public boolean isInExplorerMode() {
        return inExplorerMode;
    }

    public void addListener(GamePanelListener listener) {
        listeners.add(listener);
    }
}
