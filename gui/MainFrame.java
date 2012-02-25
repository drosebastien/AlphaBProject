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

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {
    private static final int HEIGHT = 700;
    private static final int WIDTH = 1300;

    private GridBagConstraints gbc;
    private GamePanel gamePanel;
    private TreePanel treePanel;


    public MainFrame(GamePanel gPanel) {
        super("Exploration algorithm");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();

        JSeparator separatorH = new JSeparator();
        JSeparator separatorV = new JSeparator(SwingConstants.VERTICAL);
        treePanel = new TreePanel();
        this.gamePanel = gPanel;
        JButton button = new JButton("OK");
        button.addActionListener(new OKListener());
        button.setMinimumSize(new Dimension(120, 25));
        button.setPreferredSize(new Dimension(120, 25));

        gbc.insets = new Insets(5, 5, 5, 25);
        gbc.gridheight = 1;
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
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(button, gbc);

        gbc.weightx = gbc.weighty = 0.;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(separatorV, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(treePanel, gbc);

        setLocationRelativeTo(null);
        setVisible(true);
        //repaint();
    }

    public class OKListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Voilà ce qui se passe quand on appuie sur ok");
        }
    }
}
