package gui;

import explorer.GameFactory;
import framework.Processor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;

import javax.swing.event.ChangeListener;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartPanel extends JPanel {
    private GridBagConstraints gbc;
    private JButton okButton;
    private JComboBox gameComboBox;
    private JComboBox modeComboBox;

    public StartPanel() {
        setBackground(new Color(220, 220, 220));

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        initGameComboBox();
        initModeComboBox();

        okButton = new JButton("ok");
        okButton.setMinimumSize(new Dimension(60, 25));
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonClicked(evt);
            }
        });

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Game"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(gameComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Mode"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(modeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        add(okButton, gbc);

        
    }

    public void initGameComboBox() {
        String[] games = GameFactory.getInstance().getBuildableGameName();
        gameComboBox = new JComboBox();
        gameComboBox.setMinimumSize(new Dimension(150, 25));
        gameComboBox.setPreferredSize(new Dimension(150, 25));
        for(int i = 0; i < games.length; i++) {
            gameComboBox.addItem(games[i]);
        }
    }

    public void initModeComboBox() {
        modeComboBox = new JComboBox();
        modeComboBox.setMinimumSize(new Dimension(150, 25));
        modeComboBox.setPreferredSize(new Dimension(150, 25));
        modeComboBox.addItem("Pedagogical");
    }

    public void okButtonClicked(ActionEvent evt) {
        Processor processor = new Processor();
        String selectedGame = gameComboBox.getSelectedItem().toString();
        processor.launchPedMode(selectedGame);
    }
}
