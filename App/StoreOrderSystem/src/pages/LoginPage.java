package pages;

import model.*;
import net.miginfocom.swing.MigLayout;
import view.CustomerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static CustomerView cView;
    public LoginPage(CustomerView cView) {
        this.cView = cView;
    }

    public static JPanel returnPage() {
        // Main content panel with CardLayout
        JPanel contentPanel = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();

        // Login Page Panel
        JPanel loginPanel = new JPanel(new MigLayout());

        JLabel phonenumber_ID = new JLabel("* Enter Phone Number or Employee ID");
        JTextField phoneNumberField = new JTextField();
        JLabel passwordLabel = new JLabel("* Enter Password");
        JTextField passwordField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension((int) (screenSize.width * 0.1), (int) (screenSize.height * 0.02)));
        passwordField.setPreferredSize(new Dimension((int) (screenSize.width * 0.1), (int) (screenSize.height * 0.02)));
        loginPanel.add(phonenumber_ID, "cell 0 0, growx");
        loginPanel.add(phoneNumberField, "cell 0 1, growx");
        loginPanel.add(passwordLabel, "cell 0 2, growx");
        loginPanel.add(passwordField, "cell 0 3, growx");

        JButton loginSubmitBtn = new JButton("Submit");
        loginPanel.add(loginSubmitBtn, "cell 0 4, alignx center");

        JLabel signupPrompt = new JLabel("Don't have an account?");
        JButton signupBtn = new JButton("Sign up");
        //loginPanel.add(signupPrompt, "cell 0 5, alignx center");
        //loginPanel.add(signupBtn, "cell 0 6, alignx center");

        // Signup Page Panel
        JPanel signupPanel = new JPanel(new MigLayout());

        JLabel firstNameLabel = new JLabel("* First Name");
        JTextField firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("* Last Name");
        JTextField lastNameField = new JTextField();

        JLabel phoneLabel = new JLabel("* Phone Number");
        JTextField phoneField = new JTextField();

        JLabel passwordSignupLabel = new JLabel("* Password");
        JTextField passwordSignupField = new JTextField();

        JLabel confirmPasswordLabel = new JLabel("* Confirm Password");
        JTextField confirmPasswordField = new JTextField();

        JLabel addressLabel = new JLabel("Street Address");
        JTextField addressField = new JTextField(20);

        JLabel aptLabel = new JLabel("Apartment #");
        JTextField aptField = new JTextField(8);

        JLabel cityLabel = new JLabel("City");
        JTextField cityField = new JTextField(10);

        JLabel stateLabel = new JLabel("State");
        JTextField stateField = new JTextField(4);

        JLabel zipLabel = new JLabel("Zip Code");
        JTextField zipField = new JTextField(8);


        JPanel signupLPanel = new JPanel(new MigLayout());
        JPanel signupRPanel = new JPanel(new MigLayout());
        signupLPanel.add(firstNameLabel, "cell 0 0, growx");
        signupLPanel.add(firstNameField, "cell 0 1, growx");
        signupLPanel.add(lastNameLabel, "cell 0 2, growx");
        signupLPanel.add(lastNameField, "cell 0 3, growx");
        signupLPanel.add(phoneLabel, "cell 0 4, growx");
        signupLPanel.add(phoneField, "cell 0 5, growx");
        signupLPanel.add(passwordSignupLabel, "cell 0 6, growx");
        signupLPanel.add(passwordSignupField, "cell 0 7, growx");
        signupLPanel.add(confirmPasswordLabel, "cell 0 8, growx");
        signupLPanel.add(confirmPasswordField, "cell 0 9, growx");
        signupRPanel.add(addressLabel, "cell 0 0,span 4, growx");
        signupRPanel.add(addressField, "cell 0 1,span 4, growx");
        signupRPanel.add(aptLabel, "cell 0 2, growx");
        signupRPanel.add(aptField, "cell 0 3, growx");
        signupRPanel.add(cityLabel, "cell 1 2, growx");
        signupRPanel.add(cityField, "cell 1 3, growx");
        signupRPanel.add(stateLabel, "cell 2 2, growx");
        signupRPanel.add(stateField, "cell 2 3, growx");
        signupRPanel.add(zipLabel, "cell 3 2, growx");
        signupRPanel.add(zipField, "cell 3 3, growx");

        JLabel signupHeader = new JLabel("Sign Up");
        signupHeader.setFont(new Font(signupHeader.getFont().getName(), Font.BOLD, 36));

        signupPanel.add(signupHeader, "cell 0 0, alignx center");
        signupPanel.add(signupLPanel, "cell 0 1, growx");
        signupPanel.add(signupRPanel, "cell 1 1, growx");

        JButton backToLoginBtn = new JButton("Back to Login");
        signupPanel.add(backToLoginBtn, "cell 0 20, alignx center");

        // Adding panels to CardLayout
        contentPanel.add(loginPanel, "LoginPage");
        contentPanel.add(signupPanel, "SignupPage");

        loginSubmitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneIdBox = phoneNumberField.getText();
                if(phoneNumberField.getText().length() == 10) {
                    phoneIdBox = "(" + phoneNumberField.getText().substring(0, 3) + ") " + phoneNumberField.getText().substring(3, 6) + " " + phoneNumberField.getText().substring(6);
                }
                String passwordBox = passwordField.getText();

                int accountType = cView.view.controller.verifyLogin(phoneIdBox, passwordBox);
                if(accountType == 0) {


                    Receipt receipt = cView.view.controller.getReceipt();
                    Account user = cView.view.controller.getCurrentUser();
                    if(user instanceof Customer){
                        receipt.setCustomerName(((Customer) user).getName());
                        receipt.setPhoneNumber(((Customer) user).getPhone());
                        receipt.setAddress(((Customer) user).getAddress());
                    }

                    cView.isLoggedIn = true;
                    cView.view.swapView(1);
                    cView.switchPage("HomePage",cView);
                }
                if(accountType == 1) {
                    cView.isLoggedIn = true;
                    cView.view.swapView(1);
                    cView.switchPage("HomePage",cView);
                }
            }
        });

        // Button actions
        signupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "SignupPage");
            }
        });

        backToLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentPanel, "LoginPage");
            }
        });

        return contentPanel;
    }
}
