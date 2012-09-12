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

/**
 * Cette classe permet de creer le panel pour un jeu.
 * @author Sebastien Drobisz.
 */
public abstract class GamePanel extends JPanel {
    private static final int GRAYLVL = 180;
    protected ArrayList<GamePanelListener> listeners;
    protected Board board;
    private boolean inPreviewMode;
    private boolean inExplorerMode;

    /**
     * Ce constructeur permet de creer le panel d'un jeu en lui fournissant
     * le board du jeu.
     * @param board Le board du jeu.
     */
    public GamePanel(Board board) {
        this.board = board;

        listeners = new ArrayList<GamePanelListener>();
        inPreviewMode = false;

        setMinimumSize(new Dimension(200, 200));
        setPreferredSize(new Dimension(200, 200));

        setBackground(new Color(GRAYLVL, GRAYLVL, GRAYLVL));
    }

    /**
     * Cette methode permet de dessiner le board du jeu.
     * @param g le graphics sur lequel dessiner le board.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int grayLvl = GRAYLVL;
        if(inPreviewMode) {
            grayLvl = 255;
        }
        setBackground(new Color(grayLvl, grayLvl, grayLvl));
    }

    /**
     * Cette methode permet dire au panel de jeu dans quel mode 
     * l'outil se trouve.
     * @param mode Dans le mode explorer (changement d'etat) si vrai, sinon
     * dans le mode exploration de l'algorithme.
     */
    public void setInExplorerMode(boolean mode) {
        this.inExplorerMode = mode;
    }

    /**
     * Permet de savoir dans dire donner le mode de l'outil.
     * @return le mode dans lequel se trouve l'outil.
     */
    public boolean isInExplorerMode() {
        return inExplorerMode;
    }

    /**
     * Cette methode permet d'activer le mode d'affichage pour l'apercu ou
     * de le desactiver.
     * @param inPreviewMode active si true, desactive sinon.
     */
    public void previewMode(boolean inPreviewMode) {
        this.inPreviewMode = inPreviewMode;
    }

    /**
     * Cette methode permet d'ajouter un listener au panel de jeu pour avertir
     * si un coup a ete joue.
     * @param listener Le listener a ajouter.
     */
    public void addListener(GamePanelListener listener) {
        listeners.add(listener);
    }

    /**
     * Permet de prevenir les listeners qu'un coup a ete realise.
     * @param move Le coup qui a ete realise.
     */
    public void hitFired(Move move) {
        for(GamePanelListener listener : listeners) {
                listener.hitFired(move, isInExplorerMode());
        }
    }
}
