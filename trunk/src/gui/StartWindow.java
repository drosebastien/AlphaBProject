package gui;

import javax.swing.JFrame;

/**
 * Cette classe permet d'afficher une fenetre de demarrage pour selection du
 * jeu utilise par l'outil.
 * @author Sebastien Drobisz.
 */
public class StartWindow extends JFrame{
    private static final int HEIGHT = 150;
    private static final int WIDTH = 250;

    private StartPanel pan;

    /**
     * Constructeur permettant de creer la fenetre de selection du jeu.
     */
    public StartWindow(){
        this.setTitle("MinMax Algorithms Tools");

        this.setSize(WIDTH, HEIGHT);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        pan = new StartPanel();
        this.setContentPane(pan);
        this.setVisible(true);
    }
}
