package view;

import net.miginfocom.swing.MigLayout;
import pages.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerView {
    private static JFrame frame;
    private static Dimension screenSize;
    public static JPanel contentPanel;
    public static View view;
    public static JPanel shiftPanel;
    public static boolean isLoggedIn = false;
    public CustomerView(View view) {
        this.view = view;
        frame = new JFrame("Homepage Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        shiftPanel = buildHomePage(this);
        frame.setSize(screenSize.width, screenSize.height); // Set frame size to the screen's resolution
        // Establishes layout for miglayout
        frame.setLayout(new MigLayout("fill", "[grow][right]", "[]20[grow]"));
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(buildHomePage(this), "HomePage");
        contentPanel.add(buildMenuPage(this), "MenuPage");
        contentPanel.add(buildPizzaPage(this), "PizzaPage");
        contentPanel.add(buildDrinksPage(this), "DrinksPage");
        contentPanel.add(buildSidesPage(this), "SidesPage");
        contentPanel.add(buildCartPage(this), "CartPage");
        contentPanel.add(buildLoginPage(this), "LoginPage");
        contentPanel.add(shiftPanel, "CurrentPanel");
        switchPage("HomePage", this);

        // Creates the navbar and passes the contentPanel to allow for page swapping
        //NavBar navbar = new NavBar(contentPanel);
        //frame.add(navbar.displayNavBar(), "cell 1 0, align right, growx");


        // Displays the panel in the main frame
        frame.add(contentPanel, "cell 0 1, span, grow");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }
    public static JPanel buildHomePage(CustomerView cView) {
        return new HomePage(cView).returnPage();
    }


    public static JPanel buildMenuPage(CustomerView cView){
        return new MenuPage(cView).returnPage();
    }

    public static JPanel buildCartPage(CustomerView cView) {

        return new CartPage(cView).returnPage();
    }

    public static JPanel buildLoginPage(CustomerView cView){
        return new LoginPage(cView).returnPage();
    }

    public static JPanel buildPizzaPage(CustomerView cView){
        return new PizzaPage(cView).returnPage();
    }
    public static void buildEditPizzaPage(CustomerView cView,Pizza pizza){
        Frame popupFrame = new JFrame();
        JPanel popupPanel = new PizzaPage(cView).returnPage(pizza);
    }
    public static JPanel buildDrinksPage(CustomerView cView){
        return new DrinksPage(cView).returnPage();
    }

    public static JPanel buildSidesPage(CustomerView cView){
        return new SidesPage(cView).returnPage();
    }


    public static void switchPage(String pageName, CustomerView cView) {
        contentPanel.removeAll();
        contentPanel.add(buildHomePage(cView), "HomePage");
        contentPanel.add(buildMenuPage(cView), "MenuPage");
        contentPanel.add(buildLoginPage(cView), "LoginPage");
        contentPanel.add(buildCartPage(cView), "CartPage");
        contentPanel.add(buildPizzaPage(cView), "PizzaPage");
        contentPanel.add(buildDrinksPage(cView), "DrinksPage");
        contentPanel.add(buildSidesPage(cView), "SidesPage");
        contentPanel.add(shiftPanel, "CurrentPanel");
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, pageName);
    }
    public void updateNavBar(int type) {

        // Remove the current navbar from the frame
        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JPanel && "navbar".equals(component.getName())) {
                frame.getContentPane().remove(component);
                break;
            }
        }

        // Create a new NavBar based on the type
        NavBar navbar = new NavBar(contentPanel, type, this);
        JPanel updatedNavBar = navbar.displayNavBar();
        updatedNavBar.setName("navbar"); // Mark it for easy identification
        frame.add(updatedNavBar, "cell 1 0, align right, growx");

        // Revalidate and repaint the frame to apply changes
        frame.revalidate();
        frame.repaint();
    }

    public void setShiftView(JPanel newPanel){
        shiftPanel = newPanel;
    }

}
