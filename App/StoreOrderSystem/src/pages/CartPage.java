package pages;

import model.MenuItem;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import view.*;
import model.*;

public class CartPage {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static CustomerView cView;
    private static Receipt receipt;

    public CartPage(CustomerView custView) {
        this.cView = custView;
        receipt = cView.view.controller.getReceipt();
    }

    public JPanel returnPage() {
        PopupManager popupManager = new PopupManager(cView);
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        // Update receipt from the controller
        receipt = cView.view.controller.getReceipt();

        // Create the content panel to scroll
        JPanel scrollBarPanel = new JPanel();
        scrollBarPanel.setLayout(new MigLayout("wrap 1", "[grow, fill]"));


        ArrayList<Pizza> pizzaAr = receipt.getPizzaAr();
        JLabel pizzaLabel = new JLabel("Pizza:");
        scrollBarPanel.add(pizzaLabel, "cell 0 0");
        JPanel pizzaPanel = new JPanel(new MigLayout("wrap 1", "[grow, fill]"));
        for (Pizza p : pizzaAr) {
            System.out.println(pizzaAr.size());
            pizzaPanel.add(makePizzaCard(p), "growx");
        }
        scrollBarPanel.add(pizzaPanel, "cell 0 1, growx");

        JLabel sidesLabel = new JLabel("Sides:");
        scrollBarPanel.add(sidesLabel, "cell 0 2");
        JPanel sidesPanel = new JPanel(new MigLayout("wrap 1", "[grow, fill]"));
        scrollBarPanel.add(sidesPanel, "cell 0 3");

        JLabel drinksLabel = new JLabel("Drinks:");
        scrollBarPanel.add(drinksLabel, "cell 0 4");
        JPanel drinksPanel = new JPanel(new MigLayout("wrap 1", "[grow, fill]"));
        scrollBarPanel.add(drinksPanel, "cell 0 5");

        ArrayList<MenuItem> menuItemAr = receipt.getMenuItemAr();
        for (MenuItem item : menuItemAr) {
            String name = item.getName();
            if (name.equalsIgnoreCase("bread sticks") || name.equalsIgnoreCase("bread stick bites") || name.equalsIgnoreCase("big chocolate chip cookie")) {
                sidesPanel.add(makeSideCard(item));
            } else {
                drinksPanel.add(makeDrinkCard(item));
            }


        }


        // Create JScrollPane for the content
        JScrollPane scrollPane = new JScrollPane(scrollBarPanel);
        scrollPane.setPreferredSize(new Dimension((int) (screenSize.width * .45), (int) (screenSize.height * 0.7)));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(20, 0));

        JButton editCustBtn = new JButton("Edit Cust. Info");
        JButton editOrderTimeBtn = new JButton("Edit Order Info");
        JButton editPayment = new JButton("Edit Payment");

        String custName = receipt.getCustomerName();
        String custFirstName = "";
        String custLastName = "";
        if (!custName.isEmpty()) {
            custFirstName = custName.split(" ")[0];
            custLastName = custName.split(" ")[1];
        }

        String custFName = custFirstName;
        String custLName = custLastName;
        editCustBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupManager.buildEditCustInfo(custFName, custLName, receipt.getAddress(), receipt.getPhoneNumber());
            }
        });
        editOrderTimeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                popupManager.buildEditOrderTime(receipt.getIsDelivery(), receipt.getDateTime());
            }
        });
        editPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] paymentBreakup = receipt.getPayment().split(",");
                String cardNum = "";
                String csv = "";
                String expDate = "";
                String cardHolderName = "";
                String cardPaymentType = "";

                if (paymentBreakup.length == 5) {
                    System.out.println("5");
                    cardNum = paymentBreakup[0];
                    csv = paymentBreakup[1];
                    expDate = paymentBreakup[2];
                    cardHolderName = paymentBreakup[3];
                    cardPaymentType = paymentBreakup[4];
                }
                System.out.println("card: " + cardPaymentType);
                popupManager.buildEditPayment(cardNum, csv, expDate, cardHolderName, cardPaymentType);
            }
        });

        JPanel rightPanel = new JPanel(new MigLayout());

        JPanel customerPanel = new JPanel(new MigLayout());
        // Address will be broke up as (street address, bld number, state,city, zip)
        String[] addressBreakup = receipt.getAddress().split(",");
        String address = "";
        String bldNumber = "";
        String state = "";
        String city = "";
        String zip = "";
        if (addressBreakup.length == 5) {
            address = addressBreakup[0];
            bldNumber = addressBreakup[1];
            state = addressBreakup[2];
            city = addressBreakup[3];
            zip = addressBreakup[4];
        }

        JLabel addressLabel = new JLabel(address);
        JLabel bldNumberLabel;
        if (bldNumber.isEmpty()) {
            bldNumberLabel = new JLabel(bldNumber);
        } else {
            bldNumberLabel = new JLabel("- " + bldNumber);
        }

        JLabel stateLabel = new JLabel(state);
        JLabel cityLabel = new JLabel("");
        if(city.isEmpty()) {
            cityLabel = new JLabel(city);
        }
        else{
            cityLabel = new JLabel(city +", ");
        }
        JLabel zipLabel = new JLabel(zip);

        JLabel custNameLabel = new JLabel(receipt.getCustomerName());


        customerPanel.add(editCustBtn, "cell 0 3,wrap, align right");
        customerPanel.add(custNameLabel, "cell 0 0");
        customerPanel.add(addressLabel, "cell 0 1");
        customerPanel.add(bldNumberLabel, "cell 1 1");
        customerPanel.add(cityLabel, "cell 0 2");
        customerPanel.add(stateLabel, "cell 0 2");
        customerPanel.add(zipLabel, "cell 0 2");

        customerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel orderPanel = new JPanel(new MigLayout());
        String deliverCarry = "";
        if(receipt.getIsDelivery()){
            deliverCarry = "Delivery";
        }
        else{
            deliverCarry = "Pickup";
        }
        JLabel deliveryCarryLabel = new JLabel(deliverCarry);
        String time = "";
        if(!receipt.getStringTime().isEmpty()) {
            if(receipt.getStringTime().split(":")[2].equals("ASAP")){
                time = "ASAP";
            }else {
                time = receipt.getStringTime().split(":")[0] + ":" + receipt.getStringTime().split(":")[1] + " " + receipt.getStringTime().split(":")[2];
            }
        }
        JLabel timeLabel = new JLabel(time);

        orderPanel.add(deliveryCarryLabel, "cell 0 0, wrap, align right");
        orderPanel.add(editOrderTimeBtn, "cell 0 2, wrap , align right");
        orderPanel.add(timeLabel, "cell 0 1, wrap, align right");


        orderPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JPanel paymentPanel = new JPanel(new MigLayout());
        paymentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        paymentPanel.add(editPayment, "cell 0 4 ,wrap, align right");
        String[] paymentBreakup = receipt.getPayment().split(",");
        if(paymentBreakup.length == 5) {
            JLabel cardNumberLabel = new JLabel("");
            JLabel cardHolderNameLabel = new JLabel(paymentBreakup[3]);
            if(paymentBreakup[0].length() > 5) {
                cardNumberLabel.setText("Ending with " + paymentBreakup[0].substring(paymentBreakup[0].length() - 4));
            }
            JLabel paymentTypeLabel = new JLabel(paymentBreakup[4]);
            if(paymentTypeLabel.getText().equals("cash")){
                paymentPanel.add(paymentTypeLabel, "cell 0 0 , align right");
            }
        else{
                paymentPanel.add(cardNumberLabel, "cell 0 0 , align right");
                paymentPanel.add(cardHolderNameLabel, "cell 0 1 , align right");
                paymentPanel.add(paymentTypeLabel, "cell 0 2 , align right");
            }
        }






        rightPanel.add(customerPanel, "wrap,growx,  align right");
        rightPanel.add(orderPanel, "wrap,growx, align right");
        rightPanel.add(paymentPanel, "wrap,growx, align right");


        // Add buttons and scroll pane to main panel
        panel.add(rightPanel, "east");
        panel.add(scrollPane, "west");

        BigDecimal price = new BigDecimal(receipt.getPrice()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal tax = price.multiply(new BigDecimal("0.06")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = price.add(tax).setScale(2, RoundingMode.HALF_UP);

        DecimalFormat df = new DecimalFormat("#0.00");

        JLabel itemTotalLabel = new JLabel("Item Total: ");
        JLabel taxTotalLabel = new JLabel("Tax Total: ");
        JLabel orderTotalLabel = new JLabel("Order Total: ");
        JLabel itemTotalPriceLabel = new JLabel("$ " + df.format(price));
        JLabel taxTotalPriceLabel = new JLabel("$ " + df.format(tax));
        JLabel orderTotalPriceLabel = new JLabel("$ " + df.format(total));
        JPanel totalPanel = new JPanel(new MigLayout());

        totalPanel.add(itemTotalLabel, "cell  0 1, alignx left");
        totalPanel.add(itemTotalPriceLabel, "cell 1 1, alignx right");
        totalPanel.add(taxTotalLabel, "cell  0 2, alignx left");
        totalPanel.add(taxTotalPriceLabel, "cell 1 2, alignx right");
        totalPanel.add(orderTotalLabel, "cell  0 3, alignx left");
        totalPanel.add(orderTotalPriceLabel, "cell 1 3, alignx right");

        JButton submitBtn = new JButton("Place Order");

        totalPanel.add(submitBtn, "cell  0 4,span 2,  center");

        panel.add(totalPanel, "south");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isValid = true;
                if (receipt.getPizzaAr().size() == 0 && receipt.getMenuItemAr().size() == 0) {
                    isValid = false;
                }
                String[] paymentBreakup = receipt.getPayment().split(",");
                if (paymentBreakup.length != 5) {
                    isValid = false;
                } else {


                    if (!paymentBreakup[4].equals("cash")) {
                        if (paymentBreakup[0].isEmpty()) {
                            isValid = false;
                        }
                        if (paymentBreakup[1].isEmpty()) {
                            isValid = false;
                        }
                        if (paymentBreakup[2].isEmpty()) {
                            isValid = false;
                        }
                        if (paymentBreakup[3].isEmpty()) {
                            isValid = false;
                        }
                    }
                }

                if (isValid) {
                    if (receipt.getPizzaAr().size() > 9) {
                        popupManager.overrideCode();
                    } else {
                        submitOrder();
                    }
                }
            }
        });

        return panel;
    }

    public static void submitOrder() {

        boolean isValid = true;
        if (isValid) {
            if (Calendar.getInstance().after(receipt.getDateTime())) {
                receipt.setDateTime(Calendar.getInstance());
            }

            ArrayList<Pizza> pizzaAr = new ArrayList<>();
            ArrayList<MenuItem> menuItemAr = new ArrayList<>();
            receipt.setPizzaAr(pizzaAr);
            receipt.setMenuItemAr(menuItemAr);
            receipt.setPrice(0);
            cView.view.controller.setReceipt(receipt);
            cView.switchPage("CartPage", cView);
            PopupManager pm = new PopupManager(cView);
            pm.submitPopup();
        }
    }

    public static JPanel makePizzaCard(Pizza p) {
        JPanel panel = new JPanel(new MigLayout("insets 10, wrap 4", "[grow][grow][grow][60px]", "[]10[]"));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        String crustSize = p.getCrustSize();
        if (crustSize.equalsIgnoreCase("extra")) {
            crustSize = "extra large";
        }
        JLabel sizeLabel = new JLabel("Size: " + crustSize);
        JLabel crustLabel = new JLabel("Crust: " + p.getCrustType());
        JLabel sauceLabel;
        if (p.getIsSauce()) {
            sauceLabel = new JLabel("Sauce: Tomato Based Marinara");
        } else {
            sauceLabel = new JLabel("Sauce: None");
        }

        // Use JTextArea for word-wrapped toppings
        String toppingString = "";
        ArrayList<Topping> toppingAr = p.getToppingAr();
        for (Topping t : toppingAr) {
            if (!t.getLocation().equals("Whole")) {
                toppingString += t.getName() + "(" + t.getLocation() + "), ";
            } else {
                toppingString += t.getName() + ", ";
            }
        }
        if (toppingString.length() > 1) {
            toppingString = toppingString.substring(0, toppingString.length() - 2);
        }

        JTextArea toppings = new JTextArea("Toppings: " + toppingString);
        toppings.setLineWrap(true);
        toppings.setWrapStyleWord(true);
        toppings.setEditable(false); // Make it behave like a label
        toppings.setOpaque(false); // Match panel background
        DecimalFormat priceFormat = new DecimalFormat("#.00");
        String formattedPrice = priceFormat.format(p.getPrice());
        //System.out.println("FP " + formattedPrice + " price " + p.getPrice());
        JLabel price = new JLabel("$" + formattedPrice);

        JButton deleteBtn = new JButton("D");
        JButton editBtn = new JButton("E");

        // Add components to the panel
        panel.add(sizeLabel, "cell 0 0");
        panel.add(crustLabel, "cell 1 0");
        panel.add(sauceLabel, "cell 2 0");
        panel.add(price, "cell 3 0, align center");
        panel.add(deleteBtn, "cell 3 1, align center");
        panel.add(editBtn, "cell 3 2, align center");
        panel.add(toppings, "cell 0 1 3 1, growx"); // Spans 3 columns and grows horizontally


        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cView.setShiftView(cView.buildEditPizzaPage(cView,p));

                //cView.switchPage("shiftPanel", cView);
                //cView.switchPage("HomePage",cView);
                cView.buildEditPizzaPage(cView,p);

                //cView.switchPage("shiftPanel", cView);
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt.getPizzaAr().remove(p);
                receipt.calculatePrice();
                cView.view.controller.setReceipt(receipt);
                cView.switchPage("CartPage", cView);
            }
        });
        return panel;
    }

    public static JPanel makeSideCard(MenuItem item) {
        JPanel panel = new JPanel(new MigLayout("insets 10", "[grow][grow][60px]", "[]10[]"));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel nameLabel = new JLabel("Name: " + item.getName() + " ");
        JLabel quantityLabel = new JLabel("(" + item.getQuantity() + ")");
        JLabel priceLabel = new JLabel("Price: " + item.getPrice());

        // Align price label to the right
        panel.add(nameLabel, "cell 0 0");
        panel.add(quantityLabel, "cell 1 0");
        panel.add(priceLabel, "cell 2 0, align right");

        JButton deleteBtn = new JButton("D");
        panel.add(deleteBtn, "cell 2 1 , align center");

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt.getMenuItemAr().remove(item);
                receipt.calculatePrice();
                cView.view.controller.setReceipt(receipt);
                cView.switchPage("CartPage", cView);
            }
        });

        return panel;
    }


    public static JPanel makeDrinkCard(MenuItem item) {
        JPanel panel = new JPanel(new MigLayout("insets 10, wrap 3", "[grow][grow][60px]"));  // Adjust layout for right-aligned price
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        String name = item.getName().split(",")[0];
        String size = item.getName().split(",")[1];

        JLabel nameLabel = new JLabel("Name: " + name + " ");
        JLabel quantityLabel = new JLabel("(" + item.getQuantity() + ")");
        JLabel sizeLabel = new JLabel("Size: " + size);

        // Add name, quantity, and size to the panel (in the first two columns)
        panel.add(nameLabel, "cell 0 0");
        panel.add(quantityLabel, "cell 1 0");
        panel.add(sizeLabel, "cell 0 1, span 2 1");  // Makes size span both columns

        // Add price label to the third column and align it to the right
        JLabel priceLabel = new JLabel("Price: " + item.getPrice());
        panel.add(priceLabel, "cell 2 0, align right");  // Align the price to the right

        // Add delete button below the price
        JButton deleteBtn = new JButton("D");
        panel.add(deleteBtn, "cell 2 1, align center");

        // Add action listener to the delete button
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt.getMenuItemAr().remove(item);
                receipt.calculatePrice();
                cView.view.controller.setReceipt(receipt);
                cView.switchPage("CartPage", cView);
            }
        });

        return panel;
    }




}
