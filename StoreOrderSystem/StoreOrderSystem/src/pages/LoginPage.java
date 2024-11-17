package pages;

import net.miginfocom.swing.MigLayout;
import view.CustomerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public LoginPage(CustomerView cView) {

    }
    public static JPanel returnPage(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        JLabel phonenumber_ID = new JLabel("* Enter Phone Number or Employee ID");
        JTextField phoneNumberField = new JTextField();
        JLabel passwordLabel = new JLabel("* Enter Phone Number");
        JTextField passwordField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension((int)(screenSize.width*0.1), (int)(screenSize.height*0.02)));
        passwordField.setPreferredSize(new Dimension((int)(screenSize.width*0.1), (int)(screenSize.height*0.02)));
        panel.add(phoneNumberField , "cell 0 1, growx");
        panel.add(phonenumber_ID, "cell 0 0, growx");
        panel.add(passwordLabel , "cell 0 2, growx");
        panel.add(passwordField, "cell 0 3, growx");

        JButton submitBtn = new JButton("Submit");

        panel.add(submitBtn, "cell 0 4, alignx center");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code for login logic
            }
        });


        return panel;
    }
}
