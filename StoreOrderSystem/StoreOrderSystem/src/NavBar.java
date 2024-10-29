import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class NavBar {
    private JPanel panel;

    // Default constructor
    public NavBar() {
    }

    // Constructor with role
    public NavBar(int role) {
        switch (role) {
            case 0:
                panel = buildGuestBar();  // Store the built guest navbar in panel
                break;
            case 1:
                // You can add code here for other roles
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    // Build Guest Navbar
    public JPanel buildGuestBar() {
        JPanel guestPanel = new JPanel();
        guestPanel.setLayout(new MigLayout());

        guestPanel.add(new JButton("About Us"), "cell 0 0, growx");
        guestPanel.add(new JButton("Menu"), "cell 1 0, growx");
        guestPanel.add(new JButton("Login/Sign-up"), "cell 2 0, growx");
        guestPanel.add(new JButton("Order Cart"), "cell 3 0, growx");
        guestPanel.add(new JButton("Home"), "cell 4 0, growx");

        return guestPanel;  // Return the constructed guestPanel
    }

    // Method to return the panel
    public JPanel displayNavBar() {
        return panel;  // Return the panel that was set in the constructor
    }
}
