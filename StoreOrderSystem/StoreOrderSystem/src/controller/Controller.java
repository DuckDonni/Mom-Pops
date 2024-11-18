package controller;

import model.*;
import view.*;

import java.io.IOException;

public class Controller {

    private static Model model;
    private View view;
    private static Account currentUser;
    private static Receipt receipt;
    public static void main(String [] args) throws IOException {
        Controller controller = new Controller();
        model = new Model();
        receipt = new Receipt();
        currentUser = null;
        controller.buildView();


        //System.out.println(verifyLogin("(904) 548 8186", "1472"));

        //System.out.println(currentUser.getClass());
    }
    public void buildView(){
        this.view = new View(this);
        view.swapView(0);
    }

    public static Account getCurrentUser() {
        return currentUser;
    }

    public String getOverrideCode() {
        return model.getOverrideCode();
    }

    public void logout() {
        currentUser = null;
    }

    public Receipt getReceipt() {
        return receipt;
    }
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
        System.out.println(receipt.getPizzaAr().get(0).getCrustSize());
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

   public static int verifyLogin (String username, String password) {
        // check to see if emp or cust
       Account account;
       if(username.contains("(")) {
           account = model.getDatabaseManager().validateCustomerAccount(username, password);
       } else {
           account = model.getDatabaseManager().validateEmployeeAccount(username, password);
       }

       if(account == null) {
           return -1;
       } else if(account instanceof Employee) {
           currentUser = account;
           return 1;
       } else {
           currentUser = account;
           return 0;
       }
   }
   
}