package controller;

import model.*;
import view.*;

import java.io.IOException;

/**
 * The Controller class acts as a mediator between the Model and the View,
 * coordinating actions and data flow in the application.
 */
public class Controller {

    private static Model model;
    private View view;
    private static Account currentUser;
    private static Receipt receipt;

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs during initialization.
     */
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        model = new Model();
        receipt = new Receipt();
        currentUser = null;
        System.out.println("running");
        controller.buildView();

        System.out.println(verifyLogin("(904) 548 8186", "1472"));

        System.out.println(currentUser.getClass());
    }

    /**
     * Initializes and displays the application's View.
     */
    public void buildView() {
        this.view = new View(this);
        view.swapView(0);
    }

    /**
     * Gets the currently logged-in user.
     *
     * @return The current {@link Account} or null if no user is logged in.
     */
    public static Account getCurrentUser() {
        return currentUser;
    }

    /**
     * Retrieves the override code from the Model.
     *
     * @return The override code as a String.
     */
    public String getOverrideCode() {
        return model.getOverrideCode();
    }

    /**
     * Logs out the currently logged-in user.
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Retrieves the current Receipt object.
     *
     * @return The current {@link Receipt}.
     */
    public Receipt getReceipt() {
        return receipt;
    }

    /**
     * Sets a new Receipt object.
     *
     * @param receipt The new {@link Receipt} object to set.
     */
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    /**
     * Updates an employee account with new credentials or settings.
     *
     * @param username The username of the employee.
     * @param password The password of the employee.
     * @param update   Whether to update the account or not.
     * @return True if the update was successful, false otherwise.
     * @throws IOException If an I/O error occurs during the update.
     */
    public static boolean updateEmployeeAccount(String username, String password, boolean update) throws IOException {
        return model.getDatabaseManager().updateEmployeeAccount(username, password, update);
    }

    /**
     * Updates a customer account with new details.
     *
     * @param first    The first name of the customer.
     * @param last     The last name of the customer.
     * @param oldPhone The current phone number of the customer.
     * @param newPhone The new phone number of the customer.
     * @param address  The new address of the customer.
     * @param password The new password for the account.
     * @param update   Whether to update the account or not.
     * @return True if the update was successful, false otherwise.
     * @throws IOException If an I/O error occurs during the update.
     */
    public static boolean updateCustomerAccount(String first, String last, String oldPhone, String newPhone,
                                                String address, String password, boolean update) throws IOException {
        return model.getDatabaseManager().updateCustomerAccount(first, last, oldPhone, newPhone,
                address, password, update);
    }

    /**
     * Verifies login credentials for either a customer or employee account.
     *
     * @param username The username (or phone number for customers).
     * @param password The password associated with the account.
     * @return An integer indicating the type of account:
     *         -1 if login failed, 0 if it's a customer account, 1 if it's an employee account.
     */
    public static int verifyLogin(String username, String password) {
        Account account;
        if (username.contains("(")) {
            account = model.getDatabaseManager().validateCustomerAccount(username, password);
        } else {
            account = model.getDatabaseManager().validateEmployeeAccount(username, password);
        }

        if (account == null) {
            return -1;
        } else if (account instanceof Employee) {
            currentUser = account;
            return 1;
        } else {
            currentUser = account;
            return 0;
        }
    }

}
