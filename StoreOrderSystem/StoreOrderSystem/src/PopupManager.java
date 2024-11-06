import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PopupManager {

    private JFrame frame;

    public PopupManager() {
        frame = new JFrame();
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
    }

    public JFrame buildEditCustInfo() {
        frame.getContentPane().removeAll();
        frame.setTitle("Edit Profile Information");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx", "[grow,fill][grow,fill][grow,fill]", "[][][][][][][]"));
        JLabel title = new JLabel("Customer Information");
        panel.add(title, "cell 0 0");
        JLabel fNamePrompt = new JLabel("First Name:");
        JTextField fNameField = new JTextField(10);

        JLabel lNamePrompt = new JLabel("Last Name:");
        JTextField lNameField = new JTextField(10);

        JLabel addressPrompt = new JLabel("Address:");
        JTextField addressField = new JTextField(15);

        JLabel bldRoomPrompt = new JLabel("Bld/Room #:");
        JTextField bldRoomField = new JTextField(5);

        JLabel zipPrompt = new JLabel("Zip Code:");
        JTextField zipField = new JTextField(5);

        JLabel statePrompt = new JLabel("State:");
        JTextField stateField = new JTextField(3);

        JLabel phoneNumberPrompt = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField(12);

        // Adding components in original layout with custom sizes for text fields
        panel.add(fNamePrompt, "cell 0 1, alignx left");
        panel.add(fNameField, "cell 0 2, growx");

        panel.add(lNamePrompt, "cell 1 1, alignx left");
        panel.add(lNameField, "cell 1 2, growx");

        panel.add(addressPrompt, "cell 0 3, alignx left");
        panel.add(addressField, "cell 0 4 2 1, growx");  // Span across 2 columns

        panel.add(bldRoomPrompt, "cell 2 3, alignx left");
        panel.add(bldRoomField, "cell 2 4, growx");

        panel.add(zipPrompt, "cell 0 5, alignx left");
        panel.add(zipField, "cell 0 6, growx");

        panel.add(statePrompt, "cell 1 5, alignx left");
        panel.add(stateField, "cell 1 6, growx");

        panel.add(phoneNumberPrompt, "cell 2 5, alignx left");
        panel.add(phoneNumberField, "cell 2 6, growx");

        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }
    public JFrame buildEditCustInfo(String fName,String lName, String address, String phoneNumber) {
        frame.getContentPane().removeAll();
        frame.setTitle("Edit Profile Information");
        JPanel panel = new JPanel();
        JLabel title = new JLabel("Customer Information");
        panel.add(title,"cell 0 0");
        // Address will be broke up as (street address, bld number, state, zip)
        String[] addressBreakup = address.split(",");

        panel.setLayout(new MigLayout("fillx", "[grow,fill][grow,fill][grow,fill]", "[][][][][][][]"));

        JLabel fNamePrompt = new JLabel("First Name:");
        JTextField fNameField = new JTextField(fName,10);

        JLabel lNamePrompt = new JLabel("Last Name:");
        JTextField lNameField = new JTextField(lName,10);

        JLabel addressPrompt = new JLabel("Address:");
        JTextField addressField = new JTextField(addressBreakup[0],15);

        JLabel bldRoomPrompt = new JLabel("Bld/Room #:");
        JTextField bldRoomField = new JTextField(addressBreakup[1], 5);

        JLabel zipPrompt = new JLabel("Zip Code:");
        JTextField zipField = new JTextField(addressBreakup[2], 5);

        JLabel statePrompt = new JLabel("State:");
        JTextField stateField = new JTextField(addressBreakup[3],3);

        JLabel phoneNumberPrompt = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField(phoneNumber, 12);

        // Adding components in original layout with custom sizes for text fields
        panel.add(fNamePrompt, "cell 0 1, alignx left");
        panel.add(fNameField, "cell 0 2, growx");

        panel.add(lNamePrompt, "cell 1 1, alignx left");
        panel.add(lNameField, "cell 1 2, growx");

        panel.add(addressPrompt, "cell 0 3, alignx left");
        panel.add(addressField, "cell 0 4 2 1, growx");  // Span across 2 columns

        panel.add(bldRoomPrompt, "cell 2 3, alignx left");
        panel.add(bldRoomField, "cell 2 4, growx");

        panel.add(zipPrompt, "cell 0 5, alignx left");
        panel.add(zipField, "cell 0 6, growx");

        panel.add(statePrompt, "cell 1 5, alignx left");
        panel.add(stateField, "cell 1 6, growx");

        panel.add(phoneNumberPrompt, "cell 2 5, alignx left");
        panel.add(phoneNumberField, "cell 2 6, growx");

        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }


    public JFrame buildEditOrderTime() {
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new MigLayout());
        JLabel title = new JLabel("Order Details");
        panel.add(title, "cell 0 0");
        JLabel pickupPrompt = new JLabel("Pickup");
        JRadioButton pickupOption = new JRadioButton();

        JLabel deliverPrompt = new JLabel("Deliver");
        JRadioButton deliverOption = new JRadioButton();

        ButtonGroup deliveryBtnGroup = new ButtonGroup();
        deliveryBtnGroup.add(pickupOption);
        deliveryBtnGroup.add(deliverOption);

        panel.add(pickupPrompt, "cell 0 1, alignx left");
        panel.add(pickupOption, "cell 1 1, center");
        panel.add(deliverPrompt, "cell 2 1, alignx left");
        panel.add(deliverOption, "cell 3 1, center");

        JLabel asapPrompt = new JLabel("ASAP");
        JRadioButton asapOption = new JRadioButton();
        JLabel timePrompt = new JLabel("Set Time");
        JRadioButton timeOption = new JRadioButton();

        ButtonGroup timeBtnGroup = new ButtonGroup();
        timeBtnGroup.add(asapOption);
        timeBtnGroup.add(timeOption);

        panel.add(asapPrompt, "cell 0 2, alignx left");
        panel.add(asapOption, "cell 1 2, center");
        panel.add(timePrompt, "cell 2 2, alignx left");
        panel.add(timeOption, "cell 3 2, center");

        // Create the timePanel with fixed width constraints
        JPanel timePanel = new JPanel(new MigLayout("insets 0, gapy 0", "[grow,fill][fill]", "[][]")); // No gap between rows

        JLabel prefTimePrompt = new JLabel("Preferred Time:");
        JTextField prefTimeField = new JTextField(10);

        // Add `prefTimePrompt` on top of `prefTimeField`
        timePanel.add(prefTimePrompt, "cell 0 0, alignx left, wrap");
        timePanel.add(prefTimeField, "cell 0 1, growx");

        // Setting up AM/PM options in a vertical panel

        JPanel timeBtnPanel = new JPanel(new MigLayout("insets 0, gapy 0", "[]", "[][]")); // Stack vertically with no additional gap
        JLabel amPrompt = new JLabel("AM");
        JRadioButton amOption = new JRadioButton();
        JLabel pmPrompt = new JLabel("PM");
        JRadioButton pmOption = new JRadioButton();
        ButtonGroup amPmBtnGroup = new ButtonGroup();
        amPmBtnGroup.add(amOption);
        amPmBtnGroup.add(pmOption);
        // Stack the AM and PM radio buttons vertically in `timeBtnPanel`
        timeBtnPanel.add(amPrompt, "alignx left");
        timeBtnPanel.add(amOption, "wrap"); // Wrap to move to the next row
        timeBtnPanel.add(pmPrompt, "alignx left");
        timeBtnPanel.add(pmOption);

        // Place `timeBtnPanel` in alignment with the text field, in the same row as the text field
        timePanel.add(timeBtnPanel, "cell 1 1, aligny top"); // Align top to the text field without affecting the prompt height

        timePanel.setVisible(false);
        panel.add(timePanel, "cell 0 3, spanx"); // Add timePanel to row 2 of the main panel without affecting other rows

        asapOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePanel.setVisible(timeOption.isSelected());
            }
        });
        timeOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePanel.setVisible(timeOption.isSelected());
            }
        });
        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }

    public JFrame buildEditOrderTime(Boolean isDelivery, Date time){
        frame.getContentPane().removeAll();

        JPanel panel = new JPanel(new MigLayout());
        JLabel title = new JLabel("Order Details");
        panel.add(title,"cell 0 0");
        JLabel pickupPrompt = new JLabel("Pickup");
        JRadioButton pickupOption = new JRadioButton();

        JLabel deliverPrompt = new JLabel("Deliver");
        JRadioButton deliverOption = new JRadioButton();

        ButtonGroup deliveryBtnGroup = new ButtonGroup();
        deliveryBtnGroup.add(pickupOption);
        deliveryBtnGroup.add(deliverOption);

        if(isDelivery){
            deliverOption.setSelected(true);
            pickupOption.setSelected(false);
        }
        else{
            pickupOption.setSelected(true);
            deliverOption.setSelected(false);
        }

        panel.add(pickupPrompt, "cell 0 1, alignx left");
        panel.add(pickupOption, "cell 1 1, center");
        panel.add(deliverPrompt, "cell 2 1, alignx left");
        panel.add(deliverOption, "cell 3 1, center");

        JLabel asapPrompt = new JLabel("ASAP");
        JRadioButton asapOption = new JRadioButton();
        JLabel timePrompt = new JLabel("Set Time");
        JRadioButton timeOption = new JRadioButton();

        ButtonGroup timeBtnGroup = new ButtonGroup();
        timeBtnGroup.add(asapOption);
        timeBtnGroup.add(timeOption);


        Date currentTime = new Date();
        System.out.println();
        if(currentTime.getTime() > time.getTime()){
            asapOption.setSelected(true);
            timeOption.setSelected(false);
        }
        else{
            asapOption.setSelected(false);
            timeOption.setSelected(true);
        }

        panel.add(asapPrompt, "cell 0 2, alignx left");
        panel.add(asapOption, "cell 1 2, center");
        panel.add(timePrompt, "cell 2 2, alignx left");
        panel.add(timeOption, "cell 3 2, center");

        // Create the timePanel with fixed width constraints
        JPanel timePanel = new JPanel(new MigLayout("insets 0, gapy 0", "[grow,fill][fill]", "[][]")); // No gap between rows
        timePanel.setVisible(false);
        JLabel prefTimePrompt = new JLabel("Preferred Time:");
        JTextField prefTimeField = new JTextField(10);

        // Add `prefTimePrompt` on top of `prefTimeField`
        timePanel.add(prefTimePrompt, "cell 0 0, alignx left, wrap");
        timePanel.add(prefTimeField, "cell 0 1, growx");

        // Setting up AM/PM options in a vertical panel
        JPanel timeBtnPanel = new JPanel(new MigLayout("insets 0, gapy 0", "[]", "[][]")); // Stack vertically with no additional gap
        JLabel amPrompt = new JLabel("AM");
        JRadioButton amOption = new JRadioButton();
        JLabel pmPrompt = new JLabel("PM");
        JRadioButton pmOption = new JRadioButton();
        ButtonGroup amPmBtnGroup = new ButtonGroup();
        amPmBtnGroup.add(amOption);
        amPmBtnGroup.add(pmOption);

        if(timeOption.isSelected()){
            timePanel.setVisible(true);
            if(time.getHours()>=12 && time.getHours()!= 24){
                amOption.setSelected(false);
                pmOption.setSelected(true);
                prefTimeField.setText((time.getHours()-12)+ ":" + time.getMinutes());
            }
            else{
                amOption.setSelected(true);
                pmOption.setSelected(false);
                prefTimeField.setText(time.getHours() + ":" + time.getMinutes());
            }
        }



        // Stack the AM and PM radio buttons vertically in `timeBtnPanel`
        timeBtnPanel.add(amPrompt, "alignx left");
        timeBtnPanel.add(amOption, "wrap"); // Wrap to move to the next row
        timeBtnPanel.add(pmPrompt, "alignx left");
        timeBtnPanel.add(pmOption);

        // Place `timeBtnPanel` in alignment with the text field, in the same row as the text field
        timePanel.add(timeBtnPanel, "cell 1 1, aligny top"); // Align top to the text field without affecting the prompt height


        panel.add(timePanel, "cell 0 3, spanx"); // Add timePanel to row 2 of the main panel without affecting other rows

        asapOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePanel.setVisible(timeOption.isSelected());
            }
        });
        timeOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePanel.setVisible(timeOption.isSelected());
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }

    public JFrame buildEditPayment(){
        JPanel panel = new JPanel(new MigLayout());
        JLabel title = new JLabel("Payment");
        panel.add(title, "cell 0 0");


        ButtonGroup pMethodGroup = new ButtonGroup();

        JLabel cardPrompt = new JLabel("Card");
        JRadioButton cardOption = new JRadioButton();
        JLabel cashPrompt = new JLabel("Cash/Check");
        JRadioButton cashOption = new JRadioButton();

        pMethodGroup.add(cardOption);
        pMethodGroup.add(cashOption);

        panel.add(cardPrompt, "cell 0 1");
        panel.add(cardOption, "cell 1 1");
        panel.add(cashPrompt, "cell 2 1");
        panel.add(cashOption, "cell 3 1");


        JPanel cardPanel = new JPanel(new MigLayout());
        JLabel cardNumPrompt = new JLabel("Card Number");



        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }
    public JFrame buildEditPayment(String cardNum, String csv, String expDate, String cardHolderName, String cardPaymentType){


        frame.setVisible(true);
        return frame;
    }

}
