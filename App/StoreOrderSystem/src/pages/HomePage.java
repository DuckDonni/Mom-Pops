package pages;

import model.Receipt;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the Home Page in the application.
 * Allows users to select between Delivery or Carryout order options.
 * Integrates with the CustomerView and Receipt to manage order details.
 */
public class HomePage {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private CustomerView cView;

    /**
     * Constructs a HomePage with the specified CustomerView.
     *
     * @param cView the CustomerView associated with this HomePage
     */
    public HomePage(CustomerView cView) {
        this.cView = cView;
    }

    /**
     * Creates and returns the JPanel representing the Home Page.
     * Includes buttons for selecting Delivery or Carryout order options.
     *
     * @return the JPanel containing the Home Page UI
     */
    public JPanel returnPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fill", "[center]", "[center]"));

        // Create buttons for order type selection
        JButton deliveryBtn = new JButton("Delivery");
        JButton carryOutBtn = new JButton("Carryout");

        // Retrieve the receipt to modify its delivery status
        Receipt receipt = cView.view.controller.getReceipt();

        // Add buttons to the panel
        panel.add(deliveryBtn, "cell 0 0, wrap, align right");
        panel.add(carryOutBtn, "cell 1 0, align left");

        // Action listener for the Delivery button
        deliveryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt.setIsDelivery(true); // Set delivery status
                cView.view.controller.setReceipt(receipt); // Update receipt in controller
                cView.switchPage("MenuPage", cView); // Switch to the Menu Page
            }
        });

        // Action listener for the Carryout button
        carryOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt.setIsDelivery(false); // Set carryout status
                cView.view.controller.setReceipt(receipt); // Update receipt in controller
                cView.switchPage("MenuPage", cView); // Switch to the Menu Page
            }
        });

        return panel;
    }
}
