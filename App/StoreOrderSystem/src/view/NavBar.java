package view;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavBar {
    private JPanel panel;

    // Passes the contentPanel to switch between pages
    public NavBar(JPanel contentPanel) {
        panel = buildGuestBar(contentPanel);
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

        // Swaps page to home when home button is pressed
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "HomePage");
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "MenuPage");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "LoginPage");
            }
        });
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "CartPage");
            }
        });
        return guestPanel;
    }

    private JPanel buildCustomerBar(JPanel contentPanel) {
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new MigLayout());

        JButton menuButton = new JButton("Menu");
        JButton cartButton = new JButton("Order Cart");
        JButton homeButton = new JButton("Home");

        customerPanel.add(menuButton, "cell 0 0, growx");
        customerPanel.add(cartButton, "cell 1 0, growx");
        customerPanel.add(new JTextField("User"));
        customerPanel.add(homeButton, "cell 3 0, growx");

        // Add action listeners to switch between Home and Menu
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "HomePage");
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "MenuPage");
            }
        });
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "pages.CartPage");
            }
        });
        return customerPanel;
    }


    // Method to return the panel
    public JPanel displayNavBar() {
        return panel;
    }
}
