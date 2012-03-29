package morpion.gui;

import framework.Board;
import framework.Piece;
import morpion.MorpionPosition;

import gui.GamePanelListener;
import gui.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

public class MorpionGamePanel extends GamePanel {
    private static final int LEFT_BOARDER = 25;
    private static final int TOP_BOARDER = 25;
    private static final int SQUARE_SIZE = 50;
    private int length = 3;

    public MorpionGamePanel(Board board) {
        super(board);
        addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent e) {
                mouseClickedEvent(e);
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image piece = null;
        Image cross = null;
        Image circle = null;
        try {
            piece = ImageIO.read(new File("img/morpion/piece.png"));
            cross = ImageIO.read(new File("img/morpion/cross.png"));
            circle = ImageIO.read(new File("img/morpion/circle.png"));
        } catch(IOException e) {
            System.out.println("problème avec les images");
        }

        /**for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                g.drawImage(piece,
                            LEFT_BOARDER + i*SQUARE_SIZE,
                            TOP_BOARDER + j*SQUARE_SIZE,
                            SQUARE_SIZE, SQUARE_SIZE,
                            this);
            }
        }*/
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                Image img = null;

                Piece pieceToPlace = board.getPiece(new MorpionPosition(2 - j, i));
                if(board == null || pieceToPlace == null) {
                    img = piece;
                }
                else if(pieceToPlace.getId() == 0) {
                    img = cross;
                }
                else {
                    img = circle;
                }
                g.drawImage(img,
                            LEFT_BOARDER + i*SQUARE_SIZE,
                            TOP_BOARDER + j*SQUARE_SIZE,
                            SQUARE_SIZE, SQUARE_SIZE,
                            this);
            }
        }
    }

    public void mouseClickedEvent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if(x >= LEFT_BOARDER && x <= LEFT_BOARDER + length * SQUARE_SIZE &&
           y >= TOP_BOARDER && y <= TOP_BOARDER + length * SQUARE_SIZE) {

            x = (x - LEFT_BOARDER) / SQUARE_SIZE;
            y = (y - TOP_BOARDER) / SQUARE_SIZE;

            for(GamePanelListener listener : listeners) {
                listener.hitFired(x, 2 - y);
            }
        }
    }
}
