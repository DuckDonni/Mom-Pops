package pages;

import net.miginfocom.swing.MigLayout;
import view.CustomerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaPage {
    private JPanel contentPanel;
    private CustomerView cView;
    public PizzaPage(CustomerView cView) {
        this.cView = cView;
        contentPanel = cView.contentPanel;
    }
    public JPanel returnPage(){
        JPanel panel = new JPanel(new MigLayout());

        JPanel leftPanel = new JPanel(new MigLayout());

        JLabel sizeLabel = new JLabel("Size:");
        ButtonGroup sizeBtnGroup = new ButtonGroup();
        JRadioButton smallBox = new JRadioButton("Small");
        JRadioButton MediumBox = new JRadioButton("Medium");
        JRadioButton largeBox = new JRadioButton("Large");
        JRadioButton extraLargeBox = new JRadioButton("Extra Large");

        sizeBtnGroup.add(smallBox);
        sizeBtnGroup.add(MediumBox);
        sizeBtnGroup.add(largeBox);
        sizeBtnGroup.add(extraLargeBox);


        ButtonGroup crustBtnGroup = new ButtonGroup();
        JLabel crustLabel = new JLabel("Crust");
        JRadioButton crustBox = new JRadioButton("Regular");
        JRadioButton thinBox = new JRadioButton("Thin");
        JRadioButton panBox = new JRadioButton("Pan");

        crustBtnGroup.add(crustBox);
        crustBtnGroup.add(thinBox);
        crustBtnGroup.add(panBox);

        ButtonGroup sauceBtnGroup = new ButtonGroup();
        JLabel sauceLabel = new JLabel("Sauce");
        JRadioButton tomatoSauceBox = new JRadioButton("Tomato Based Marinara");
        JRadioButton noSauceBox = new JRadioButton("None");

        sauceBtnGroup.add(tomatoSauceBox);
        sauceBtnGroup.add(noSauceBox);

        ButtonGroup cheeseBtnGroup = new ButtonGroup();
        JLabel cheeseLabel = new JLabel("Cheese");
        JRadioButton cheeseBox = new JRadioButton("Cheese");
        JRadioButton noCheeseBox = new JRadioButton("None");

        cheeseBtnGroup.add(cheeseBox);
        cheeseBtnGroup.add(noCheeseBox);

        leftPanel.add(sizeLabel, "cell 0 0, growx");
        leftPanel.add(smallBox, "cell 0 1, growx");
        leftPanel.add(MediumBox, "cell 1 1, growx");
        leftPanel.add(largeBox, "cell 2 1, growx");
        leftPanel.add(extraLargeBox, "cell 3 1, growx");

        leftPanel.add(crustLabel, "cell 0 2, growx");
        leftPanel.add(crustBox, "cell 0 3, growx");
        leftPanel.add(thinBox, "cell 0 4, growx");
        leftPanel.add(panBox, "cell 0 5, growx");

        leftPanel.add(sauceLabel, "cell 1 2, growx");
        leftPanel.add(tomatoSauceBox, "cell 1 3, growx");
        leftPanel.add(noSauceBox, "cell 1 4, growx");

        leftPanel.add(cheeseLabel, "cell 0 6, growx");
        leftPanel.add(cheeseBox, "cell 0 7, growx");
        leftPanel.add(noCheeseBox, "cell 0 8, growx");


        JPanel rightPanel = new JPanel(new MigLayout());

        JLabel toppingsLabel = new JLabel("Toppings:");
        JLabel toppingMessage1 = new JLabel(" - First Topping(Free)");
        JLabel toppingMessage2 = new JLabel(" - Extra Toppings + 0.75(S) 1.00(M) / 1.25(L) / 1.50(XL)");
        JLabel meatLabel = new JLabel("Choose Meats");
        JCheckBox pepperoniBox = new JCheckBox("Pepperoni");
        JCheckBox sausageBox = new JCheckBox("Sausage");
        JCheckBox hamBox = new JCheckBox("Ham");



        JLabel nonMeatLabel = new JLabel("Choose Non-Meats");
        JCheckBox gPepperBox = new JCheckBox("Green Pepper");
        JCheckBox onionBox = new JCheckBox("Onion");
        JCheckBox tomatoBox = new JCheckBox("Tomato");
        JCheckBox mushroomBox = new JCheckBox("Mushroom");
        JCheckBox pineappleBox = new JCheckBox("Pineapple");
        JCheckBox extraCheeseBox = new JCheckBox("Extra Cheese");



        rightPanel.add(toppingsLabel, "cell 0 0");
        rightPanel.add(toppingMessage1, "cell 0 1");
        rightPanel.add(toppingMessage2, "cell 0 2");
        rightPanel.add(meatLabel, "cell 0 3");
        rightPanel.add(pepperoniBox, "cell 0 4");
        rightPanel.add(sausageBox, "cell 0 5");
        rightPanel.add(hamBox, "cell 0 6");
        rightPanel.add(nonMeatLabel, "cell 0 7");
        rightPanel.add(gPepperBox, "cell 0 8");
        rightPanel.add(onionBox, "cell 0 9");
        rightPanel.add(tomatoBox, "cell 0 10");
        rightPanel.add(mushroomBox, "cell 0 11");
        rightPanel.add(pineappleBox, "cell 0 12");
        rightPanel.add(extraCheeseBox, "cell 0 13, growx");
        panel.add(leftPanel, "cell 0 0, growx");
        panel.add(rightPanel, "cell 2 0, growx");

        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");
        panel.add(submitBtn, "cell 0 1");
        panel.add(cancelBtn, "cell 1 1");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "MenuPage");
            }
        });

        return panel;
    }
}
