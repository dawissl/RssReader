package cz.uhk.fim.rssreader.gui;



import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


    public MainFrame() {
        init();
    }

    private void init(){
        setTitle("RSS Reader");
        setSize(1366,768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    //TODO kouknout na dva RSS feedy a zkopirovat si jejich url
    //https://videacesky.cz/feed
    //https://rss.idnes.cz
}
