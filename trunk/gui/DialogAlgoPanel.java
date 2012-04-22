package gui;

import explorer.*;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;

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
        dialogTextArea = new JTextArea();
        dialogTextArea.setLineWrap(true);
        dialogTextArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(dialogTextArea);
        scroll.setViewportView(dialogTextArea);
        scroll.setMinimumSize(new Dimension(200, 200));
        scroll.setPreferredSize(new Dimension(200, 200));
        add(scroll);
    }

    public void moved(Movement move, int indexInTreeGame, MinMaxEvent event) {
        System.out.println(move);
    }
}
