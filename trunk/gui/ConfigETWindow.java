package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ConfigETWindow extends JFrame{
    private static final int HEIGHT = 200;
    private static final int WIDTH = 300;

     public ConfigETWindow(){

        this.setTitle("Options");

        this.setSize(WIDTH, HEIGHT);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.setResizable(false);

        ConfigETPanel pan = new ConfigETPanel();
        this.setContentPane(pan);
     }
}
