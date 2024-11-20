package pages;

import model.MenuItem;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;
import view.*;
import model.*;
public class CartPage {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private CustomerView cView;
    private Receipt receipt;
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
        scrollBarPanel.add(pizzaLabel,"cell 0 0");
        JPanel pizzaPanel = new JPanel(new MigLayout("wrap 1", "[grow, fill]"));
        for (Pizza p : pizzaAr) {
            System.out.println(pizzaAr.size());
            pizzaPanel.add(makePizzaCard(p), "growx");
        }
        scrollBarPanel.add(pizzaPanel, "cell 0 1, growx");

        JLabel sidesLabel = new JLabel("Sides:");
        scrollBarPanel.add(sidesLabel,"cell 0 2");
        JPanel sidesPanel = new JPanel(new MigLayout("wrap 1", "[grow, fill]"));
        scrollBarPanel.add(sidesPanel,"cell 0 3");

        JLabel drinksLabel = new JLabel("Drinks:");
        scrollBarPanel.add(drinksLabel,"cell 0 4");
        JPanel drinksPanel = new JPanel(new MigLayout("wrap 1", "[grow, fill]"));
        scrollBarPanel.add(drinksPanel,"cell 0 5");

        ArrayList<MenuItem> menuItemAr = receipt.getMenuItemAr();
        for(MenuItem item : menuItemAr){
            String name = item.getName();
            if(name.equalsIgnoreCase("bread sticks") || name.equalsIgnoreCase("bread stick bites") || name.equalsIgnoreCase("big chocolate chip cookie")){
                sidesPanel.add(makeSideCard(item));
            }
            else{
                drinksPanel.add(makeDrinkCard(item));
            }


        }



        // Create JScrollPane for the content
        JScrollPane scrollPane = new JScrollPane(scrollBarPanel);
        scrollPane.setPreferredSize(new Dimension((int) (screenSize.width * .35), (int) (screenSize.height * 0.7)));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(20, 0));

        JButton editCustBtn = new JButton("Edit Cust. Info");
        JButton editOrderTimeBtn = new JButton("Edit Order Info");
        JButton editPayment = new JButton("Edit Payment");

        String custName = receipt.getCustomerName();
        String custFirstName = "";
        String custLastName = "";
        if(!custName.isEmpty()){
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

                if(paymentBreakup.length ==5){
                    System.out.println("5");
                    cardNum = paymentBreakup[0];
                    csv = paymentBreakup[1];
                    expDate = paymentBreakup[2];
                    cardHolderName = paymentBreakup[3];
                    cardPaymentType = paymentBreakup[4];
                }
                System.out.println("card: "+cardPaymentType);
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
        if(addressBreakup.length==5) {
            address = addressBreakup[0];
            bldNumber = addressBreakup[1];
            state = addressBreakup[2];
            city = addressBreakup[3];
            zip = addressBreakup[4];
        }

        JLabel addressLabel = new JLabel(address);
        JLabel bldNumberLabel;
        if(bldNumber.isEmpty()){
            bldNumberLabel = new JLabel(bldNumber);
        }
        else{
            bldNumberLabel = new JLabel("- " + bldNumber);
        }

        JLabel stateLabel = new JLabel(state);
        JLabel cityLabel = new JLabel(city);
        JLabel zipLabel = new JLabel(zip);

        JLabel custNameLabel = new JLabel(receipt.getCustomerName());


        customerPanel.add(editCustBtn, "cell 0 0");
        customerPanel.add(custNameLabel, "cell 0 1");
        customerPanel.add(addressLabel, "cell 0 2");
        customerPanel.add(bldNumberLabel, "cell 1 2");
        customerPanel.add(cityLabel, "cell 0 3");
        customerPanel.add(stateLabel, "cell 1 3");
        customerPanel.add(zipLabel, "cell 2 3");


        JPanel orderPanel = new JPanel(new MigLayout());
        orderPanel.add(editOrderTimeBtn);

        JPanel paymentPanel = new JPanel(new MigLayout());
        paymentPanel.add(editPayment);




        rightPanel.add(customerPanel, "wrap, align right");
        rightPanel.add(orderPanel, "wrap, align right");
        rightPanel.add(paymentPanel, "wrap, align right");




        // Add buttons and scroll pane to main panel
        panel.add(rightPanel, "east");
        panel.add(scrollPane, "west");

        return panel;
    }


    public static JPanel makePizzaCard(Pizza p) {
        JPanel panel = new JPanel(new MigLayout("insets 10, wrap 4", "[grow][grow][grow][60px]", "[]10[]"));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        String crustSize = p.getCrustSize();
        if(crustSize.equalsIgnoreCase("extra")){
            crustSize = "extra large";
        }
        JLabel sizeLabel = new JLabel("Size: " + crustSize);
        JLabel crustLabel = new JLabel("Crust: " + p.getCrustType() );
        JLabel sauceLabel;
        if(p.getIsSauce()) {
            sauceLabel = new JLabel("Sauce: Tomato Based Marinara");
        }
        else{
            sauceLabel = new JLabel("Sauce: None");
        }

        // Use JTextArea for word-wrapped toppings
        String toppingString = "";
        ArrayList<Topping> toppingAr = p.getToppingAr();
        for(Topping t: toppingAr){
            if(!t.getLocation().equals("Whole")) {
                toppingString += t.getName() + "(" + t.getLocation() + "), ";
            }
            else{
                toppingString += t.getName() + ", ";
            }
        }
        if(toppingString.length() >1){
            toppingString = toppingString.substring(0, toppingString.length()-2);
        }

        JTextArea toppings = new JTextArea("Toppings: " + toppingString);
        toppings.setLineWrap(true);
        toppings.setWrapStyleWord(true);
        toppings.setEditable(false); // Make it behave like a label
        toppings.setOpaque(false); // Match panel background
        DecimalFormat priceFormat = new DecimalFormat("#.00");
        String formattedPrice = priceFormat.format(p.getPrice());
        System.out.println("FP " + formattedPrice + " price " +p.getPrice());
        JLabel price = new JLabel("$" + formattedPrice);

        // Add components to the panel
        panel.add(sizeLabel, "cell 0 0");
        panel.add(crustLabel, "cell 1 0");
        panel.add(sauceLabel, "cell 2 0");
        panel.add(price, "cell 3 0 1 2, align center"); // Spans 2 rows and aligns to the right
        panel.add(toppings, "cell 0 1 3 1, growx"); // Spans 3 columns and grows horizontally

        return panel;
    }
    public static JPanel makeSideCard(MenuItem item){
        JPanel panel = new JPanel(new MigLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel nameLabel = new JLabel("Name: " + item.getName() + " ");
        JLabel quantityLabel = new JLabel("(" + item.getQuantity() + ")");
        JLabel priceLabel = new JLabel("Price: " + item.getPrice());
        panel.add(nameLabel, "cell 0 0");
        panel.add(quantityLabel, "cell 1 0");
        panel.add(priceLabel, "cell 2 0, align right");

        return panel;
    }
    public static JPanel makeDrinkCard(MenuItem item){
        JPanel panel = new JPanel(new MigLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        String name = item.getName().split(",")[0];
        String size = item.getName().split(",")[1];

        JPanel dataPanel = new JPanel(new MigLayout());
        JLabel nameLabel = new JLabel("Name: " + name + " ");
        JLabel quantityLabel = new JLabel("(" + item.getQuantity() + ")");
        JLabel priceLabel = new JLabel("Price: " + item.getPrice());
        JLabel sizeLabel = new JLabel("Size: " + size);
        dataPanel.add(nameLabel, "cell 0 0");
        dataPanel.add(quantityLabel, "cell 1 0");
        dataPanel.add(sizeLabel, " cell 0 1");
        panel.add(dataPanel, " cell 0 0, growx");
        panel.add(priceLabel, "cell 1 0 1 2, align right");



        return panel;
    }


}
