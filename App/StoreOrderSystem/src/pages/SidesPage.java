package pages;

import model.*;
import net.miginfocom.swing.MigLayout;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * The SidesPage class represents the page where the user can select sides to add to their order.
 * This page includes checkboxes for different sides (e.g., Bread Sticks, Bread Stick Bites, Big Chocolate Chip Cookie),
 * along with increment buttons to select the quantity of each item. The user can submit their selection or cancel to return
 * to the main menu.
 * <p>
 * The page uses MigLayout to organize components and includes event listeners to toggle the visibility of increment buttons
 * based on the user's selection.
 * </p>
 */
public class SidesPage {
    private JPanel contentPanel;
    private CustomerView cView;

    /**
     * Constructs a SidesPage with the specified CustomerView.
     * The constructor initializes the CustomerView object used for navigation and the content panel.
     *
     * @param cView the CustomerView object used for page navigation and accessing the content panel
     */
    public SidesPage(CustomerView cView) {
        this.cView = cView;
        contentPanel = cView.contentPanel;
    }

    /**
     * Returns the panel representing the sides selection page.
     * This method creates the layout of the page, including checkboxes for various side options,
     * increment buttons for selecting quantities, and action listeners for submitting or canceling the order.
     *
     * @return the JPanel containing the sides selection page
     */
    public JPanel returnPage(){
        JPanel panel = new JPanel(new MigLayout());

        // Creating labels and checkboxes for side options
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

        // Initially setting the increment panels as invisible
        bSticksIncPanel.setVisible(false);
        bSBitesIncPanel.setVisible(false);
        cookieIncPanel.setVisible(false);

        // Adding action listeners to show/hide increment panels based on checkbox selection
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

        // Adding components to the panel
        panel.add(title, "cell 0 0");
        panel.add(bSticksBox, "cell 0 1");
        panel.add(bSticksIncPanel, "cell 1 1");
        panel.add(bSBitesBox, "cell 0 2");
        panel.add(bSBitesIncPanel, "cell 1 2");
        panel.add(cookieBox, "cell 0 3");
        panel.add(cookieIncPanel, "cell 1 3");

        // Creating and adding submit and cancel buttons
        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");
        panel.add(submitBtn, "cell 0 4");
        panel.add(cancelBtn, "cell 1 4");

        // Action listener for the submit button to add selected sides to the receipt
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validForm = true;
                Receipt receipt = cView.view.controller.getReceipt();
                ArrayList<MenuItem> menuItemAr = receipt.getMenuItemAr();

                // Adding selected sides to the receipt with their respective quantities and prices
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

                // Updating the receipt with the selected items
                receipt.setMenuItemAr(menuItemAr);

                // Returning to the MenuPage after submission
                cView.switchPage("MenuPage", cView);
            }
        });

        // Action listener for the cancel button to return to the MenuPage without submitting
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("MenuPage", cView);
            }
        });

        return panel;
    }
}
