package ControllerClasses;

import ModelClasses.Model;

public class Controller {
    private static Model model;
    public static void main(String [] args) {
        model = new Model();
        System.out.println(verifyLogin("0001", "password"));
    }

    // !! wrong return type
    static public boolean verifyLogin(String username, String password) {
        // !! needs to diferentiate between customers and Employees by itself
        // return model.getDatabaseManager().validateCustomerAccount(username, password); // customer test
        return model.getDatabaseManager().validateEmployeeAccount(username, password); // Employee test
    }
}
