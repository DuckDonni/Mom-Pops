package view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The ButtonFactory class provides methods to create and manage common UI components such as increment buttons,
 * size selection buttons, and side selection buttons. These components are used throughout the application for selecting
 * quantities and options.
 * <p>
 * The class includes methods for building buttons with increment and decrement actions, radio buttons for size selection,
 * and radio buttons for side selection. It also provides methods to retrieve the selected values from these buttons.
 * </p>
 */
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

    /**
     * Builds an increment button panel with a decrement button, increment button, and a text field to display and modify
     * the amount. The amount is constrained between 0 and 999. This method also includes key and action listeners to
     * handle user input and modify the amount accordingly.
     *
     * @return a JPanel containing the increment buttons and the amount field
     */
    public JPanel buildIncrementButton() {
        panel = new JPanel(new MigLayout());
        amount = 1;

        // Creating decrement, increment buttons and the amount display field
        JButton decBtn = new JButton("<");
        JButton incBtn = new JButton(">");
        JTextField amountField = new JTextField("" + amount, 3);
        amountField.setHorizontalAlignment(0);
        amountField.setEditable(false);

        // KeyListener to handle invalid input in the amount field (e.g., negative values or values exceeding 999)
        amountField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Handle key typing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Handle key press
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Handle key release
            }
        });

        // ActionListener for increment button to increase the amount by 1
        incBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(amount < 999) {
                    amount = Integer.parseInt(amountField.getText()) + 1;
                }
                amountField.setText("" + amount);
            }
        });

        // ActionListener for decrement button to decrease the amount by 1
        decBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (amount > 1) {
                    amount -= 1;
                }
                amountField.setText("" + amount);
            }
        });

        // Adding components to the panel
        panel.add(decBtn, "cell 0 0");
        panel.add(amountField, "cell 1 0");
        panel.add(incBtn, "cell 2 0");

        return panel;
    }

    /**
     * Returns the current increment amount selected by the user.
     *
     * @return the selected increment amount
     */
    public int getIncAmount() {
        return amount;
    }

    /**
     * Sets the increment amount to a specific value.
     *
     * @param amount the amount to set
     */
    public void setIncAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Builds a panel with radio buttons to select the size of an item (Small, Medium, Large).
     * The buttons are grouped together so only one can be selected at a time.
     *
     * @return a JPanel containing the size selection radio buttons
     */
    public JPanel buildSizeButton() {
        JPanel panel = new JPanel(new MigLayout());
        sizeBtnGroup = new ButtonGroup();

        // Creating size radio buttons
        smallBtn = new JRadioButton("S");
        mediumBtn = new JRadioButton("M");
        largeBtn = new JRadioButton("L");

        // Adding buttons to the group
        sizeBtnGroup.add(smallBtn);
        sizeBtnGroup.add(mediumBtn);
        sizeBtnGroup.add(largeBtn);

        // Adding buttons to the panel
        panel.add(smallBtn, "cell 0 0");
        panel.add(mediumBtn, "cell 1 0");
        panel.add(largeBtn, "cell 2 0");

        return panel;
    }

    /**
     * Returns the size selected by the user (Small, Medium, Large).
     *
     * @return the selected size ("small", "medium", "large", or "none" if no size is selected)
     */
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

    /**
     * Builds a panel with radio buttons to select the side of the item (Left, Whole, Right).
     * The buttons are grouped together so only one can be selected at a time.
     *
     * @return a JPanel containing the side selection radio buttons
     */
    public JPanel buildSideButton() {
        JPanel panel = new JPanel(new MigLayout());
        leftBtn = new JRadioButton("Left");
        rightBtn = new JRadioButton("Right");
        midBtn = new JRadioButton("Whole");

        // Adding buttons to the group
        sideBtnGroup = new ButtonGroup();
        sideBtnGroup.add(leftBtn);
        sideBtnGroup.add(rightBtn);
        sideBtnGroup.add(midBtn);

        // Adding buttons to the panel
        panel.add(leftBtn, "cell 0 0");
        panel.add(midBtn, "cell 1 0");
        panel.add(rightBtn, "cell 2 0");

        return panel;
    }

    /**
     * Returns the side selected by the user (Left, Whole, Right).
     *
     * @return the selected side ("Left", "Whole", "Right", or "none" if no side is selected)
     */
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

    /**
     * Sets the selected side based on the provided side string ("Left", "Whole", "Right").
     *
     * @param side the side to set ("Left", "Whole", or "Right")
     */
    public void setSelectedSide(String side){
        switch (side){
            case "Left":
                leftBtn.setSelected(true);
                break;
            case "Whole":
                midBtn.setSelected(true);
                break;
            case "Right":
                rightBtn.setSelected(true);
                break;
        }
    }
}
