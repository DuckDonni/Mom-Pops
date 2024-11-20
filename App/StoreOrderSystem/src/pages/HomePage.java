package pages;

import model.Receipt;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private CustomerView cView;
    public HomePage(CustomerView cView){
        this.cView = cView;
    }
    public JPanel returnPage(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fill", "[center]", "[center]"));
        JButton deliveryBtn = new JButton("Delivery");
        JButton carryOutBtn = new JButton("Carryout");

        Receipt receipt = cView.view.controller.getReceipt();

        panel.add(deliveryBtn, "cell 0 0, wrap, align right");
        panel.add(carryOutBtn, "cell 1 0, align left");


        deliveryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt.setIsDelivery(true);
                cView.view.controller.setReceipt(receipt);
                cView.switchPage("MenuPage", cView);
            }
        });

        carryOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                receipt.setIsDelivery(false);
                cView.view.controller.setReceipt(receipt);
                cView.switchPage("MenuPage", cView);
            }
        });

        return panel;
    }
}
