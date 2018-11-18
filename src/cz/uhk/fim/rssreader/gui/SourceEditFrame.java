package cz.uhk.fim.rssreader.gui;

import cz.uhk.fim.rssreader.model.RSSSource;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author David Sladecek
 */
public class SourceEditFrame extends JFrame {

    private String sourceName;
    private String sourceLink;
    private JComboBox<RSSSource> sources;
    private int index=-1;

    public SourceEditFrame(JComboBox<RSSSource> sources,Object source, int index) {
        this.sources=sources;
        RSSSource created = (RSSSource) source;
        this.sourceLink = created.getSource();
        this.sourceName = created.getName();
        this.index = index;
        init();
    }

    public SourceEditFrame(JComboBox<RSSSource> sources) {
        this.sources=sources;
        init();
    }

    private void init() {
        setTitle("Add/Edit source");
        setSize(480, 150);
        setLocationRelativeTo(null);
        initContent();
    }


    private void initContent() {
        JPanel namePanel = new JPanel(new FlowLayout());
        JPanel linkPanel = new JPanel(new FlowLayout());
        JPanel controlPanel = new JPanel(new FlowLayout());
        JLabel lblName = new JLabel("Name");
        JLabel lblSource = new JLabel("Source");
        JLabel lblError = new JLabel("Invalid input");
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        JTextField txtName = new JTextField(sourceName);
        txtName.setPreferredSize(new Dimension(350, 20));
        JTextField txtLink = new JTextField(sourceLink);
        txtLink.setPreferredSize(new Dimension(350, 20));
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        namePanel.add(lblName);
        namePanel.add(txtName);
        linkPanel.add(lblSource);
        linkPanel.add(txtLink);
        controlPanel.add(btnOK);
        controlPanel.add(btnCancel);
        controlPanel.add(lblError,"South");
        add(namePanel, "North");
        add(linkPanel, "Center");
        add(controlPanel, "South");

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                setVisible(false);
            }
        });

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sourceValidation()) {
                    if(index==-1){
                        sources.addItem(new RSSSource(txtName.getText(),txtLink.getText()));
                    }else{
                        sources.removeItemAt(index);
                        sources.addItem(new RSSSource(txtName.getText(),txtLink.getText()));
                        sources.setSelectedIndex(sources.getItemCount()-1);
                    }
                    dispose();
                    setVisible(false);
                }else{
                    lblError.setVisible(true);
                }

            }

            private boolean sourceValidation() {
                txtLink.setText(txtLink.getText().replace(";","").trim());
                txtName.setText(txtName.getText().replace(";","").trim());
                if(txtLink.getText().isEmpty()||txtName.getText().isEmpty()){
                    return false;
                }
                if(txtLink.getText().contains(" ")){
                    return false;
                }
                if(!txtLink.getText().contains("http")&&!txtLink.getText().contains(".xml")){
                    return  false;
                }
                if(txtLink.getText().contains("http")&&txtLink.getText().contains(".xml")){
                    return  false;
                }
                return true;
            }
        });
    }

}



