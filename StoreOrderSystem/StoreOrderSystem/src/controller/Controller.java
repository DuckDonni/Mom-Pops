package controller;

import model.*;
import view.*;

import java.io.IOException;

public class Controller {

    private static Model model;
    private static View view;
    private static Account currentUser;

    public static void main(String [] args) throws IOException {
        model = new Model();
        //view = new View();
        currentUser = null;

        updateEmployeeAccount("Test", "test Password 2 electric boogaloo");
        updateCustomerAccount("Robert", "Cooke", "(904) 548 8186",
                "(904) 548 8186", "toot toot way", "1472");

    }

    public String getOverrideCode() {
        return model.getOverrideCode();
    }

    //verifyLogin

    public void logout() {
        currentUser = null;
    }

    //updateCustomerAccount
    public static boolean updateEmployeeAccount(String username, String password) throws IOException {
        model.getDatabaseManager().updateEmployeeAccount(username, password);

        return true; // !! <-
    }

    public static boolean updateCustomerAccount(String first, String last, String oldPhone, String newPhone, String address, String password) throws IOException {
        model.getDatabaseManager().updateCustomerAccount(first, last, oldPhone, newPhone, address, password);

        return true; // !! <-
    }

}