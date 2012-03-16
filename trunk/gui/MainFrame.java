package gui;

import morpion.gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 1024;

    private GridBagConstraints gbc;
    private GamePanel gamePanel;
    private TreePanel treePanel;

    private JButton button;
    private JButton nextButton;
    private JButton previousButton;
    private JRadioButton radioButton;

    public MainFrame(GamePanel gPanel, TreePanel treePanel) {
        super("Exploration algorithm");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();

        JSeparator separatorH = new JSeparator();
        JSeparator separatorV = new JSeparator(SwingConstants.VERTICAL);
        this.treePanel = treePanel;
        this.gamePanel = gPanel;
        JLabel explorerLabel = new JLabel("Explorer");

        radioButton = new JRadioButton();
        radioButton.addActionListener(new ExplorerListener());

        button = new JButton("OK");
        button.addActionListener(new OKListener());
        button.setMinimumSize(new Dimension(120, 25));
        button.setPreferredSize(new Dimension(120, 25));

        nextButton = new JButton(">");
        nextButton.addActionListener(new NextListener());

        previousButton = new JButton("<");
        previousButton.addActionListener(new PreviousListener());

        gbc.insets = new Insets(5, 5, 5, 25);
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        add(gamePanel, gbc);

        gbc.insets = new Insets(0, 5, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(separatorH, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.anchor = GridBagConstraints.BELOW_BASELINE_TRAILING;
        add(explorerLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.BASELINE;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(radioButton, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(button, gbc);

        gbc.weightx = gbc.weighty = 0.;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(separatorV, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.;
        gbc.gridheight = GridBagConstraints.RELATIVE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(treePanel), gbc);

        gbc.gridy = 4;
        gbc.weightx = gbc.weighty = 0.;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(previousButton, gbc);

        gbc.gridx = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(nextButton, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
        //repaint();
    }

    public class ExplorerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(radioButton.isSelected()) {
                gamePanel.setInExplorerMode(true);
                treePanel.setInExplorerMode(true);
            }
            else {
                gamePanel.setInExplorerMode(false);
                treePanel.setInExplorerMode(false);
            }
        }
    }

    public class OKListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Voilà ce qui se passe quand on appuie sur ok");
            treePanel.repaint();
        }
    }

    public class NextListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            treePanel.nextEvent();
        }
    }

    public class PreviousListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            treePanel.previousEvent();
        }
    }
}
