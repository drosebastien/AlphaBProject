package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;

public class NormalTreePanel extends TreePanel {
    private static final int RADIUSNODE = 4;

    private JTree jTreeRoot;
    private Timer timer;
    private int[] path;
    private TimerListener timerListener;
    private boolean pauseOnThisPanel;

    public NormalTreePanel() {
        super();

        pauseOnThisPanel = false;

        timerListener = new TimerListener();
        timer = new Timer(300, timerListener);

        addMouseListener(new MouseAdapter () {
            public void mouseClicked(MouseEvent evt) {
                mouseClickedEvent(evt);
            }
            public void mouseExited(MouseEvent evt) {
                mouseExitedEvent(evt);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                mouseMovedEvent(evt);
            }
        });

        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(700, 800));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        revalidate();

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        if(root != null) {
            jTreeRoot = new JTree(60, 30, 100, 11);
            jTreeRoot.initTree(root);
            try {
                jTreeRoot.paintComponent(g);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            int rightMargin = jTreeRoot.getRightMargin();
            int bottomMargin = jTreeRoot.getBottomMargin();
            setPreferredSize(new Dimension(rightMargin, bottomMargin));
            setMinimumSize(new Dimension(rightMargin, bottomMargin));
        }
    }

    private void mouseClickedEvent(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();

        if(jTreeRoot != null) {
            try {
                int[] path = jTreeRoot.getPathToCoordinate(x, y);
                if(evt.getButton() == MouseEvent.BUTTON1) { // if Button 1
                    this.clickOnNode(path);
                }
                else {                                    // else
                    this.bestNodeSelected(path);
                }
            }
            catch(NodeNotFoundException error) {
            }
        }
    }

    private void mouseExitedEvent(MouseEvent evt) {
        timer.stop();
    }

    private void mouseMovedEvent(MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        if(pauseOnThisPanel) {
            quitPreview();
            pauseOnThisPanel = false;
        }
        timerListener.setMouseEvent(evt);
        timer.restart();
    }

    private void mousePausedEvent(MouseEvent evt) {
        try {
            path = jTreeRoot.getPathToCoordinate(evt.getX(), evt.getY());
            pauseOnThisPanel = true;
            preview(path);
        }
        catch(NodeNotFoundException error) {
        }
        timer.stop();
    }

    private class TimerListener implements ActionListener {
        private MouseEvent evt;

        public TimerListener() {
            this.evt = null;
        }

        public void setMouseEvent(MouseEvent evt) {
            this.evt = evt;
        }

        public void actionPerformed(ActionEvent evt) {
            if(evt != null) {
                mousePausedEvent(this.evt);
            }
        }
    }
}
