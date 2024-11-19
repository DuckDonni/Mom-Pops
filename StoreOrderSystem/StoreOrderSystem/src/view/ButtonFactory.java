package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ButtonFactory {
    private int amount;
    private JPanel panel;
    private ButtonGroup sizeBtnGroup;
    private JRadioButton smallBtn;
    private JRadioButton mediumBtn;
    private JRadioButton largeBtn;

    private ButtonGroup sideBtnGroup;
    private JRadioButton leftBtn;
    private JRadioButton midBtn;
    private JRadioButton rightBtn;

    public JPanel buildIncrementButton() {
        panel = new JPanel(new MigLayout());
        amount = 1;
        JButton decBtn = new JButton("<");
        JButton incBtn = new JButton(">");
        JTextField amountField = new JTextField("" + amount, 3);
        amountField.setHorizontalAlignment(0);
        amountField.setEditable(false);
        amountField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(amountField.getText().contains("-")) {
                    amountField.setText("0");
                    amount = 0;
                }
                else if(amountField.getText().length() > 4) {
                    amountField.setText("999");
                    amount = 999;
                }
                else if(Integer.parseInt(amountField.getText()) >999) {
                    amountField.setText("999");
                    amount = 999;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(amountField.getText().contains("-")) {
                    amountField.setText("0");
                    amount = 0;
                }
                else if(amountField.getText().length() > 4) {
                    amountField.setText("999");
                    amount = 999;
                }
                else if(Integer.parseInt(amountField.getText()) >999) {
                    amountField.setText("999");
                    amount = 999;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(amountField.getText().contains("-")) {
                    amountField.setText("0");
                    amount = 0;
                }
                else if(amountField.getText().length() > 4) {
                    amountField.setText("999");
                    amount = 999;
                }
                else if(Integer.parseInt(amountField.getText()) >999) {
                    amountField.setText("999");
                    amount = 999;
                }
            }
        });
        amountField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(amountField.getText().contains("-")) {
                    amountField.setText("0");
                    amount = 0;
                }
                else if(amountField.getText().length() > 4) {
                    amountField.setText("999");
                    amount = 999;
                }
            }
        });
        incBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(amount < 999){
                    amount = Integer.parseInt(amountField.getText()) + 1;
                }
                if(amount > 999){
                    amountField.setText("999");
                    amount = 999;
                }
                if(amount < 0){
                    amountField.setText("0");
                    amount = 0;
                }
                amountField.setText("" + amount);
            }
        });
        decBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = Integer.parseInt(amountField.getText());
                if (amount != 1) {
                    amount -= 1;
                }
                if(amount < 1){
                    amountField.setText("0");
                    amount = 0;
                }
                if(amount > 999){
                    amountField.setText("999");
                    amount = 999;
                }
                amountField.setText("" + amount);
            }
        });
        panel.add(decBtn, "cell 0 0");
        panel.add(amountField, "cell 1 0");
        panel.add(incBtn, "cell 2 0");

        return panel;
    }

    public int getIncAmount() {

        return amount;
    }

    public void setIncAmount(int amount) {
        this.amount = amount;
    }

    public JPanel buildSizeButton() {
        JPanel panel = new JPanel(new MigLayout());
        sizeBtnGroup = new ButtonGroup();
        smallBtn = new JRadioButton("S");
        mediumBtn = new JRadioButton("M");
        largeBtn = new JRadioButton("L");
        sizeBtnGroup.add(smallBtn);
        sizeBtnGroup.add(mediumBtn);
        sizeBtnGroup.add(largeBtn);

        panel.add(smallBtn, "cell 0 0");
        panel.add(mediumBtn, "cell 1 0");
        panel.add(largeBtn, "cell 2 0");
        return panel;
    }

    public String getSelectedSize() {
        if (sizeBtnGroup != null) {
            ButtonModel model = sizeBtnGroup.getSelection();
            if (smallBtn != null && mediumBtn != null && largeBtn != null) {
                if (model == smallBtn.getModel()) {
                    return "small";
                } else if (model == mediumBtn.getModel()) {
                    return "medium";
                } else if (model == largeBtn.getModel()) {
                    return "large";
                }
            }
        }
        return "none";
    }

    public JPanel buildSideButton() {
        JPanel panel = new JPanel(new MigLayout());
        leftBtn = new JRadioButton("Left");
        rightBtn = new JRadioButton("Right");
        midBtn = new JRadioButton("Whole");

        sideBtnGroup = new ButtonGroup();
        sideBtnGroup.add(leftBtn);
        sideBtnGroup.add(rightBtn);
        sideBtnGroup.add(midBtn);
        panel.add(leftBtn, "cell 0 0");
        panel.add(midBtn, "cell 1 0");
        panel.add(rightBtn, "cell 2 0");


        return panel;
    }

    public String getSelectedSide() {
        if (sideBtnGroup != null) {
            ButtonModel model = sideBtnGroup.getSelection();

            if (leftBtn != null && rightBtn != null && midBtn != null) {
                if (model == leftBtn.getModel()) {
                    return "Left";
                } else if (model == midBtn.getModel()) {
                    return "Whole";
                } else if (model == rightBtn.getModel()) {
                    return "Right";
                }
            }
        }
        return "none";
    }

}
