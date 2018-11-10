package cz.uhk.fim.rssreader.gui;


import cz.uhk.fim.rssreader.model.RssItem;
import cz.uhk.fim.rssreader.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class DetailView extends JFrame {
    private static final int WIDTH = 540;
    private static final int ITEM_WIDTH = 400;
    private static final int HEIGHT = 200;
    final String startHTML = "<html><p style='width:" + ITEM_WIDTH + " px'>";
    final String endHTML = "</p></html>";
    RssItem rssItem;

    public DetailView(RssItem item){
        setLayout(new WrapLayout());
        setSize(WIDTH, HEIGHT);
        setUndecorated(true);
        setLocationRelativeTo(null);
        rssItem=item;
        initDetailListeners();
        setHeader(item.getTitle(),item.getAuthor());
        setContent(item.getDescription());
        setPubDate(item.getPubDate());
        getContentPane().setBackground(Color.decode(FileUtils.BACKGROUND_COLOR));

    }

    private void setContent(String description) {
        JLabel lblContent = new JLabel();
        lblContent.setForeground(Color.decode(FileUtils.TEXT_COLOR));
        lblContent.setFont(new Font("Times New Roman",Font.PLAIN,12));
        lblContent.setText(String.format("%s%s%s", startHTML, description, endHTML));
        add(lblContent,"Center");

    }

    private void setHeader(String title,String author) {
        JPanel header = new JPanel();
        header.setBackground(Color.decode(FileUtils.BACKGROUND_COLOR));
        header.setLayout(new WrapLayout());
        JLabel lblTitle = new JLabel();
        JLabel lblAuthor = new JLabel();
        lblTitle.setText(String.format("%s%s%s", startHTML, title, endHTML));
        lblTitle.setFont(new Font("Helvetica",Font.BOLD,16));
        lblAuthor.setFont(new Font("Helvetica",Font.PLAIN,12));
        lblTitle.setForeground(Color.decode(FileUtils.HEADER_COLOR));
        header.add(lblTitle);
        if(author!=null){
            lblAuthor.setForeground(Color.lightGray);

            lblAuthor.setText(String.format("%s%s%s", startHTML, "Autor:"+author, endHTML));
            header.add(lblAuthor);
        }
        add(header,"North");
    }

    private void setPubDate(String info) {
        JLabel lblInfo = new JLabel();
        lblInfo.setForeground(Color.decode(FileUtils.INFO_COLOR));
        lblInfo.setFont(new Font("Lucida Fax",Font.ITALIC,10));
        lblInfo.setText(String.format("%s%s%s", startHTML, info, endHTML));
        add(lblInfo,"South");
    }



    private void initDetailListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    setVisible(false);
                }
            }
        });
    }


}
