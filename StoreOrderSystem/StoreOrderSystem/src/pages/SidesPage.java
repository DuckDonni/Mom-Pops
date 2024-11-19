package pages;

import model.*;
import net.miginfocom.swing.MigLayout;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SidesPage {
    private JPanel contentPanel;
    private CustomerView cView;
    public SidesPage(CustomerView cView) {
        this.cView = cView;
        contentPanel = cView.contentPanel;
    }
    public JPanel returnPage(){
        JPanel panel = new JPanel(new MigLayout());
        JLabel title = new JLabel("Sides:");
        JCheckBox bSticksBox = new JCheckBox("Bread Sticks");
        ButtonFactory bSticksIncBF = new ButtonFactory();
        JPanel bSticksIncPanel = bSticksIncBF.buildIncrementButton();

        JCheckBox bSBitesBox = new JCheckBox("Bread Stick Bites");
        ButtonFactory bSBitesIncBF = new ButtonFactory();
        JPanel bSBitesIncPanel = bSBitesIncBF.buildIncrementButton();

        JCheckBox cookieBox = new JCheckBox("Big Chocolate Chip Cookie");
        ButtonFactory cookieIncBF = new ButtonFactory();
        JPanel cookieIncPanel = cookieIncBF.buildIncrementButton();


        bSticksIncPanel.setVisible(false);
        bSBitesIncPanel.setVisible(false);
        cookieIncPanel.setVisible(false);


        bSticksBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bSticksIncPanel.setVisible(bSticksBox.isSelected());
            }
        });
        bSBitesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bSBitesIncPanel.setVisible(bSBitesBox.isSelected());
            }
        });
        cookieBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cookieIncPanel.setVisible(cookieBox.isSelected());
            }
        });

        panel.add(title, "cell 0 0");
        panel.add(bSticksBox, "cell 0 1");
        panel.add(bSticksIncPanel, "cell 1 1");
        panel.add(bSBitesBox, "cell 0 2");
        panel.add(bSBitesIncPanel, "cell 1 2");
        panel.add(cookieBox, "cell 0 3");
        panel.add(cookieIncPanel, "cell 1 3");

        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");

        panel.add(submitBtn, "cell 0 4");
        panel.add(cancelBtn, "cell 1 4");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validForm = true;
                Receipt receipt = cView.view.controller.getReceipt();
                ArrayList<MenuItem> menuItemAr = receipt.getMenuItemAr();
                if(bSticksBox.isSelected()){
                    int quantity = bSticksIncBF.getIncAmount();

                    MenuItem item = new MenuItem(quantity, "Bread Sticks", 4.00);
                    menuItemAr.add(item);
                }
                if(bSBitesBox.isSelected()){
                    int quantity = bSBitesIncBF.getIncAmount();
                    MenuItem item = new MenuItem(quantity, "Bread Stick Bites", 2.00);
                    menuItemAr.add(item);
                }
                if(cookieBox.isSelected()){
                    int quantity = cookieIncBF.getIncAmount();
                    MenuItem item = new MenuItem(quantity, "Big Chocolate Chip Cookie", 4.00);
                    menuItemAr.add(item);
                }
                receipt.setMenuItemAr(menuItemAr);
                cView.switchPage("MenuPage", cView);



            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("MenuPage", cView);
            }
        });

        return panel;
    }
}
