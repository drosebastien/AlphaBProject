package gui;

import framework.*;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

import java.util.ArrayList;

public abstract class GamePanel extends JPanel {
    private static final int GRAYLVL = 180;
    protected ArrayList<GamePanelListener> listeners;
    protected Board board;
    private boolean inPreviewMode;
    private boolean inExplorerMode;

    public GamePanel(Board board) {
        this.board = board;

        listeners = new ArrayList<GamePanelListener>();
        inPreviewMode = false;

        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));

        setBackground(new Color(GRAYLVL, GRAYLVL, GRAYLVL));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int grayLvl = GRAYLVL;
        if(inPreviewMode) {
            grayLvl = 255;
        }
        setBackground(new Color(grayLvl, grayLvl, grayLvl));
    }

    public void setInExplorerMode(boolean mode) {
        this.inExplorerMode = mode;
    }

    public boolean isInExplorerMode() {
        return inExplorerMode;
    }

    public void previewMode(boolean inPreviewMode) {
        this.inPreviewMode = inPreviewMode;
    }

    public void addListener(GamePanelListener listener) {
        listeners.add(listener);
    }

    public void hitFired(Move move) {
        for(GamePanelListener listener : listeners) {
                listener.hitFired(move, isInExplorerMode());
        }
    }
}
