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

public class MainFrame extends JFrame implements TreePanelListener {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 1024;

    private ArrayList<MinMaxEducativeToolsListener> listeners;

    private boolean play;

    private ConfigETWindow configETWindow;

    private GridBagConstraints gbc;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private JScrollPane treePanelScrollPane;
    private boolean inExplorerMode;

    private JSpinner treeDepthSpinner;
    private JButton playButton;
    private JButton stopButton;
    private JButton nextButton;
    private JButton previousButton;
    private JCheckBox checkBox;
    private JTextArea dialogTextArea;

    public MainFrame(GamePanel gPanel, TreePanel treePanel) {
        super("Exploration algorithm");

        play = false;

        this.treePanel = treePanel;
        treePanel.addListener(this);
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

        treePanelScrollPane = new JScrollPane(treePanel);

        checkBox = new JCheckBox();
        checkBox.addActionListener(new ExplorerListener());

        dialogTextArea = new JTextArea();

        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playPausePerformed(evt);
            }
        });
        playButton.setMinimumSize(new Dimension(80, 25));
        playButton.setPreferredSize(new Dimension(80, 25));

        stopButton = new JButton("stop");
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stopPerformed(evt);
            }
        });
        stopButton.setMinimumSize(new Dimension(80, 25));
        stopButton.setPreferredSize(new Dimension(80, 25));

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
        add(treePanelScrollPane, gbc);

        gbc.gridy = 10;
        gbc.weighty = 0.;
        gbc.weightx = 1.;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 5, 5, 0);
        add(previousButton, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0.;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(playButton, gbc);

        gbc.gridx = 5;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(stopButton, gbc);

        gbc.gridx = 6;
        gbc.weightx = 1.;
        gbc.insets = new Insets(5, 5, 5, 0);
        gbc.anchor = GridBagConstraints.LINE_START;
        add(nextButton, gbc);
    }

    public void windowValuesHaveChanged(int minValue, int maxValue) {
        makePause();
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).windowValuesHaveChanged(minValue, maxValue);
        }
    }

    public void algoHaveChanged(String algoName) {
        int depth = getIntValueOfSpinner(treeDepthSpinner);
        makePause();
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).algoHaveChanged(algoName);
        }
    }

    private void playPausePerformed(ActionEvent evt) {
        if(play) {
            makePause();
        }
        else {
            for(MinMaxEducativeToolsListener listener : listeners) {
                listener.play(inExplorerMode);
                play = !play;
                playButton.setText("Pause");
            }
        }
    }

    private void stopPerformed(ActionEvent evt) {
        for(MinMaxEducativeToolsListener listener : listeners) {
            makePause();
            listener.stop(inExplorerMode);
        }
    }

    private void makePause() {
        for(MinMaxEducativeToolsListener listener : listeners) {
            listener.pause(inExplorerMode);
            play = false;
            playButton.setText("Play");
        }
    }

    public class ExplorerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(checkBox.isSelected()) {
                makePause();

                gamePanel.setInExplorerMode(true);
                treePanel.setInExplorerMode(true);
                inExplorerMode = true;
                playButton.setEnabled(false);
                stopButton.setEnabled(false);
            }
            else {
                gamePanel.setInExplorerMode(false);
                treePanel.setInExplorerMode(false);
                inExplorerMode = false;
                playButton.setEnabled(true);
                stopButton.setEnabled(true);
            }
        }
    }

    public void addListener(MinMaxEducativeToolsListener listener) {
        listeners.add(listener);
    }

    private class TreeDepthSpinnerListener implements ChangeListener {

        public void stateChanged(ChangeEvent evt) {
            makePause();
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

//méthode listener

    public void clickOnNode(boolean isInExplorerMode, int[] path) {
        // ne fait rien
    }

    public void bestNodeSelected(boolean isInExplorerMode, int[] path) {
        // ne fait rien
    }

    public void preview(int[] moves, boolean inExplorerMode) {
        makePause();
    }

    public void quitPreview() {
        // ne fait rien
    }
}
