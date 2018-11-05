package cz.uhk.fim.rssreader.app;

import cz.uhk.fim.rssreader.gui.MainFrame;
import javax.swing.*;

public class App {

    public static void main (String []args){
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));

    }
}
