package gui;

import morpion.gui.*;
import framework.*;
import explorer.*;

import java.util.ArrayList;

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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 1024;

    private ArrayList<MinMaxEducativeToolsListener> listeners;

    private ConfigETWindow configETWindow;

    private GridBagConstraints gbc;
    private Game game;                                                          // à retirer
    private EvalFunction fct;                                                   // à retirer
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private boolean inExplorerMode;

    private JButton button;
    private JSpinner treeDepthSpinner;
    private JButton nextButton;
    private JButton previousButton;
    private JCheckBox checkBox;
    private JTextArea dialogTextArea;

    public MainFrame(Game game, EvalFunction fct,
                     GamePanel gPanel, TreePanel treePanel) {                   //retirer game et evalFunction.
        super("Exploration algorithm");

        this.game = game;                                                       // à retirer
        this.fct = fct;                                                         // à retirer

        this.treePanel = treePanel;
        this.gamePanel = gPanel;

        listeners = new ArrayList<MinMaxEducativeToolsListener>();
        inExplorerMode = false;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());
        initComponent();
        initMenu();

        setLocationRelativeTo(null);
        setVisible(true);
        //repaint();
    }

    public void algoHaveChanged(String algoName) {
        int depth = getIntValueOfSpinner(treeDepthSpinner);
        for(int i = 0; i < listeners.size(); i++) {
            MinMaxAlgo algo = MinMaxAlgoFactory.getInstance().getMinMaxAlgo(
                              algoName, game, depth, fct);
            listeners.get(i).algoHaveChanged(algo);
        }
    }

    public void windowValuesHaveChanged(int minValue, int maxValue) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).windowValuesHaveChanged(minValue, maxValue);
        }
    }

    public void initMenu() {
        configETWindow = new ConfigETWindow(this);
        JMenuBar menuBar = new JMenuBar();

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem optionsItem = new JMenuItem("Options");
        optionsItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                optionsItemEvent(event);
            }
        });

        toolsMenu.add(optionsItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        JMenuItem helpItem = new JMenuItem("Help");
        helpMenu.add(helpItem);

        menuBar.add(toolsMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
    }

    private void optionsItemEvent(ActionEvent event) {
        configETWindow.setVisible(true);
    }

    public void initComponent() {
        gbc = new GridBagConstraints();

        JLabel explorerLabel = new JLabel("Explorer mode");
        JLabel treeDepthLabel = new JLabel("Tree depth");

        checkBox = new JCheckBox();
        checkBox.addActionListener(new ExplorerListener());

        dialogTextArea = new JTextArea();

//        button = new JButton("OK");
//        button.addActionListener(new OKListener());
//        button.setMinimumSize(new Dimension(120, 25));
//        button.setPreferredSize(new Dimension(120, 25));

        Integer value = new Integer(2);
        Integer min = new Integer(2);
        Integer max = new Integer(6);
        Integer step = new Integer(1);
        treeDepthSpinner = new JSpinner();
        treeDepthSpinner.setMinimumSize(new Dimension(40, 25));
        treeDepthSpinner.setPreferredSize(new Dimension(40, 25));
        treeDepthSpinner.addChangeListener(new TreeDepthSpinnerListener());
        SpinnerNumberModel treeDepthSpinnerModel = new SpinnerNumberModel(
                                                         value, min, max, step);
        treeDepthSpinner.setModel(treeDepthSpinnerModel);

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

        gbc.insets = new Insets(0, 5, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JSeparator(), gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        add(explorerLabel, gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(checkBox, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(treeDepthLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(treeDepthSpinner, gbc);

        gbc.insets = new Insets(10, 5, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JSeparator(), gbc);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(dialogTextArea), gbc);

        gbc.weightx = gbc.weighty = 0.;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);
        add(new JSeparator(SwingConstants.VERTICAL), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = gbc.weighty = 1.;
        gbc.gridheight = GridBagConstraints.RELATIVE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JScrollPane(treePanel), gbc);

        gbc.gridy = 10;
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
    }

    public class ExplorerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(checkBox.isSelected()) {
                gamePanel.setInExplorerMode(true);
                treePanel.setInExplorerMode(true);
                inExplorerMode = true;
            }
            else {
                gamePanel.setInExplorerMode(false);
                treePanel.setInExplorerMode(false);
                inExplorerMode = false;
            }
        }
    }

    public void addListener(MinMaxEducativeToolsListener listener) {
        listeners.add(listener);
    }

    private class TreeDepthSpinnerListener implements ChangeListener {

        public void stateChanged(ChangeEvent evt) {
            int value = getIntValueOfSpinner(treeDepthSpinner);

            for(MinMaxEducativeToolsListener listener : listeners) {
                listener.treeDepthChanged(value);
            }
        }
    }

    private int getIntValueOfSpinner(JSpinner spinner) {
        SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
        return model.getNumber().intValue();
    }

    public class NextListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for(MinMaxEducativeToolsListener listener : listeners) {
                listener.progress(inExplorerMode);
            }
        }
    }

    public class PreviousListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for(MinMaxEducativeToolsListener listener : listeners) {
                listener.removeLast(inExplorerMode);
            }
        }
    }
}
