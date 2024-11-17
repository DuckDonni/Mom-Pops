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


        System.out.println(updateEmployeeAccount("Test", "BOOOOOOOOOOOG", true));

        System.out.println(updateCustomerAccount("Robert", "Cooke", "(904) 548 8186",
                "(904) 548 8186", "toot toot way", "1472", true));
    }

    public String getOverrideCode() {
        return model.getOverrideCode();
    }

    //verifyLogin

    public void logout() {
        currentUser = null;
    }

    //updateCustomerAccount
    public static boolean updateEmployeeAccount(String username, String password, boolean update) throws IOException {
        return model.getDatabaseManager().updateEmployeeAccount(username, password, update);
    }

    public static boolean updateCustomerAccount(String first, String last, String oldPhone, String newPhone,
                                                String address, String password, boolean update) throws IOException {
        return model.getDatabaseManager().updateCustomerAccount(first, last, oldPhone, newPhone,
                address, password, update);
    }

}