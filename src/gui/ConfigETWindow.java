package gui;

import explorer.AbstractGameEvalFctFactory;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Cette classe est utilise pour creer ajouter une fenetre contenant un panel
 * de configuration pour l'outil pedagogique.
 * @author Sebastien Drobisz.
 */
public class ConfigETWindow extends JFrame{
    private static final int HEIGHT = 200;
    private static final int WIDTH = 480;

    private ConfigETPanel pan;
    private MainFrame mainFrame;

    /**
     * Ce constructeur permet de creer la fenetre ainsi que le panel
     * de configuration qui lui est associe.
     * @param mainFrame La frame principale de l'outil.
     */
    public ConfigETWindow(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.setTitle("Options");

        this.setSize(WIDTH, HEIGHT);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.setResizable(false);

        pan = new ConfigETPanel(this);
        this.setContentPane(pan);
    }

    /**
     * Cette methode permet de prevenir le frame principale qu'une demande
     * de changement d'algo a ete realisee.
     * @param algoName Le nom de l'algo qu'il a ete demande d'utiliser.
     */
    public void algoHaveChanged(String algoName) {
        mainFrame.algoHaveChanged(algoName);
    }

    /**
     * Cette methode permet de prevenir la frame principale qu'une demande
     * de changement de fonction d'evaluatio a ete realisee
     */
    public void fctHaveChanged(String fctName) {
        mainFrame.fctHaveChanged(fctName);
    }

    /**
     * Cette fonction permet de donner au panel le factory des fonctions
     * d'evaluation instanciables.
     * @param factory Le factory.
     */
    public void setEvalFctFactory(AbstractGameEvalFctFactory factory) {
        pan.setEvalFctFactory(factory);
    }

    /**
     * Cette methode permet de prevenir qu'une modification de la fenetre
     * de recherche a ete solicitee.
     * @param minValue La borne inferieure de la nouvelle fenetre.
     * @param maxValue La borne superieure de la nouvelle fenetre.
     */
    public void minMaxWindowValuesSpinnerStateChanged(int minValue,
                                                                 int maxValue) {

        mainFrame.windowValuesHaveChanged(minValue, maxValue);
    }
}
