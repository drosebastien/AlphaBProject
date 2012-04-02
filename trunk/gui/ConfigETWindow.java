package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ConfigETWindow extends JFrame{
    private static final int HEIGHT = 200;
    private static final int WIDTH = 300;
    private ConfigETPanel pan;
    private MainFrame mainFrame;

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

    public void algoHaveChanged(String algoName) {
        mainFrame.algoHaveChanged(algoName);
    }

    public void minMaxWindowValuesSpinnerStateChanged(int minValue,
                                                                 int maxValue) {

        mainFrame.windowValuesHaveChanged(minValue, maxValue);
    }
}
