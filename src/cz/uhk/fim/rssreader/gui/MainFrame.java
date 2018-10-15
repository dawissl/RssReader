package cz.uhk.fim.rssreader.gui;



import cz.uhk.fim.rssreader.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame {

    public MainFrame() {
        init();
    }

    private void init(){
        setTitle("RSS Reader");
        setSize(1366,768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initContentUI();
    }


    private void initContentUI(){
        JPanel controlPanel = new JPanel(new BorderLayout());
        JButton btnLoad = new JButton("Load");
        JTextField txtInputField = new JTextField();
        JButton btnSave = new JButton("Save");

        controlPanel.add(btnLoad,"West");
        controlPanel.add(btnSave,"East");
        controlPanel.add(txtInputField,"Center");
        add(controlPanel,"North");

        JTextArea txtContent  = new JTextArea();
        add(new JScrollPane(txtContent),"Center");

        try {
            txtContent.setText(FileUtils.loadStrinfFromFile("rssVC.xml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

        //TODO
        //  add listeners for SAVE and LOAD
        // pridat validateInput - true/false
        // pridat JLabel ktery bude ukazovat chyby (vyskyt v kontrola panelu nad nebo pod txtInputFieldem)
        //                  - mozne vystupy chyba pro load, save, validate (vzdy dat do catche) - enumerace


}
