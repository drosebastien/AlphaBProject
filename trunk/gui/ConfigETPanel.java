package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class ConfigETPanel extends JPanel {
    private JButton button;
    private GridBagConstraints gbc;
    private JSpinner minValueSpinner;
    private JSpinner maxValueSpinner;
    private JComboBox algoComboBox;
    private JComboBox fctComboBox;
    private JLabel windowLabel;
    private JLabel windowMinLabel;
    private JLabel windowMaxLabel;
    private JLabel algoSelectionLabel;
    private JLabel evalFctLabel;

    public ConfigETPanel() {
        setBackground(new Color(220, 220, 220));

        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();

        algoComboBox = new JComboBox();
        fctComboBox = new JComboBox();

        JSeparator separator = new JSeparator();
        separator.setOrientation(separator.HORIZONTAL);

        windowLabel = new JLabel("Window values");
        windowMinLabel = new JLabel("Min value");
        windowMaxLabel = new JLabel("Max value");

        algoSelectionLabel = new JLabel("Algorithm");
        evalFctLabel = new JLabel("Evalution function");

        button = new JButton("OK");
        button.setMinimumSize(new Dimension(120, 25));
        button.setPreferredSize(new Dimension(120, 25));

        minValueSpinner = new JSpinner();
        minValueSpinner.setMinimumSize(new Dimension(60, 25));
        minValueSpinner.setPreferredSize(new Dimension(60, 25));
        maxValueSpinner = new JSpinner();
        maxValueSpinner.setMinimumSize(new Dimension(60, 25));
        maxValueSpinner.setPreferredSize(new Dimension(60, 25));

        gbc.insets = new Insets(5, 5, 10, 5);
        gbc.gridheight = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(windowLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(windowMinLabel, gbc);
        gbc.gridx = 2;
        add(windowMaxLabel, gbc);

        gbc.gridx = 1;
        add(minValueSpinner, gbc);
        gbc.gridx = 3;
        add(maxValueSpinner, gbc);

        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(separator, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(algoSelectionLabel, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(algoComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        add(evalFctLabel, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(fctComboBox, gbc);
    }
}
