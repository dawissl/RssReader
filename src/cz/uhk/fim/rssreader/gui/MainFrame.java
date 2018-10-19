package cz.uhk.fim.rssreader.gui;



import cz.uhk.fim.rssreader.utils.ErrorsType;
import cz.uhk.fim.rssreader.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        JLabel lblValidation = new JLabel();
        lblValidation.setHorizontalAlignment(JLabel.CENTER);


        controlPanel.add(btnLoad,"West");
        controlPanel.add(btnSave,"East");
        controlPanel.add(txtInputField,"Center");
        controlPanel.add(lblValidation,"South");
        add(controlPanel,"North");

        JTextArea txtContent  = new JTextArea();
        add(new JScrollPane(txtContent),"Center");


        btnLoad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(!FileUtils.validateInput(lblValidation,txtInputField.getText())){
                        throw  new IOException(lblValidation.getText());
                    }
                    txtContent.setText(FileUtils.loadStringFromFile(txtInputField.getText()));
                } catch (IOException e2) {
                    FileUtils.validateInput(lblValidation,"LOAD_ERROR");
                    e2.printStackTrace();
                    System.out.println(e2.getMessage());
                }
            }
        });

        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(!FileUtils.validateInput(lblValidation,txtInputField.getText())){
                        throw  new IOException(lblValidation.getText());
                    }
                    FileUtils.saveStringToFile(txtInputField.getText(),txtContent.getText().getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                    FileUtils.validateInput(lblValidation,"SAVE_ERROR");
                    System.out.println(e1.getMessage());
                }
            }
        });


    }



}
