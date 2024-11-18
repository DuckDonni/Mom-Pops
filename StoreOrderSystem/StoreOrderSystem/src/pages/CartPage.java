package pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;
import model.*;
public class CartPage {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;

    public CartPage() {
    }

    public static JPanel returnPage() {
        PopupManager popupManager = new PopupManager();
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        // Create the content panel to scroll
        JPanel scrollBarPanel = new JPanel();
        scrollBarPanel.setLayout(new MigLayout("wrap 1", "[grow, fill]"));


        scrollBarPanel.add(makeCard(), "growx");


        // Create JScrollPane for the content
        JScrollPane scrollPane = new JScrollPane(scrollBarPanel);
        scrollPane.setPreferredSize(new Dimension((int) (screenSize.width * .35), (int) (screenSize.height * 0.7))); // Set desired size
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scroll

        // Customize vertical scrollbar size
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(20, 0)); // Increase width of vertical scrollbar

        JButton editCustBtn = new JButton("Edit Cust. Info");
        JButton editOrderTimeBtn = new JButton("Edit Order Info");
        JButton editPayment = new JButton("Edit Payment");

        editCustBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupManager.buildEditCustInfo();
            }
        });

        editOrderTimeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupManager.buildEditOrderTime();
            }
        });

        editPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupManager.buildEditPayment();
            }
        });

        JPanel rightPanel = new JPanel(new MigLayout());

        JPanel customerPanel = new JPanel(new MigLayout());
        customerPanel.add(editCustBtn);
        //Logic for displaying customer info from account

        JPanel orderPanel = new JPanel(new MigLayout());
        orderPanel.add(editOrderTimeBtn);
        //Logic for displaying order info from receipt

        JPanel paymentPanel = new JPanel(new MigLayout());
        paymentPanel.add(editPayment);
        //Logic for displaying payment info from receipt


        rightPanel.add(customerPanel, "wrap, align right");
        rightPanel.add(orderPanel, "wrap, align right");
        rightPanel.add(paymentPanel, "wrap, align right");

        // Add buttons and scroll pane to main panel
        panel.add(rightPanel, "east");
        panel.add(scrollPane, "west"); // Keep scroll pane constrained to the desired width

        return panel;
    }

    public static JPanel makeCard() {
        JPanel panel = new JPanel(new MigLayout("insets 10, wrap 4", "[grow][grow][grow][60px]", "[]10[]"));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel sizeLabel = new JLabel("Size: ");
        JLabel crustLabel = new JLabel("Crust: ");
        JLabel sauceLabel = new JLabel("Sauce: ");

        // Use JTextArea for word-wrapped toppings
        JTextArea toppings = new JTextArea("Toppings: ");
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
