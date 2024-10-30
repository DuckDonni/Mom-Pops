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
    public JPanel buildGuestBar(JPanel contentPanel) {
        JPanel guestPanel = new JPanel();
        guestPanel.setLayout(new MigLayout());

        JButton aboutUsButton = new JButton("About Us");
        JButton menuButton = new JButton("Menu");
        JButton loginButton = new JButton("Login/Sign-up");
        JButton cartButton = new JButton("Order Cart");
        JButton homeButton = new JButton("Home");

        guestPanel.add(aboutUsButton, "cell 0 0, growx");
        guestPanel.add(menuButton, "cell 1 0, growx");
        guestPanel.add(loginButton, "cell 2 0, growx");
        guestPanel.add(cartButton, "cell 3 0, growx");
        guestPanel.add(homeButton, "cell 4 0, growx");

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
        return guestPanel;
    }

    public JPanel buildCustomerBar(JPanel contentPanel) {
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new MigLayout());

        JButton aboutUsButton = new JButton("About Us");
        JButton menuButton = new JButton("Menu");
        JButton orderHistButton = new JButton("Order History");
        JButton profileButtoner = new JButton("Profile");
        JButton cartButton = new JButton("Order Cart");
        JButton homeButton = new JButton("Home");

        customerPanel.add(aboutUsButton, "cell 0 0, growx");
        customerPanel.add(menuButton, "cell 1 0, growx");
        customerPanel.add(orderHistButton, "cell 2 0, growx");
        customerPanel.add(profileButtoner, "cell 3 0, growx");
        customerPanel.add(cartButton, "cell 4 0, growx");
        customerPanel.add(homeButton, "cell 5 0, growx");

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

        return customerPanel;
    }


    // Method to return the panel
    public JPanel displayNavBar() {
        return panel;
    }
}
