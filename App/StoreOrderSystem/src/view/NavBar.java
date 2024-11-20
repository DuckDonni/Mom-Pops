package view;

import javax.swing.*;

import model.Account;
import model.Customer;
import model.Employee;
import model.Receipt;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavBar {
    private JPanel panel;
    private CustomerView cView;
    public NavBar(JPanel contentPanel, int type, CustomerView cView) {
        if (type == 0) {
            panel = buildGuestBar(contentPanel);
        } else if (type == 1) {
            panel = buildCustomerBar(contentPanel);
        }
        this.cView = cView;
    }

    // Build Guest Navbar with action listeners for navigation
    private JPanel buildGuestBar(JPanel contentPanel) {
        JPanel guestPanel = new JPanel();
        guestPanel.setLayout(new MigLayout());

        JButton menuButton = new JButton("Menu");
        JButton loginButton = new JButton("Login/Sign-up");
        JButton cartButton = new JButton("Order Cart");
        JButton homeButton = new JButton("Home");

        guestPanel.add(menuButton, "cell 0 0, growx");
        guestPanel.add(loginButton, "cell 1 0, growx");
        guestPanel.add(cartButton, "cell 2 0, growx");
        guestPanel.add(homeButton, "cell 3 0, growx");

        // Add action listeners
        homeButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "HomePage");
        });

        menuButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "MenuPage");
        });

        loginButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "LoginPage");
        });

        cartButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "CartPage");
        });


        return guestPanel;
    }

    // Build Customer Navbar with action listeners for navigation
    private JPanel buildCustomerBar(JPanel contentPanel) {
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new MigLayout());

        JButton menuButton = new JButton("Menu");
        JButton cartButton = new JButton("Order Cart");
        JButton homeButton = new JButton("Home");
        JButton logoutButton = new JButton("Logout");

        String userName = "";
        Account currentUser = cView.view.controller.getCurrentUser();
        if (currentUser instanceof Customer) {
            userName = ((Customer) currentUser).getName(); // Safely cast and retrieve name
        }
        if(currentUser instanceof Employee){
            userName = ((Employee) currentUser).getUsername();
        }
        JLabel usernameLabel = new JLabel(userName);

        customerPanel.add(menuButton, "cell 0 0, growx");
        customerPanel.add(cartButton, "cell 1 0, growx");
        customerPanel.add(homeButton, "cell 2 0, growx");
        customerPanel.add(usernameLabel, "cell 3 0, growx");
        customerPanel.add(logoutButton, "cell 4 0, growx");

        // Add action listeners
        homeButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "HomePage");
        });

        menuButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "MenuPage");
        });

        cartButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "CartPage");
        });

        logoutButton.addActionListener(e -> {
            cView.view.swapView(0);
            Receipt receipt = new Receipt();
            cView.view.controller.setReceipt(receipt);
            cView.switchPage("LoginPage",cView);
            CardLayout cl = (CardLayout) contentPanel.getLayout();
            cl.show(contentPanel, "LoginPage");
        });

        return customerPanel;
    }

    // Method to return the panel
    public JPanel displayNavBar() {
        return panel;
    }
}
