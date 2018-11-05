package cz.uhk.fim.rssreader.gui;

import cz.uhk.fim.rssreader.model.RSSList;
import cz.uhk.fim.rssreader.model.RssItem;
import cz.uhk.fim.rssreader.utils.FileUtils;
import cz.uhk.fim.rssreader.utils.RssParser;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private RSSList rssList;

    public MainFrame() {
        init();
    }

    private void init(){
        setTitle("RSS Reader");
        setSize(1024,640);
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

        JPanel contentPanel = new JPanel(new WrapLayout());

        add(new JScrollPane(contentPanel),"Center");

        try {
            rssList = new RssParser().getParsedRSS("rssVC.xml");
            for(RssItem item : rssList.getAllItems()){
                contentPanel.add(new CardView(item));
            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

      /*  btnLoad.addMouseListener(new MouseAdapter() {
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
               /*try {
                    if(!FileUtils.validateInput(lblValidation,txtInputField.getText())){
                        throw  new IOException(lblValidation.getText());
                    }
                    FileUtils.saveStringToFile(txtInputField.getText(),txtContent.getText().getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                    FileUtils.validateInput(lblValidation,"SAVE_ERROR");
                    System.out.println(e1.getMessage());
                }
                try {
                    rssList = new RssParser().getParsedRSS(txtInputField.getText());
                    txtContent.setText("");
                    for(RssItem item : rssList.getAllItems()){
                        txtContent.append(String.format("%s - autor: %s%n",item.getTitle(),item.getAuthor()));
                    }
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });       */



    }



}
