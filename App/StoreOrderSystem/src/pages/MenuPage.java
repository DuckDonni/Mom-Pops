package pages;

import net.miginfocom.swing.MigLayout;
import view.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MenuPage class represents the main menu of the application, providing options for the user to navigate
 * to different sections such as building a pizza, ordering drinks, or selecting sides.
 * This page presents three buttons: one for each menu option, and allows users to select an option and
 * navigate to the appropriate page.
 * <p>
 * This class uses MigLayout for layout management and interacts with the CustomerView to handle page navigation.
 * </p>
 */
public class MenuPage {
    private CustomerView cView;

    /**
     * Constructs a MenuPage with the specified CustomerView.
     * The constructor initializes the CustomerView object used for navigation to different pages.
     *
     * @param cView the CustomerView object used for navigation to other pages
     */
    public MenuPage(CustomerView cView){
        this.cView = cView;
    }

    /**
     * Returns the main panel containing the menu options.
     * The panel includes three buttons for navigating to the PizzaPage, DrinksPage, and SidesPage.
     * Each button has an action listener that switches the page when clicked.
     *
     * @return the JPanel containing the menu with navigation buttons
     */
    public JPanel returnPage(){
        // Creating a panel with MigLayout for arranging buttons
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        // Creating buttons for pizza, drinks, and sides sections
        JButton pizzaButton = new JButton("Build Pizza");
        JButton drinksButton = new JButton("Drinks");
        JButton sidesButton = new JButton("Sides");

        // Adding buttons to the panel
        panel.add(pizzaButton, "cell 0 0, growx");
        panel.add(drinksButton, "cell 1 0, growx");
        panel.add(sidesButton, "cell 2 0, growx");

        // ActionListener for the pizza button, switches to PizzaPage
        pizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("PizzaPage", cView);
            }
        });

        // ActionListener for the drinks button, switches to DrinksPage
        drinksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("DrinksPage", cView);
            }
        });

        // ActionListener for the sides button, switches to SidesPage
        sidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("SidesPage", cView);
            }
        });

        return panel;
    }
}
