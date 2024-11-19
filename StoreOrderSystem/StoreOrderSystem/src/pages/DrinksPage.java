package pages;

import model.*;
import model.MenuItem;
import net.miginfocom.swing.MigLayout;
import view.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.MemoryNotificationInfo;
import java.util.ArrayList;

public class DrinksPage {
    private JPanel contentPanel;
    private CustomerView cView;
    public DrinksPage(CustomerView cView) {
        this.cView = cView;
        contentPanel = cView.contentPanel;
    }
    public JPanel returnPage(){
        JPanel panel = new JPanel(new MigLayout());

        JLabel drinksLabel = new JLabel("Drinks:");


        JCheckBox pepsiBox = new JCheckBox("Pepsi");
        ButtonFactory pepsiBF = new ButtonFactory();
        ButtonFactory pepsiSize = new ButtonFactory();


        JCheckBox dPepsiBox = new JCheckBox("Diet Pepsi");
        ButtonFactory dPepsiBF = new ButtonFactory();
        ButtonFactory dPepsiSize = new ButtonFactory();

        JCheckBox orangeBox = new JCheckBox("Orange");
        ButtonFactory orangeBF = new ButtonFactory();
        ButtonFactory orangeSize = new ButtonFactory();


        JCheckBox dOrangeBox = new JCheckBox("Diet Orange");
        ButtonFactory dOrangeBF = new ButtonFactory();
        ButtonFactory dOrangeSize = new ButtonFactory();

        JCheckBox rBeerBox = new JCheckBox("Root Beer");
        ButtonFactory rBeerBF = new ButtonFactory();
        ButtonFactory rBeerSize = new ButtonFactory();

        JCheckBox dRBeerBox = new JCheckBox("Diet Root Beer");
        ButtonFactory dRBeerBF = new ButtonFactory();
        ButtonFactory dRBeerSize = new ButtonFactory();

        JCheckBox starryBox = new JCheckBox("Starry");
        ButtonFactory starryBF = new ButtonFactory();
        ButtonFactory starrySize = new ButtonFactory();

        JCheckBox lemonadeBox = new JCheckBox("Lemonade");
        ButtonFactory lemonadeBF = new ButtonFactory();
        ButtonFactory lemonadeSize = new ButtonFactory();



        panel.add(drinksLabel, "cell 0 0");

        JPanel pepsiPanel = pepsiBF.buildIncrementButton();
        JPanel pepsiSizePanel = pepsiSize.buildSizeButton();
        panel.add(pepsiBox, "cell 0 1");
        panel.add(pepsiPanel, "cell 1 1");
        panel.add(pepsiSizePanel, "cell 0 2");

        JPanel dPepsiPanel = dPepsiBF.buildIncrementButton();
        JPanel dPepsiSizePanel = dPepsiSize.buildSizeButton();
        panel.add(dPepsiBox, "cell 0 3");
        panel.add(dPepsiPanel, "cell 1 3");
        panel.add(dPepsiSizePanel, "cell 0 4");


        JPanel orangePanel = orangeBF.buildIncrementButton();
        JPanel orangeSizePanel = orangeSize.buildSizeButton();
        panel.add(orangeBox, "cell 0 5");
        panel.add(orangePanel, "cell 1 5");
        panel.add(orangeSizePanel, "cell 0 6");

        JPanel dOrangePanel = dOrangeBF.buildIncrementButton();
        JPanel dOrangeSizePanel = dOrangeSize.buildSizeButton();
        panel.add(dOrangeBox, "cell 0 7");
        panel.add(dOrangePanel, "cell 1 7");
        panel.add(dOrangeSizePanel, "cell 0 8");


        JPanel rBeerPanel = rBeerBF.buildIncrementButton();
        JPanel rBeerSizePanel = rBeerSize.buildSizeButton();
        panel.add(rBeerBox, "cell 2 1");
        panel.add(rBeerPanel, "cell 3 1");
        panel.add(rBeerSizePanel, "cell 2 2");

        JPanel dRBeerPanel = dRBeerBF.buildIncrementButton();
        JPanel dRBeerSizePanel = dRBeerSize.buildSizeButton();
        panel.add(dRBeerBox, "cell 2 3");
        panel.add(dRBeerPanel, "cell 3 3");
        panel.add(dRBeerSizePanel, "cell 2 4");

        JPanel starryPanel = starryBF.buildIncrementButton();
        JPanel starrySizePanel = starrySize.buildSizeButton();
        panel.add(starryBox, "cell 2 5");
        panel.add(starryPanel, "cell 3 5");
        panel.add(starrySizePanel, "cell 2 6");

        JPanel lemonadePanel = lemonadeBF.buildIncrementButton();
        JPanel lemonadeSizePanel = lemonadeSize.buildSizeButton();
        panel.add(lemonadeBox, "cell 2 7");
        panel.add(lemonadePanel, "cell 3 7");
        panel.add(lemonadeSizePanel, "cell 2 8");

        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");

        panel.add(submitBtn, "cell 0 9");
        panel.add(cancelBtn, "cell 1 9");


        //Sets inc Panels to not visible
        pepsiPanel.setVisible(false);
        dPepsiPanel.setVisible(false);
        orangePanel.setVisible(false);
        dOrangePanel.setVisible(false);
        rBeerPanel.setVisible(false);
        dRBeerPanel.setVisible(false);
        starryPanel.setVisible(false);
        lemonadePanel.setVisible(false);

        pepsiSizePanel.setVisible(false);
        dPepsiSizePanel.setVisible(false);
        orangeSizePanel.setVisible(false);
        dOrangeSizePanel.setVisible(false);
        rBeerSizePanel.setVisible(false);
        dRBeerSizePanel.setVisible(false);
        starrySizePanel.setVisible(false);
        lemonadeSizePanel.setVisible(false);


        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validItem = true;
                Receipt receipt = cView.view.controller.getReceipt();
                ArrayList<MenuItem> menuItemAr = receipt.getMenuItemAr();

                if(pepsiBox.isSelected()){
                    if(pepsiSize.getSelectedSize().equals("none")){
                        validItem = false;
                    }
                    else{
                        MenuItem item = new MenuItem(pepsiBF.getIncAmount(),pepsiBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }
                if(dPepsiBox.isSelected()){
                    if(dPepsiSize.getSelectedSize().equals("none")){
                        validItem = false;
                    }
                    else{
                        MenuItem item = new MenuItem(dPepsiBF.getIncAmount(),dPepsiBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }
                if(orangeBox.isSelected()){
                    if(orangeSize.getSelectedSize().equals("none")){
                        validItem = false;
                    }
                    else{
                        MenuItem item = new MenuItem(orangeBF.getIncAmount(),orangeBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }
                if(dOrangeBox.isSelected()){
                    if(dOrangeSize.getSelectedSize().equals("none")){
                        validItem = false;
                    }
                    else{
                        MenuItem item = new MenuItem(dOrangeBF.getIncAmount(),dOrangeBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }
                if(rBeerBox.isSelected()){
                    if(rBeerSize.getSelectedSize().equals("none")){
                        validItem = false;
                        System.out.println("test2");
                    }
                    else{
                        System.out.println("test");
                        MenuItem item = new MenuItem(rBeerBF.getIncAmount(),rBeerBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }
                if(dRBeerBox.isSelected()){
                    if(dRBeerSize.getSelectedSize().equals("none")){
                        validItem = false;
                    }
                    else{
                        MenuItem item = new MenuItem(dRBeerBF.getIncAmount(),dRBeerBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }
                if(starryBox.isSelected()){
                    if(starrySize.getSelectedSize().equals("none")){
                        validItem = false;
                    }
                    else{
                        MenuItem item = new MenuItem(starryBF.getIncAmount(),starryBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }
                if(lemonadeBox.isSelected()){
                    if(lemonadeSize.getSelectedSize().equals("none")){
                        validItem = false;
                    }
                    else{
                        MenuItem item = new MenuItem(lemonadeBF.getIncAmount(),lemonadeBox.getText(),1.75);
                        menuItemAr.add(item);
                    }
                }





                if(validItem){
                    receipt.setMenuItemAr(menuItemAr);
                    cView.switchPage("MenuPage", cView);
                }

            }
        });

        // Action Listeners for button elements
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "MenuPage");
            }
        });
        pepsiBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pepsiPanel.setVisible(pepsiBox.isSelected());
                pepsiSizePanel.setVisible(pepsiBox.isSelected());
            }
        });
        dPepsiBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dPepsiPanel.setVisible(dPepsiBox.isSelected());
                dPepsiSizePanel.setVisible(dPepsiBox.isSelected());
            }
        });
        orangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orangePanel.setVisible(orangeBox.isSelected());
                orangeSizePanel.setVisible(orangeBox.isSelected());
            }
        });
        dOrangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dOrangePanel.setVisible(dOrangeBox.isSelected());
                dOrangeSizePanel.setVisible(dOrangeBox.isSelected());
            }
        });
        rBeerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rBeerPanel.setVisible(rBeerBox.isSelected());
                rBeerSizePanel.setVisible(rBeerBox.isSelected());
            }
        });
        dRBeerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dRBeerPanel.setVisible(dRBeerBox.isSelected());
                dRBeerSizePanel.setVisible(dRBeerBox.isSelected());
            }
        });
        starryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starryPanel.setVisible(starryBox.isSelected());
                starrySizePanel.setVisible(starryBox.isSelected());
            }
        });
        lemonadeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lemonadePanel.setVisible(lemonadeBox.isSelected());
                lemonadeSizePanel.setVisible(lemonadeBox.isSelected());
            }
        });

        return panel;
    }

    public boolean processChecked(JCheckBox checkBox, ButtonFactory bF){
        if(checkBox.isSelected()){

            String size = bF.getSelectedSize();
            switch(size){
                case "small":
                    return true;
                case "medium":
                    return true;
                case "large":
                    return true;
                case "none":
                    return false;
            }


        }

        return true;
    }
}
