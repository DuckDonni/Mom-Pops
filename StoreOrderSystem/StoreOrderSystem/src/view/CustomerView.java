package view;

import net.miginfocom.swing.MigLayout;
import pages.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerView {
    private static JFrame frame;
    private static Dimension screenSize;
    public static JPanel contentPanel;
    public CustomerView() {

        frame = new JFrame("Homepage Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
        switchPage("HomePage", this);
        // Creates the navbar and passes the contentPanel to allow for page swapping
        NavBar navbar = new NavBar(contentPanel);
        frame.add(navbar.displayNavBar(), "cell 1 0, align right, growx");


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

        return new CartPage().returnPage();
    }

    public static JPanel buildLoginPage(CustomerView cView){
        return new LoginPage(cView).returnPage();
    }

    public static JPanel buildPizzaPage(CustomerView cView){
        return new PizzaPage(cView).returnPage();
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
        contentPanel.add(buildCartPage(cView), "pages.CartPage");
        contentPanel.add(buildPizzaPage(cView), "PizzaPage");
        contentPanel.add(buildDrinksPage(cView), "DrinksPage");
        contentPanel.add(buildSidesPage(cView), "SidesPage");

        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, pageName);
    }
}
