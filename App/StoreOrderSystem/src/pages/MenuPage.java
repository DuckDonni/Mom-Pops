package pages;

import net.miginfocom.swing.MigLayout;
import view.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage {
    private CustomerView cView;
    public MenuPage(CustomerView cView){
        this.cView = cView;
    }
    public JPanel returnPage(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        JButton pizzaButton = new JButton("Build Pizza");
        JButton drinksButton = new JButton("Drinks");
        JButton sidesButton = new JButton("Sides");
        panel.add(pizzaButton, "cell 0 0, growx");
        panel.add(drinksButton, "cell 1 0, growx");
        panel.add(sidesButton, "cell 2 0, growx");

        pizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("PizzaPage", cView);
            }
        });
        drinksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("DrinksPage", cView);
            }
        });
        sidesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cView.switchPage("SidesPage", cView);
            }
        });
        return panel;
    }
}
