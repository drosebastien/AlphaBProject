package gui;

import explorer.*;

import javax.swing.JPanel;

import java.awt.Graphics;

public abstract class AlgoPanel extends JPanel implements MinMaxListener {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event) {
    }

    public void setValueOfNode(String value, MinMaxEvent event) {
    }

    public void refreshTree(MinMaxEvent event) {
    }

    public void setNewBestNode(int indexOfChild, MinMaxEvent event) {
    }

    public void setDropedNode(int indexOfChild, MinMaxEvent event) {
    }
}
