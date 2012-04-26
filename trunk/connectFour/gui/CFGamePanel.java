package connectFour.gui;

import framework.*;
import connectFour.*;

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

public class CFGamePanel extends GamePanel {
    private static final int LEFT_BOARDER = 16;
    private static final int TOP_BOARDER = 28;
    private static final int SQUARE_SIZE = 24;
    private int height = 6;
    private int width = 7;

    public CFGamePanel(Board board) {
        super(board);

        addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent e) {
                mouseClickedEvent(e);
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image empty = null;
        Image red = null;
        Image yellow = null;
        try {
            empty = ImageIO.read(new File("img/connectFour/empty.png"));
            red = ImageIO.read(new File("img/connectFour/red.png"));
            yellow = ImageIO.read(new File("img/connectFour/yellow.png"));
        } catch(IOException e) {
            System.out.println("problème avec les images");
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Image img = null;

                Piece pieceToPlace = board.getPiece(new CFPosition(i, j));
                if(board == null || pieceToPlace == null) {
                    img = empty;
                }
                else if(pieceToPlace.getId() == 0) {
                    img = yellow;
                }
                else {
                    img = red;
                }

                g.drawImage(img,
                            LEFT_BOARDER + j*SQUARE_SIZE,
                            TOP_BOARDER + i*SQUARE_SIZE,
                            SQUARE_SIZE, SQUARE_SIZE,
                            this);
            }
        }
    }

    public void mouseClickedEvent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if(x >= LEFT_BOARDER && x <= LEFT_BOARDER + width * SQUARE_SIZE &&
           y >= TOP_BOARDER && y <= TOP_BOARDER + height * SQUARE_SIZE) {

            x = (x - LEFT_BOARDER) / SQUARE_SIZE;
            y = height - 1 - (y - TOP_BOARDER) / SQUARE_SIZE;

            CFPosition pos = new CFPosition(x, y);
            CFPiece piece = new CFPiece("UNKNOW", -1);
            Move move = new CFMove(pos, piece);

            System.out.printf("(x, y) = (%d; %d)\n", x, y);
            this.hitFired(move);
        }
    }
}
