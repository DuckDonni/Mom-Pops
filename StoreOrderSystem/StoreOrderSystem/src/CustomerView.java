import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class CustomerView {
    private JFrame frame;
    private View view;
    private static Dimension screenSize;
    public CustomerView(View view) {
        view = this.view;
        frame = new JFrame("Homepage Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height); // Set frame size to the screen's resolution

        // Establishes layout for miglayout
        frame.setLayout(new MigLayout("fill", "[grow][right]", "[]20[grow]"));

        // Creates a panel to swap pages with Cardlayout
        JPanel contentPanel = new JPanel(new CardLayout());

        // Creates the navbar and passes the contentPanel to allow for page swapping
        NavBar navbar = new NavBar(contentPanel);
        frame.add(navbar.displayNavBar(), "cell 1 0, align right, growx");

        // Creates each instance for pages in the contentPanel
        contentPanel.add(buildHomePage(), "HomePage");
        contentPanel.add(buildMenuPage(), "MenuPage");
        contentPanel.add(buildLoginPage(), "LoginPage");

        // Displays the panel in the main frame
        frame.add(contentPanel, "cell 0 1, span, grow");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }
    public static JPanel buildHomePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JButton("Delivery"), "cell 0 0, growx");
        panel.add(new JButton("Carryout"), "cell 1 0, growx");
        return panel;
    }


    public static JPanel buildMenuPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JButton("Build Pizza"), "cell 0 0, growx");
        panel.add(new JButton("Drinks"), "cell 1 0, growx");
        panel.add(new JButton("Sides"), "cell 2 0, growx");
        return panel;
    }

    public static JPanel buildLoginPage(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        JLabel phonenumber_ID = new JLabel("Enter Phone Number or Employee ID");
        JTextField phoneNumberField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter Phone Number");
        JTextField passwordField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension((int)(screenSize.width*0.1), (int)(screenSize.height*0.02)));
        passwordField.setPreferredSize(new Dimension((int)(screenSize.width*0.1), (int)(screenSize.height*0.02)));
        panel.add(phoneNumberField , "cell 0 1, growx");
        panel.add(phonenumber_ID, "cell 0 0, growx");
        panel.add(passwordLabel , "cell 0 2, growx");
        panel.add(passwordField, "cell 0 3, growx");
        return panel;
    }

}
