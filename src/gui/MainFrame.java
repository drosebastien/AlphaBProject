package gui;

import morpion.gui.*;
import framework.*;
import explorer.*;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.Icon;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Cette classe permet de construire la frame principale de l'outil contenant
 * les differents composants
 * @author Sebastien Drobisz
 */
public class MainFrame extends JFrame implements TreePanelListener {
    private static final int HEIGHT = 600;
    private static final int WIDTH = 1024;

    private ArrayList<MinMaxEducativeToolsListener> listeners;

    private boolean play;
    private Icon playIcon;
    private Icon pauseIcon;

    private ConfigETWindow configETWindow;

    private GridBagConstraints gbc;
    private GamePanel gamePanel;
    private TreePanel treePanel;
    private JScrollPane treePanelScrollPane;
    private boolean inExplorerMode;

    private JSpinner treeDepthSpinner;
    private JButton playButton;
    private JSlider speedSlider;
    private JButton stopButton;
    private JButton nextButton;
    private JButton previousButton;
    private JCheckBox checkBox;
    private AlgoPanel algoPanel;

    /**
     * Ce constructeur permet de creer une frame principale de l'outil en
     * lui fournissant les 3 composants principaux.
     * @param gPanel Le panel du jeu.
     * @param treePanel Le panel de l'arbre d'exploration
     * @param algoPanel Le panel fournissant des informations supplementaire
     * sur le fonctionnement de l'algorithme.
     */
    public MainFrame(GamePanel gPanel, TreePanel treePanel,
                                                        AlgoPanel algoPanel) {
        super("Exploration algorithm");

        play = false;

        this.treePanel = treePanel;
        treePanel.addListener(this);
        this.gamePanel = gPanel;
        this.algoPanel = algoPanel;

        listeners = new ArrayList<MinMaxEducativeToolsListener>();
        inExplorerMode = false;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());
        initComponent();
        initMenu();

        setLocationRelativeTo(null);
        setVisible(true);
        //repaint();
    }

    private void initMenu() {
        configETWindow = new ConfigETWindow(this);
        JMenuBar menuBar = new JMenuBar();

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem optionsItem = new JMenuItem("Options");
        optionsItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                optionsItemEvent(evt);
            }
        });
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        toolsMenu.add(optionsItem);
        toolsMenu.add(quitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        JMenuItem helpItem = new JMenuItem("Help");
        helpMenu.add(helpItem);

        menuBar.add(toolsMenu);
        //menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
    }

    private void optionsItemEvent(ActionEvent event) {
        configETWindow.setVisible(true);
    }

    private void initComponent() {
        gbc = new GridBagConstraints();

        JLabel explorerLabel = new JLabel("Exploration mode");
        JLabel treeDepthLabel = new JLabel("Tree depth");

        treePanelScrollPane = new JScrollPane(treePanel);

        checkBox = new JCheckBox();
        checkBox.addActionListener(new ExplorerListener());

        Image pauseImage = null;
        try {
            pauseImage = ImageIO.read(new File("img/button/pause.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        pauseIcon = new ImageIcon(pauseImage);

        Image playImage = null;
        try {
            playImage = ImageIO.read(new File("img/button/play.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        playIcon = new ImageIcon(playImage);
        playButton = new JButton(playIcon);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                playPausePerformed(evt);
            }
        });
        playButton.setMinimumSize(new Dimension(60, 27));
        playButton.setPreferredSize(new Dimension(60, 27));


        Image stopImage = null;
        try {
            stopImage = ImageIO.read(new File("img/button/stop.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        Icon stopIcon = new ImageIcon(stopImage);
        stopButton = new JButton(stopIcon);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stopPerformed(evt);
            }
        });
        stopButton.setMinimumSize(new Dimension(35, 27));
        stopButton.setPreferredSize(new Dimension(35, 27));

        speedSlider = new JSlider();
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });

        speedSlider.setMinimumSize(new Dimension(100,25));
        speedSlider.setInverted(true);
        speedSlider.setValue(13);
        speedSlider.setMinimum(8);
        speedSlider.setMaximum(18);

        Integer value = new Integer(2);
        Integer min = new Integer(2);
        Integer max = new Integer(7);
        Integer step = new Integer(1);
        treeDepthSpinner = new JSpinner();
        treeDepthSpinner.setMinimumSize(new Dimension(40, 25));
        treeDepthSpinner.setPreferredSize(new Dimension(40, 25));
        treeDepthSpinner.addChangeListener(new TreeDepthSpinnerListener());
        SpinnerNumberModel treeDepthSpinnerModel = new SpinnerNumberModel(
                                                         value, min, max, step);
        treeDepthSpinner.setModel(treeDepthSpinnerModel);


        Image nextImage = null;
        try {
            nextImage = ImageIO.read(new File("img/button/next.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        Icon nextIcon = new ImageIcon(nextImage);
        nextButton = new JButton(nextIcon);
        nextButton.setMinimumSize(new Dimension(40, 27));
        nextButton.setPreferredSize(new Dimension(40, 27));
        nextButton.addActionListener(new NextListener());


        Image previousImage = null;
        try {
            previousImage = ImageIO.read(new File("img/button/previous.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        Icon previousIcon = new ImageIcon(previousImage);
        previousButton = new JButton(previousIcon);
        previousButton.setMinimumSize(new Dimension(40, 27));
        previousButton.setPreferredSize(new Dimension(40, 27));
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
        gbc.gridheight = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.BOTH;
        add(algoPanel, gbc);

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

        gbc.gridy = 7;
        gbc.weighty = 0.;
        gbc.weightx = 0.;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 0);
        add(playButton, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0.;
        gbc.insets = new Insets(5, 40, 5, 0);
//        gbc.anchor = GridBagConstraints.CENTER;
        add(previousButton, gbc);

        gbc.gridx = 5;
        gbc.insets = new Insets(5, 5, 5, 0);
//        gbc.anchor = GridBagConstraints.CENTER;
        add(stopButton, gbc);

        gbc.gridx = 6;
        gbc.weightx = 0.;
        gbc.insets = new Insets(5, 5, 5, 0);
//        gbc.anchor = GridBagConstraints.LINE_START;
        add(nextButton, gbc);

        gbc.gridx = 7;
        gbc.weightx = 1;
        gbc.insets = new Insets(5,5,5,10);
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Speed"), gbc);

        gbc.gridx = 8;
        gbc.weightx = 0;
        gbc.insets = new Insets(5,5,5,10);
        gbc.anchor = GridBagConstraints.LINE_END;
        add(speedSlider, gbc);
    }

    /**
     * Cette methode permet de fournir le factory des fonctions d'evaluation
     * @param factory Le factory des fonctions d'evaluation.
     */
    public void setEvalFctFactory(AbstractGameEvalFctFactory factory) {
        configETWindow.setEvalFctFactory(factory);
    }

    /**
     * Cette methode permet de prevenir les listeners que la fenetre de
     * recherche de l'algorithme a ete modifiee.
     * @param minValue La borne inferieure de la fenetre de recherche.
     * @param maxValue La borne superieure de la fenetre de recherche.
     */
    public void windowValuesHaveChanged(int minValue, int maxValue) {
        makePause();
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).windowValuesHaveChanged(minValue, maxValue);
        }
    }

    /**
     * Permet de prevenir les listeners que l'algorithme doit etre modifie
     * @param algoName Le nom l'algorithme qui doit etre change.
     */
    public void algoHaveChanged(String algoName) {
        int depth = getIntValueOfSpinner(treeDepthSpinner);
        makePause();
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).algoHaveChanged(algoName);
        }
    }

    /**
     * Cette methode permet de prevenir les listeners qu'une demande de
     * changement de fonction d'evaluation a ete faite.
     * @param fctName Le nom de la fonction qui doit etre modifiee.
     */
    public void fctHaveChanged(String fctName) {
        int depth = getIntValueOfSpinner(treeDepthSpinner);//pas sur utile
        makePause();
        for(MinMaxEducativeToolsListener listener : listeners) {
            listener.fctHaveChanged(fctName);
        }
    }

    private void playPausePerformed(ActionEvent evt) {
        if(play) {
            makePause();
        }
        else {
            sendSpeedToListeners();
            for(MinMaxEducativeToolsListener listener : listeners) {
                listener.play(inExplorerMode);
            }
            play = !play;
            playButton.setIcon(pauseIcon);
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
            playButton.setIcon(playIcon);
        }
    }

    private class ExplorerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(checkBox.isSelected()) {
                makePause();

                setInExplorerMode(true);
            }
            else {
                setInExplorerMode(false);
            }
        }
    }

    private void setInExplorerMode(boolean value) {
        inExplorerMode = value;
        gamePanel.setInExplorerMode(inExplorerMode);
        treePanel.setInExplorerMode(inExplorerMode);
        playButton.setEnabled(!inExplorerMode);
        stopButton.setEnabled(!inExplorerMode);
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

    private class NextListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for(MinMaxEducativeToolsListener listener : listeners) {
                listener.progress(inExplorerMode);
            }
        }
    }

    private class PreviousListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for(MinMaxEducativeToolsListener listener : listeners) {
                listener.removeLast(inExplorerMode);
            }
        }
    }

    private void speedSliderStateChanged(ChangeEvent evt) {
        sendSpeedToListeners();
    }

    private void sendSpeedToListeners() {
        for(MinMaxEducativeToolsListener listener : listeners) {
            listener.setSpeed((int) Math.round(Math.pow(1.6,
                                                    speedSlider.getValue())));
        }
    }

//méthode listener

    public void clickOnNode(boolean isInExplorerMode, int[] path) {
        // ne fait rien
    }

    public void bestNodeSelected(boolean isInExplorerMode, int[] path) {
        makePause();
    }

    public void preview(int[] moves, boolean inExplorerMode) {
        makePause();
    }

    public void quitPreview() {
        // ne fait rien
    }
}
