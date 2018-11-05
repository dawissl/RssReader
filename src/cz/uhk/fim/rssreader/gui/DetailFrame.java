package cz.uhk.fim.rssreader.gui;


import cz.uhk.fim.rssreader.model.RssItem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DetailFrame extends JFrame {

    RssItem rssItem;

    public DetailFrame(RssItem item){
        rssItem=item;
        initDetail();
    }

    private void initDetail() {
       setUndecorated(true);
        setSize(320,480);
        setLocationRelativeTo(null);
        initContent();
        initDetailListeners();
    }

    private void initContent() {
        JLabel lbl = new JLabel(rssItem.getTitle());
        add(lbl);
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
