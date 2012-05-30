package gui;

import explorer.MinMaxAlgoFactory;
import explorer.AbstractGameEvalFctFactory;

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

/**
 * Cette classe permet de creer un panel de configuration pour regler different
 * parametre de l'outil d'exploration.
 * @author Sebastien Drobisz.
 */
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
    private JTextArea textArea;
    private JScrollPane scroll;

    /**
     * Cette methode permet de dessiner le panel de configuration
     * @param parentFrame La frame parent a ce panel.
     */
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

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scroll = new JScrollPane(textArea);
        scroll.setViewportView(textArea);
        scroll.setMinimumSize(new Dimension(50, 150));
        scroll.setPreferredSize(new Dimension(50, 150));

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
        gbc.gridwidth = GridBagConstraints.RELATIVE;
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
        gbc.gridwidth = GridBagConstraints.RELATIVE;
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

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(scroll, gbc);

        addAlgo();
    }

    /**
     * Cette methode permet de fournir un factory de fonctions d'evaluation
     * pour lui permettre de connaitre toutes les fonctions d'evaluation
     * qu'il est possible d'instancier.
     * @param factory Le factory des fonctions d'evaluation utilisables.
     */
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
        String choicedAlgo = fctComboBox.getSelectedItem().toString();
        textArea.setText(gameEvalFctFactory.getDescription(choicedAlgo));
        textArea.setCaretPosition(0);
        listener.fctHaveChanged(choicedAlgo);
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
