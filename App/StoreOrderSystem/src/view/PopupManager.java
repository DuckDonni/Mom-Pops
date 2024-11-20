package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import model.*;
import pages.*;

public class PopupManager {

    private JFrame frame;
    private CustomerView cView;

    public PopupManager(CustomerView cView) {
        frame = new JFrame();
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        this.cView = cView;
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

        JLabel cityPrompt = new JLabel("City:");
        JTextField cityField = new JTextField(4);

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

        panel.add(cityPrompt, "cell 2 5, alignx left");
        panel.add(cityField, "cell 2 6, growx");

        panel.add(phoneNumberPrompt, "cell 0 7, alignx left");
        panel.add(phoneNumberField, "cell 0 8, growx");

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, "cell 0 9, alignx center");

        JButton cancelBtn = new JButton("Cancel");
        panel.add(cancelBtn, "cell 1 9, alignx center");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Receipt receipt = cView.view.controller.getReceipt();
                String customerName = fNameField.getText() + " " + lNameField.getText();

                receipt.setCustomerName(customerName);

                String address = "";
                boolean validInformation = true;

                if (addressField.getText().contains(",") || addressField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += addressField.getText() + ",";
                }
                if (bldRoomField.getText().contains(",")) {
                    validInformation = false;
                } else {
                    address += bldRoomField.getText() + ",";
                }
                if (stateField.getText().contains(",") || stateField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += stateField.getText() + ",";
                }
                if (cityField.getText().contains(",") || cityField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += cityField.getText() + ",";
                }
                if (zipField.getText().contains(",") || zipField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += zipField.getText() + ",";
                }
                if (phoneNumberField.getText().length() != 10) {
                    validInformation = false;
                }

                if (validInformation) {
                    receipt.setAddress(address);
                    receipt.setPhoneNumber(phoneNumberField.getText());
                    cView.view.controller.setReceipt(receipt);
                    cView.switchPage("CartPage", cView);
                    frame.dispose();
                }

            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }

    public JFrame buildEditCustInfo(String fName, String lName, String address, String phoneNumber) {
        frame.getContentPane().removeAll();
        frame.setTitle("Edit Profile Information");
        JPanel panel = new JPanel();
        JLabel title = new JLabel("Customer Information");
        panel.add(title, "cell 0 0");
        // Address will be broke up as (street address, bld number, state,city, zip)
        String[] addressBreakup = address.split(",");

        panel.setLayout(new MigLayout("fillx", "[grow,fill][grow,fill][grow,fill]", "[][][][][][][]"));

        JLabel fNamePrompt = new JLabel("First Name:");
        JTextField fNameField = new JTextField(fName, 10);

        JLabel lNamePrompt = new JLabel("Last Name:");
        JTextField lNameField = new JTextField(lName, 10);

        JLabel addressPrompt = new JLabel("Address:");
        JTextField addressField;
        JLabel bldRoomPrompt = new JLabel("Bld/Room #:");
        JTextField bldRoomField;
        JLabel zipPrompt = new JLabel("Zip Code:");
        JTextField zipField;
        JLabel statePrompt = new JLabel("State:");
        JTextField stateField;
        JLabel cityPrompt = new JLabel("City:");
        JTextField cityField;


        if (addressBreakup.length == 5) {
            addressField = new JTextField(addressBreakup[0], 15);
            bldRoomField = new JTextField(addressBreakup[1], 5);
            zipField = new JTextField(addressBreakup[2], 5);
            stateField = new JTextField(addressBreakup[3], 3);
            cityField = new JTextField(addressBreakup[4], 4);
        } else {
            addressField = new JTextField("", 15);
            bldRoomField = new JTextField("", 5);
            zipField = new JTextField("", 5);
            stateField = new JTextField("", 3);
            cityField = new JTextField("", 4);
        }


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

        // Move city here
        panel.add(cityPrompt, "cell 2 5, alignx left");
        panel.add(cityField, "cell 2 6, growx");

        panel.add(phoneNumberPrompt, "cell 0 7, alignx left");
        panel.add(phoneNumberField, "cell 0 8, growx");

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, "cell 1 9, center");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Receipt receipt = cView.view.controller.getReceipt();
                String customerName = fNameField.getText() + " " + lNameField.getText();

                receipt.setCustomerName(customerName);

                String address = "";
                boolean validInformation = true;

                if (addressField.getText().contains(",") || addressField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += addressField.getText() + ",";
                }
                if (bldRoomField.getText().contains(",")) {
                    validInformation = false;
                } else {
                    address += bldRoomField.getText() + ",";
                }
                if (stateField.getText().contains(",") || stateField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += stateField.getText() + ",";
                }
                if (cityField.getText().contains(",") || cityField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += cityField.getText() + ",";
                }
                if (zipField.getText().contains(",") || zipField.getText().isEmpty()) {
                    validInformation = false;
                } else {
                    address += zipField.getText() + ",";
                }
                if (phoneNumberField.getText().length() != 10) {
                    validInformation = false;
                }

                if (validInformation) {
                    receipt.setAddress(address);
                    receipt.setPhoneNumber(phoneNumberField.getText());
                    cView.view.controller.setReceipt(receipt);
                    cView.switchPage("CartPage", cView);
                    frame.dispose();
                }

            }
        });

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


        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, "cell 1 4, span 2, center");


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

    public JFrame buildEditOrderTime(boolean isDelivery, Calendar time) {
        frame.getContentPane().removeAll();
        Receipt receipt = cView.view.controller.getReceipt();
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

        if (isDelivery) {
            deliverOption.setSelected(true);
            pickupOption.setSelected(false);
        } else {
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


        Calendar currentTime = Calendar.getInstance();

        System.out.println("Good time " + time.getTime() + " Current time" + currentTime.getTime());
        if (currentTime.after(time)) {
            System.out.println("Triggers");
            asapOption.setSelected(true);
            timeOption.setSelected(false);
        } else {
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

        String[] storeTimeBreakup = receipt.getStringTime().split(":");
        if (timeOption.isSelected()) {
            timePanel.setVisible(true);
            if (storeTimeBreakup.length == 3) {
                if (storeTimeBreakup[2].equals("PM")) {
                    amOption.setSelected(false);
                    pmOption.setSelected(true);
                    prefTimeField.setText(storeTimeBreakup[0] + ":" + storeTimeBreakup[1]);
                } else {
                    amOption.setSelected(true);
                    pmOption.setSelected(false);
                    prefTimeField.setText(storeTimeBreakup[0] + ":" + storeTimeBreakup[1]);
                }
            }
//            if(time.HOUR_OF_DAY()>12 && time.getHours()< 25){
//                amOption.setSelected(false);
//                pmOption.setSelected(true);
//                prefTimeField.setText((time.getHours()-12)+ ":" + time.getMinutes());
//            }
//            else{
//                amOption.setSelected(true);
//                pmOption.setSelected(false);
//                prefTimeField.setText(time.getHours() + ":" + time.getMinutes());
//            }
        }


        // Stack the AM and PM radio buttons vertically in `timeBtnPanel`
        timeBtnPanel.add(amPrompt, "alignx left");
        timeBtnPanel.add(amOption, "wrap"); // Wrap to move to the next row
        timeBtnPanel.add(pmPrompt, "alignx left");
        timeBtnPanel.add(pmOption);

        // Place `timeBtnPanel` in alignment with the text field, in the same row as the text field
        timePanel.add(timeBtnPanel, "cell 1 1, aligny top"); // Align top to the text field without affecting the prompt height


        panel.add(timePanel, "cell 0 3, spanx"); // Add timePanel to row 2 of the main panel without affecting other rows

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, "cell 1 4, span 2, center");


        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;

                Calendar cal = Calendar.getInstance();
                if (deliverOption.isSelected()) {
                    receipt.setIsDelivery(true);
                } else {
                    receipt.setIsDelivery(false);
                }

                if (timeOption.isSelected()) {
                    String prefTime = prefTimeField.getText();

                    if (prefTime.length() > 5 || prefTime.length() < 3 || !prefTime.contains(":")) {
                        isValid = false;
                    }

//                    try{
//                        if(!amOption.isSelected() && !pmOption.isSelected()){
//                            isValid = false;
//                        }else{
//                            cal.set(Calendar.HOUR, Integer.parseInt(prefTime.split(":")[0]));
//                            cal.set(Calendar.MINUTE, Integer.parseInt(prefTime.split(":")[1]));
//                            if(amOption.isSelected()){
//                                cal.set(Calendar.AM_PM,Calendar.AM);
//                            }
//                            else{
//                                cal.set(Calendar.AM_PM,Calendar.PM);
//                            }
//
//                        }
//
//                    }catch(Exception exception){
//                        isValid = false;
//                        System.out.println(exception.getMessage());
//                    }


                }

                if (isValid) {
                    String am_pm;
                    if (amOption.isSelected()) {
                        am_pm = "AM";
                    } else {
                        am_pm = "PM";
                    }
                    String stringTime = prefTimeField.getText() + ":" + am_pm;
                    System.out.println("Stored " + stringTime);
                    receipt.setStringTime(stringTime);
                    cView.view.controller.setReceipt(receipt);
                    cView.switchPage("CartPage", cView);
                    frame.dispose();
                }

            }
        });

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

    public JFrame buildEditPayment() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel(new MigLayout("wrap 4")); // wrap to 4 columns for the payment method row
        JLabel title = new JLabel("Payment");
        panel.add(title, "span"); // title spans all columns and is centered

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

        JPanel cardPanel = new JPanel(new MigLayout("wrap 2, fillx")); // wrap to 2 columns within the card panel
        JLabel cardNumPrompt = new JLabel("Card Number");
        JTextField cardNumField = new JTextField(15); // specify preferred width

        JLabel csvPrompt = new JLabel("CSV");
        JTextField csvField = new JTextField(10); // specify preferred width

        JLabel expPrompt = new JLabel("Exp Date");
        JTextField expField = new JTextField(10);

        JLabel cardholderPrompt = new JLabel("Cardholder Name");
        JTextField cardholderField = new JTextField(15);

        JLabel cardTypePrompt = new JLabel("Card Type");
        JTextField cardTypeField = new JTextField(15);

        cardPanel.add(cardNumPrompt, "cell 0 0, align left");
        cardPanel.add(cardNumField, "cell 0 1,growx"); // field grows to fill available space in cell
        cardPanel.add(csvPrompt, "cell 0 2,align left");
        cardPanel.add(csvField, "cell 0 3,growx");
        cardPanel.add(expPrompt, "cell 1 2,align left");
        cardPanel.add(expField, "cell 1 3,growx");
        cardPanel.add(cardholderPrompt, "cell 2 2, align left");
        cardPanel.add(cardholderField, "cell 2 3, growx");
        cardPanel.add(cardTypePrompt, "cell 1 0, align left");
        cardPanel.add(cardTypeField, "cell 1 1,growx");
        cardPanel.setVisible(false);
        panel.add(cardPanel, "cell 0 2, span 4, growx"); // make cardPanel span all columns

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, "cell 0 3, span 4, center");

        cardOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.setVisible(true);
            }
        });
        cashOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.setVisible(false);
            }
        });


        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }

    public JFrame buildEditPayment(String cardNum, String csv, String expDate, String cardHolderName, String cardPaymentType) {
        frame.getContentPane().removeAll();
        Receipt receipt = cView.view.controller.getReceipt();
        String[] paymentConc = new String[5];
        paymentConc[0] = cardNum;
        paymentConc[1] = csv;
        paymentConc[2] = expDate;
        paymentConc[3] = cardHolderName;
        paymentConc[4] = cardPaymentType;

        if (cardPaymentType.equals("cash")) {
            cardNum = "";
            csv = "";
            expDate = "";
            cardHolderName = "";
            cardPaymentType = "";
        }

        JPanel panel = new JPanel(new MigLayout("wrap 4")); // wrap to 4 columns for the payment method row
        JLabel title = new JLabel("Payment");
        panel.add(title, "span"); // title spans all columns and is centered

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

        JPanel cardPanel = new JPanel(new MigLayout("wrap 2, fillx")); // wrap to 2 columns within the card panel
        JLabel cardNumPrompt = new JLabel("Card Number");
        JTextField cardNumField = new JTextField(cardNum, 15); // specify preferred width

        JLabel csvPrompt = new JLabel("CSV");
        JTextField csvField = new JTextField(csv, 10); // specify preferred width

        JLabel expPrompt = new JLabel("Exp Date");
        JTextField expField = new JTextField(expDate, 10);

        JLabel cardholderPrompt = new JLabel("Cardholder Name");
        JTextField cardholderField = new JTextField(cardHolderName, 15);

        JLabel cardTypePrompt = new JLabel("Card Type");
        JTextField cardTypeField = new JTextField(cardPaymentType, 15);

        cardPanel.add(cardNumPrompt, "cell 0 0, align left");
        cardPanel.add(cardNumField, "cell 0 1,growx"); // field grows to fill available space in cell
        cardPanel.add(csvPrompt, "cell 0 2,align left");
        cardPanel.add(csvField, "cell 0 3,growx");
        cardPanel.add(expPrompt, "cell 1 2,align left");
        cardPanel.add(expField, "cell 1 3,growx");
        cardPanel.add(cardholderPrompt, "cell 2 2, align left");
        cardPanel.add(cardholderField, "cell 2 3, growx");
        cardPanel.add(cardTypePrompt, "cell 1 0, align left");
        cardPanel.add(cardTypeField, "cell 1 1,growx");
        cardPanel.setVisible(false);
        panel.add(cardPanel, "cell 0 2, span 4, growx"); // make cardPanel span all columns

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, "cell 0 3, span 4, center");

        if (paymentConc[4].equalsIgnoreCase("cash")) {
            cardPanel.setVisible(false);
            cardOption.setSelected(false);
            cashOption.setSelected(true);
        } else {
            cardPanel.setVisible(true);
            cardOption.setSelected(true);
            cashOption.setSelected(false);
        }

        cardOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.setVisible(true);
                paymentConc[4] = "";
            }
        });
        cashOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.setVisible(false);
                paymentConc[4] = "cash";
            }
        });

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                if (!paymentConc[4].equals("cash")) {
                    if ((cardNumField.getText().length() < 15 || cardNumField.getText().length() > 16)) {
                        isValid = false;
                    }
                    if (cardTypeField.getText().isEmpty()) {
                        isValid = false;
                    }
                    if (cardholderField.getText().isEmpty()) {
                        isValid = false;
                    }
                    if (cardTypeField.getText().isEmpty()) {
                        isValid = false;
                    }
                    if (csvField.getText().length() < 3 || csvField.getText().length() > 4) {
                        isValid = false;
                    }
                    if (expField.getText().isEmpty()) {
                        isValid = false;
                    } else if (expField.getText().length() == 5) {
                        if (expField.getText().charAt(2) != '/') {
                            isValid = false;
                        }
                    } else {
                        isValid = false;
                    }
                }


                if (isValid) {

                    if (paymentConc[4].equals("cash")) {
                        receipt.setPayment("a" + "," + "a" + "," + "a" + "," + "a" + "," + paymentConc[4]);
                    } else {
                        System.out.println(cardNumField.getText() + "," + csvField.getText() + "," + expField.getText() + "," + cardholderField.getText() + "," + cardTypeField.getText());
                        receipt.setPayment(cardNumField.getText() + "," + csvField.getText() + "," + expField.getText() + "," + cardholderField.getText() + "," + cardTypeField.getText());
                    }
                    cView.view.controller.setReceipt(receipt);
                    cView.switchPage("CartPage", cView);
                    frame.dispose();
                }
            }
        });
        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }

    public JFrame overrideCode() {
        JPanel panel = new JPanel(new MigLayout());
        JLabel header = new JLabel("Override Code");
        JLabel prompt1 = new JLabel("Call the store at (770) 555 - 1212");
        JLabel prompt2 = new JLabel("and request an override code");

        JTextField overrideField = new JTextField(20);

        panel.add(header, "cell 0 0, align left");
        panel.add(prompt1, "cell 1 1, align center");
        panel.add(prompt2, "cell 1 2, align center");
        panel.add(overrideField, "cell 0 3,span 2, align center");
        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (overrideField.getText().equals("123456")) {
                    CartPage.submitOrder();
                }
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }

    public JFrame submitPopup() {
        JPanel panel = new JPanel(new MigLayout());
        JLabel notice = new JLabel("Your order has been placed!");

        JButton okayBtn = new JButton("Okay");

        panel.add(notice, "cell 0 0, center ");
        panel.add(okayBtn, "cell 0 1, center");

        okayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }


}
