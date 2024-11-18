package pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import view.*;
import model.*;
public class CartPage {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private CustomerView cView;
    private static Receipt receipt;
    public CartPage(CustomerView custView) {
        this.cView = custView;
        receipt = cView.view.controller.getReceipt();
    }

    public JPanel returnPage() {
        PopupManager popupManager = new PopupManager();
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        // Update receipt from the controller
        receipt = cView.view.controller.getReceipt();

        // Create the content panel to scroll
        JPanel scrollBarPanel = new JPanel();
        scrollBarPanel.setLayout(new MigLayout("wrap 1", "[grow, fill]"));
        ArrayList<Pizza> pizzaAr = receipt.getPizzaAr();
        for (Pizza p : pizzaAr) {
            System.out.println(pizzaAr.size());
            scrollBarPanel.add(makeCard(p), "growx");
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

        editCustBtn.addActionListener(e -> popupManager.buildEditCustInfo());
        editOrderTimeBtn.addActionListener(e -> popupManager.buildEditOrderTime());
        editPayment.addActionListener(e -> popupManager.buildEditPayment());

        JPanel rightPanel = new JPanel(new MigLayout());

        JPanel customerPanel = new JPanel(new MigLayout());
        customerPanel.add(editCustBtn);

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


    public static JPanel makeCard(Pizza p) {
        JPanel panel = new JPanel(new MigLayout("insets 10, wrap 4", "[grow][grow][grow][60px]", "[]10[]"));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel sizeLabel = new JLabel("Size: " + p.getCrustSize());
        JLabel crustLabel = new JLabel("Crust: " + p.getCrustSize());
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
        toppingString = toppingString.substring(0, toppingString.length()-2);
        JTextArea toppings = new JTextArea("Toppings: " + toppingString);
        toppings.setLineWrap(true);
        toppings.setWrapStyleWord(true);
        toppings.setEditable(false); // Make it behave like a label
        toppings.setOpaque(false); // Match panel background

        JLabel price = new JLabel("$");

        // Add components to the panel
        panel.add(sizeLabel, "cell 0 0");
        panel.add(crustLabel, "cell 1 0");
        panel.add(sauceLabel, "cell 2 0");
        panel.add(price, "cell 3 0 1 2, align center"); // Spans 2 rows and aligns to the right
        panel.add(toppings, "cell 0 1 3 1, growx"); // Spans 3 columns and grows horizontally

        return panel;
    }



}
