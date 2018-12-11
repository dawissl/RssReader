package cz.uhk.fim.rssreader.gui;

import cz.uhk.fim.rssreader.model.RSSList;
import cz.uhk.fim.rssreader.model.RSSSource;
import cz.uhk.fim.rssreader.model.RssItem;

import cz.uhk.fim.rssreader.utils.FileUtils;
import cz.uhk.fim.rssreader.utils.RssParser;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private RSSList rssList;

    public MainFrame() {
        init();
    }

    private void init() {
        setTitle("RSS Reader");
        setSize(1024, 640);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initContentUI();

    }

    private void initContentUI() {
        JPanel controlPanel = new JPanel(new FlowLayout());
        JSlider slider = new JSlider(2,4,2);
        JLabel lblSize= new JLabel("Zoom");
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnRemove = new JButton("Remove");
        JButton btnSave = new JButton("Save");

        JComboBox<RSSSource> sources = new JComboBox<>();
        sources.setPreferredSize(new Dimension(350, 25));
        controlPanel.add(lblSize);
        controlPanel.add(slider);
        controlPanel.add(btnEdit);
        controlPanel.add(btnAdd);
        controlPanel.add(btnRemove);
        controlPanel.add(sources);
        controlPanel.add(btnSave);
        add(controlPanel, "North");

        JPanel contentPanel = new JPanel(new WrapLayout());
        JLabel lblInfo = new JLabel("Nothing to load, please add source");
        lblInfo.setVisible(false);
        contentPanel.add(lblInfo, "Center");

        add(new JScrollPane(contentPanel), "Center");

        File f = new File(FileUtils.CONFIG_FILE);
        if(f.exists()&&!f.isDirectory()&&f.getTotalSpace()>0){
            try {
                List<RSSSource> list = FileUtils.loadSources();
                for(RSSSource s:list){
                    sources.addItem(s);
                }
                loadArticles(sources, contentPanel,slider.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            lblInfo.setVisible(true);
            btnEdit.setEnabled(false);
            btnRemove.setEnabled(false);
        }


        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RSSSource> output = new ArrayList<>();
                for(int i=0;i<sources.getItemCount();i++){
                    RSSSource tmp = sources.getItemAt(i);
                    output.add(tmp);
                }
                FileUtils.saveSources(output);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SourceEditFrame(sources).setVisible(true);
                btnEdit.setEnabled(true);
                btnRemove.setEnabled(true);
            }
        });


        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SourceEditFrame(sources, sources.getSelectedItem(), sources.getSelectedIndex()).setVisible(true);
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sources.getItemCount()==1){
                    removeArticles(rssList, contentPanel);
                }
                sources.removeItemAt(sources.getSelectedIndex());
            }
        });


        sources.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sources.getItemCount() >0) {
                    if(!lblInfo.isVisible())
                        removeArticles(rssList, contentPanel);
                    lblInfo.setVisible(false);
                    loadArticles(sources, contentPanel,slider.getValue());
                }
            }
        });


         slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                removeArticles(rssList,contentPanel);
                loadArticles(sources,contentPanel,slider.getValue());
            }
        });
    }

    private void removeArticles(RSSList rssList, JPanel contentPanel) {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        rssList.clearAll();

    }

    private void loadArticles(JComboBox<RSSSource> sources, JPanel contentPanel,int size) {
        try {

            RSSSource loadedSource = (RSSSource) sources.getSelectedItem();
            if(loadedSource==null) return;
            rssList = new RssParser().getParsedRSS(loadedSource.getSource());
            for (RssItem item : rssList.getAllItems()) {
                contentPanel.add(new CardView(item,size));
            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }



}
