package view;

import controller.*;

/**
 * The View class is responsible for managing and displaying different views in the application.
 * It initializes the customer and staff views and provides functionality to swap between them based on user interactions.
 */
public class View {
    private CustomerView customerView;
    private StaffView staffView;
    public static Controller controller;

    /**
     * Constructor that initializes the View object, setting up the controller and creating instances
     * of CustomerView and StaffView.
     *
     * @param controller the controller object that manages the business logic
     */
    public View(Controller controller) {
        this.controller = controller;
        customerView = new CustomerView(this);
        staffView = new StaffView();
    }

    /**
     * Swaps between the customer view and staff view based on the specified option.
     *
     * @param option an integer that determines which view to display:
     *               - 0: Show the customer view and update its navigation bar.
     *               - 1: Show the staff view and update the customer view's navigation bar accordingly.
     */
    public void swapView(int option) {
        if (option == 0) {
            customerView.getFrame().setVisible(true); // Show customer view
            customerView.updateNavBar(0);
        } else if (option == 1) {
            customerView.getFrame().setVisible(true); // Hide customer view
            customerView.updateNavBar(1);
        }
    }

    // Uncomment to run a test scenario of initializing the view with a controller and toggling the view
    // public static void main(String[] args) {
    //     View view = new View(new Controller());
    //     view.swapView(0); // Initially shows view.CustomerView (you can toggle this)
    // }
}
