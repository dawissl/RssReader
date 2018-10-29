package cz.uhk.fim.rssreader.gui;

import cz.uhk.fim.rssreader.model.RssItem;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.swing.*;
import java.awt.*;

public class CardView extends JPanel {

    private static final int WIDTH=180;
    private static final int ITEM_WIDTH=160;
    private static final int HEIGHT=1;
    final String startHTML = "<html><p style='width:"+ITEM_WIDTH+" px'>";
    final String endHTML = "</p></html>";

    public CardView(RssItem item){
        setLayout(new WrapLayout());
        setSize(ITEM_WIDTH,HEIGHT);
        setTitle(item.getTitle());
        setDescription(item.getDescription());
        setBackground(new Color(item.hashCode()).brighter());
        setInfo(String.format("%s - %s",item.getPubDate(),item.getAuthor()));
    }

    private void setDescription(String description) {
        JLabel lblDescription = new JLabel();
        lblDescription.setText(String.format("%s%s%s",startHTML,description,endHTML));
        lblDescription.setSize(ITEM_WIDTH,HEIGHT);
        lblDescription.setFont(new Font(Font.MONOSPACED,Font.PLAIN,11));
        add(lblDescription);
    }

    private void setTitle(String title) {
        JLabel lblTitle = new JLabel();
        lblTitle.setText(String.format("%s%s%s",startHTML,shorted(title),endHTML));
        lblTitle.setSize(WIDTH,HEIGHT);
        lblTitle.setFont(new Font(Font.MONOSPACED,Font.BOLD,12));
        add(lblTitle);
    }

    private void setInfo(String format){
        JLabel lblInfo=new JLabel();
        lblInfo.setText(String.format("%s%s%s",startHTML,format,endHTML));
        lblInfo.setSize(ITEM_WIDTH,HEIGHT);
        lblInfo.setFont(new Font(Font.MONOSPACED, Font.ITALIC,10));
        lblInfo.setForeground(Color.GRAY);
        add(lblInfo);
    }

    private String shorted(String input){
        if(input.length()>25){
            return input.substring(0,25)+"...";
        }
        return input;
    }

}