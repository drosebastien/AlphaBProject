package gui;

import explorer.*;

import javax.swing.JPanel;

import java.awt.Graphics;

/**
 * Cette classe permet de creer un panel qui permet de donner une
 * meilleure description de ce que fait l'algorithme.
 * @author Sebastien Drobisz.
 */
public abstract class AlgoPanel extends JPanel implements MinMaxListener {
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
