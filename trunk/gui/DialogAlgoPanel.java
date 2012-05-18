package gui;

import explorer.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class DialogAlgoPanel extends AlgoPanel {

    private GridBagConstraints gbc;
    private JTextArea dialogTextArea;

    public DialogAlgoPanel() {
        initComponent();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void initComponent() {
        gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        dialogTextArea = new JTextArea();
        dialogTextArea.setEditable(false);
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(dialogTextArea);
        scroll.setViewportView(dialogTextArea);
        scroll.setMinimumSize(new Dimension(200, 200));
        scroll.setPreferredSize(new Dimension(200, 200));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = gbc.weighty = 1.;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetPerformed(evt);
            }
        });
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.weightx = gbc.weighty = 0.;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resetButton, gbc);
    }

    private void resetPerformed(ActionEvent evt) {
        dialogTextArea.setText("");
    }

    //méthode de l'interface MinMaxListener

    public void started() {
    }

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent evt) {
        if(evt.getMessage() != null) {
            if(move == Movement.FORWARD) {
                dialogTextArea.insert("--------------\n", 0);
                dialogTextArea.insert(evt.getMessage() + "\n", 0);
            }
            else if(move == Movement.BACKWARD) {
                dialogTextArea.insert("--------------\n", 0);
                dialogTextArea.insert(evt.getMessage() + "\n", 0);
            }
        }
    }

    public void setValueOfNode(String value, MinMaxEvent evt) {
        if(evt.getMessage() != null) {
            dialogTextArea.insert("--------------\n", 0);
            dialogTextArea.insert(evt.getMessage() + "\n", 0);
        }
    }

    public void refreshTree(MinMaxEvent evt) {
    }

    public void setNewBestNode(int indexOfChild, MinMaxEvent evt) {
        if(evt.getMessage() != null) {
            dialogTextArea.insert("--------------\n", 0);
            dialogTextArea.insert(evt.getMessage() + "\n", 0);
        }
    }

    public void setDropedNode(int indexOfChild, MinMaxEvent evt) {
        if(evt.getMessage() != null) {
            dialogTextArea.insert("--------------\n", 0);
            dialogTextArea.insert(evt.getMessage() + "\n", 0);
        }
    }

    public void finished(MinMaxEvent evt) {
        if(evt.getMessage() != null) {
            dialogTextArea.insert("--------------\n", 0);
            dialogTextArea.insert("Evaluation end : " +
                                  evt.getMessage() + "\n", 0);
        }
    }
}
