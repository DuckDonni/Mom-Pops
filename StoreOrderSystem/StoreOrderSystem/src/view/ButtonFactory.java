package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        incBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = Integer.parseInt(amountField.getText()) + 1;
                amountField.setText("" + amount);
            }
        });
        decBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount = Integer.parseInt(amountField.getText());
                if (amount != 0) {
                    amount -= 1;
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
        JRadioButton smallBtn = new JRadioButton("S");
        JRadioButton mediumBtn = new JRadioButton("M");
        JRadioButton largeBtn = new JRadioButton("L");
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
        midBtn = new JRadioButton("Middle");

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
                    return "left";
                } else if (model == midBtn.getModel()) {
                    return "middle";
                } else if (model == rightBtn.getModel()) {
                    return "right";
                }
            }
        }
        return "none";
    }

}
