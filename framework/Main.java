package framework;

import java.util.Scanner;
import javax.swing.UIManager;

import gui.StartWindow;

public class Main {
    public static void main(String[] args) {
        for (UIManager.LookAndFeelInfo laf :
            UIManager.getInstalledLookAndFeels() ){
            if ("Nimbus".equals(laf.getName())) {
                try {
                    UIManager.setLookAndFeel(laf.getClassName());

                } catch (Exception e) {
                // TODO: handle exception
                }
            }
        }

        try {
        UIManager.setLookAndFeel(
        "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        // TODO: handle exception
        }

        StartWindow tool = new StartWindow();

//        Processor processor = new Processor();
//        processor.start();
    }
}
