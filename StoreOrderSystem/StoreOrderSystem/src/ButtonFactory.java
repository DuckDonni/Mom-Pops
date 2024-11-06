import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonFactory {
    private int amount;
    private JPanel panel;
    private ButtonGroup btnGroup;
    private JRadioButton smallBtn;
    private JRadioButton mediumBtn;
    private JRadioButton largeBtn;

    public ButtonFactory(){

    }

    public JPanel buildIncrementButton(){
        panel = new JPanel(new MigLayout());
        amount = 1;
        JButton decBtn = new JButton("<");
        JButton incBtn = new JButton(">");
        JTextField amountField = new JTextField("" + amount, 3);
        amountField.setHorizontalAlignment(0);

        incBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = Integer.parseInt(amountField.getText()) +1;
                amountField.setText(""+amount);
            }
        });
        decBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = Integer.parseInt(amountField.getText());
                if(amount != 0){
                    amount-=1;
                }
                amountField.setText(""+amount);
            }
        });
        panel.add(decBtn, "cell 0 0");
        panel.add(amountField, "cell 1 0");
        panel.add(incBtn, "cell 2 0");

        return panel;
    }
    public int getIncAmount(){
        return amount;
    }
    public void setIncAmount(int amount){
        this.amount = amount;
    }

    public JPanel buildSizeButton(){
        JPanel panel = new JPanel(new MigLayout());
        btnGroup = new ButtonGroup();
        JRadioButton smallBtn = new JRadioButton("S");
        JRadioButton mediumBtn = new JRadioButton("M");
        JRadioButton largeBtn = new JRadioButton("L");
        btnGroup.add(smallBtn);
        btnGroup.add(mediumBtn);
        btnGroup.add(largeBtn);

        panel.add(smallBtn, "cell 0 0");
        panel.add(mediumBtn, "cell 1 0");
        panel.add(largeBtn, "cell 2 0");
        return panel;
    }
    public String getSelectedSize(){
         ButtonModel model = btnGroup.getSelection();
         if(smallBtn != null && mediumBtn != null && largeBtn != null){
             if(model == smallBtn.getModel()){
                 return "small";
             }
             else if(model == mediumBtn.getModel()){
                 return "medium";
             }
             else if(model == largeBtn.getModel()){
                 return "large";
             }
         }

         return "none";
    }
}
