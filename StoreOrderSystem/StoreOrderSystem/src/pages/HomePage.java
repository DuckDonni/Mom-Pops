package pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import view.*;

import java.awt.*;

public class HomePage {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private CustomerView cView;
    public HomePage(CustomerView cView){
        this.cView = cView;
    }
    public JPanel returnPage(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JButton("Delivery"), "cell 0 0, growx");
        panel.add(new JButton("Carryout"), "cell 1 0, growx");
        return panel;
    }
}
