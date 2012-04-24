package gui;

import explorer.MinMaxAlgoFactory;
import explorer.AbstractGameEvalFctFactory;

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

import javax.swing.event.ChangeListener;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfigETPanel extends JPanel {
    private AbstractGameEvalFctFactory gameEvalFctFactory;
    private ConfigETWindow listener;
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

    public ConfigETPanel(ConfigETWindow parentFrame) {
        listener = parentFrame;
        setBackground(new Color(220, 220, 220));

        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();

        algoComboBox = new JComboBox();
        algoComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    algoComboBoxActionPerformed(evt);
                }
            });
        fctComboBox = new JComboBox();
        fctComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    evalFctComboBoxActionPerformed(evt);
                }
            });

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
        minValueSpinner.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    minMaxWindowValuesSpinnerStateChanged(evt);
                }
            });

        maxValueSpinner = new JSpinner();
        maxValueSpinner.setMinimumSize(new Dimension(60, 25));
        maxValueSpinner.setPreferredSize(new Dimension(60, 25));
        maxValueSpinner.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent evt) {
                    minMaxWindowValuesSpinnerStateChanged(evt);
                }
            });

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

        addAlgo();
    }

    public void setEvalFctFactory(AbstractGameEvalFctFactory factory) {
        this.gameEvalFctFactory = factory;
        addEvalFct(factory.getBuildableEvalFct());
    }

    private void addAlgo() {
        String[] algoNames =
                   MinMaxAlgoFactory.getInstance().getBuildableMinMaxAlgoName();
        for(int i = 0; i < algoNames.length; i++) {
            String[] splitAlgoNames = algoNames[i].split("\\.");
            algoComboBox.addItem(splitAlgoNames[splitAlgoNames.length - 1]);
        }
    }

    private void addEvalFct(String[] evalFcts) {
        for(int i = 0; i < evalFcts.length; i++) {
            fctComboBox.addItem(evalFcts[i]);
        }
    }

    private void algoComboBoxActionPerformed(ActionEvent evt) {
        listener.algoHaveChanged(algoComboBox.getSelectedItem().toString());
    }

    private void evalFctComboBoxActionPerformed(ActionEvent evt) {
        listener.fctHaveChanged(fctComboBox.getSelectedItem().toString());
    }

    private void minMaxWindowValuesSpinnerStateChanged(ChangeEvent evt) {
        int minValue = getIntValueOfSpinner(minValueSpinner);
        int maxValue = getIntValueOfSpinner(maxValueSpinner);

        listener.minMaxWindowValuesSpinnerStateChanged(minValue, maxValue);
    }

    private int getIntValueOfSpinner(JSpinner spinner) {
        SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
        return model.getNumber().intValue();
    }
}
