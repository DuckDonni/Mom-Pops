import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

public class CustomerView {
    private static JFrame frame;
    private View view;
    private static Dimension screenSize;
    private static JPanel contentPanel;
    public CustomerView(View view) {

        this.view = view;
        frame = new JFrame("Homepage Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height); // Set frame size to the screen's resolution

        // Establishes layout for miglayout
        frame.setLayout(new MigLayout("fill", "[grow][right]", "[]20[grow]"));
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(buildHomePage(), "HomePage");
        contentPanel.add(buildMenuPage(), "MenuPage");
        contentPanel.add(buildPizzaPage(), "PizzaPage");
        contentPanel.add(buildDrinksPage(), "DrinksPage");
        contentPanel.add(buildCartPage(), "CartPage");
        contentPanel.add(buildLoginPage(), "LoginPage");
        switchPage("HomePage");
        // Creates the navbar and passes the contentPanel to allow for page swapping
        NavBar navbar = new NavBar(contentPanel);
        frame.add(navbar.displayNavBar(), "cell 1 0, align right, growx");


        // Displays the panel in the main frame
        frame.add(contentPanel, "cell 0 1, span, grow");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return frame;
    }
    public static JPanel buildHomePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.add(new JButton("Delivery"), "cell 0 0, growx");
        panel.add(new JButton("Carryout"), "cell 1 0, growx");
        return panel;
    }


    public static JPanel buildMenuPage() {
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
                switchPage("PizzaPage");
            }
        });
        drinksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPage("DrinksPage");
            }
        });
        return panel;
    }

    public static JPanel buildCartPage() {
        PopupManager popupManager = new PopupManager();
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        // Create the content panel to scroll
        JPanel scrollBarPanel = new JPanel();
        scrollBarPanel.setLayout(new MigLayout("wrap 1", "[grow, fill]"));

        JLabel label = new JLabel("label");

        scrollBarPanel.add(label);

        // Create JScrollPane for the content
        JScrollPane scrollPane = new JScrollPane(scrollBarPanel);
        scrollPane.setPreferredSize(new Dimension((int)(screenSize.width*.35), (int)(screenSize.height*0.7))); // Set desired size

        // Customize scrollbar size
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(20, 0)); // Increase width of vertical scrollbar

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setPreferredSize(new Dimension(0, 20)); // Increase height of horizontal scrollbar

        JButton editCustBtn = new JButton("Edit Cust. Info");
        JButton editOrderTimeBtn = new JButton("Edit Order Info");
        JButton editPayment = new JButton("Edit Payment");
        editCustBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sample for filled edit field
                // "Jonah","Smith","160 Paulk Rd,,AL,36301","3346181809"
                popupManager.buildEditCustInfo();
            }
        });

        editOrderTimeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Test for preset time
//                Date t = new Date();
//                t.setHours(22);
//                t.setMinutes(21);
//                popupManager.buildEditOrderTime(true,t);
                popupManager.buildEditOrderTime();
            }
        });
        editPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupManager.buildEditPayment();
            }
        });
        // Add buttons and scroll pane to main panel
        panel.add(editCustBtn, "cell 3 0");
        panel.add(editOrderTimeBtn, "cell 3 1");
        panel.add(editPayment, "cell 3 2");
        panel.add(scrollPane, "cell 0 0, span 3 3, grow"); // Span scroll pane across multiple cells

        return panel;
    }

    public static JPanel buildLoginPage(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        JLabel phonenumber_ID = new JLabel("Enter Phone Number or Employee ID");
        JTextField phoneNumberField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter Phone Number");
        JTextField passwordField = new JTextField();
        phoneNumberField.setPreferredSize(new Dimension((int)(screenSize.width*0.1), (int)(screenSize.height*0.02)));
        passwordField.setPreferredSize(new Dimension((int)(screenSize.width*0.1), (int)(screenSize.height*0.02)));
        panel.add(phoneNumberField , "cell 0 1, growx");
        panel.add(phonenumber_ID, "cell 0 0, growx");
        panel.add(passwordLabel , "cell 0 2, growx");
        panel.add(passwordField, "cell 0 3, growx");
        return panel;
    }

    public static JPanel buildPizzaPage(){
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
        JRadioButton extraCheeseBox = new JRadioButton("Extra Cheese");
        JRadioButton noCheeseBox = new JRadioButton("None");

        cheeseBtnGroup.add(cheeseBox);
        cheeseBtnGroup.add(extraCheeseBox);
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
        leftPanel.add(extraCheeseBox, "cell 0 8, growx");
        leftPanel.add(noCheeseBox, "cell 0 9, growx");


        JPanel rightPanel = new JPanel(new MigLayout());

        JLabel toppingsLabel = new JLabel("Toppings:");

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




        rightPanel.add(toppingsLabel, "cell 0 0");
        rightPanel.add(meatLabel, "cell 0 1");
        rightPanel.add(pepperoniBox, "cell 0 2");
        rightPanel.add(sausageBox, "cell 0 3");
        rightPanel.add(hamBox, "cell 0 4");
        rightPanel.add(nonMeatLabel, "cell 0 5");
        rightPanel.add(gPepperBox, "cell 0 6");
        rightPanel.add(onionBox, "cell 0 7");
        rightPanel.add(tomatoBox, "cell 0 8");
        rightPanel.add(mushroomBox, "cell 0 9");
        rightPanel.add(pineappleBox, "cell 0 10");

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

    public static JPanel buildDrinksPage(){
        JPanel panel = new JPanel(new MigLayout());

        JLabel drinksLabel = new JLabel("Drinks:");


        JCheckBox pepsiBox = new JCheckBox("Pepsi");
        ButtonFactory pepsiBF = new ButtonFactory();
        ButtonFactory pepsiSize = new ButtonFactory();


        JCheckBox dPepsiBox = new JCheckBox("Diet Pepsi");
        ButtonFactory dPepsiBF = new ButtonFactory();
        ButtonFactory dPepsiSize = new ButtonFactory();

        JCheckBox orangeBox = new JCheckBox("Orange");
        ButtonFactory orangeBF = new ButtonFactory();
        ButtonFactory orangeSize = new ButtonFactory();


        JCheckBox dOrangeBox = new JCheckBox("Diet Orange");
        ButtonFactory dOrangeBF = new ButtonFactory();
        ButtonFactory dOrangeSize = new ButtonFactory();

        JCheckBox rBeerBox = new JCheckBox("Root Beer");
        ButtonFactory rBeerBF = new ButtonFactory();
        ButtonFactory rBeerSize = new ButtonFactory();

        JCheckBox dRBeerBox = new JCheckBox("Diet Root Beer");
        ButtonFactory dRBeerBF = new ButtonFactory();
        ButtonFactory dRBeerSize = new ButtonFactory();

        JCheckBox starryBox = new JCheckBox("Starry");
        ButtonFactory starryBF = new ButtonFactory();
        ButtonFactory starrySize = new ButtonFactory();

        JCheckBox lemonadeBox = new JCheckBox("Lemonade");
        ButtonFactory lemonadeBF = new ButtonFactory();
        ButtonFactory lemonadeSize = new ButtonFactory();



        panel.add(drinksLabel, "cell 0 0");

        JPanel pepsiPanel = pepsiBF.buildIncrementButton();
        JPanel pepsiSizePanel = pepsiSize.buildSizeButton();
        panel.add(pepsiBox, "cell 0 1");
        panel.add(pepsiPanel, "cell 1 1");
        panel.add(pepsiSizePanel, "cell 0 2");

        JPanel dPepsiPanel = dPepsiBF.buildIncrementButton();
        JPanel dPepsiSizePanel = dPepsiSize.buildSizeButton();
        panel.add(dPepsiBox, "cell 0 3");
        panel.add(dPepsiPanel, "cell 1 3");
        panel.add(dPepsiSizePanel, "cell 0 4");


        JPanel orangePanel = orangeBF.buildIncrementButton();
        JPanel orangeSizePanel = dPepsiSize.buildSizeButton();
        panel.add(orangeBox, "cell 0 5");
        panel.add(orangePanel, "cell 1 5");
        panel.add(orangeSizePanel, "cell 0 6");

        JPanel dOrangePanel = dOrangeBF.buildIncrementButton();
        JPanel dOrangeSizePanel = dPepsiSize.buildSizeButton();
        panel.add(dOrangeBox, "cell 0 7");
        panel.add(dOrangePanel, "cell 1 7");
        panel.add(dOrangeSizePanel, "cell 0 8");


        JPanel rBeerPanel = rBeerBF.buildIncrementButton();
        JPanel rBeerSizePanel = dPepsiSize.buildSizeButton();
        panel.add(rBeerBox, "cell 2 1");
        panel.add(rBeerPanel, "cell 3 1");
        panel.add(rBeerSizePanel, "cell 2 2");

        JPanel dRBeerPanel = dRBeerBF.buildIncrementButton();
        JPanel dRBeerSizePanel = dPepsiSize.buildSizeButton();
        panel.add(dRBeerBox, "cell 2 3");
        panel.add(dRBeerPanel, "cell 3 3");
        panel.add(dRBeerSizePanel, "cell 2 4");

        JPanel starryPanel = starryBF.buildIncrementButton();
        JPanel starrySizePanel = dPepsiSize.buildSizeButton();
        panel.add(starryBox, "cell 2 5");
        panel.add(starryPanel, "cell 3 5");
        panel.add(starrySizePanel, "cell 2 6");

        JPanel lemonadePanel = lemonadeBF.buildIncrementButton();
        JPanel lemonadeSizePanel = dPepsiSize.buildSizeButton();
        panel.add(lemonadeBox, "cell 2 7");
        panel.add(lemonadePanel, "cell 3 7");
        panel.add(lemonadeSizePanel, "cell 2 8");

        JButton submitBtn = new JButton("Submit");
        JButton cancelBtn = new JButton("Cancel");

        panel.add(submitBtn, "cell 0 9");
        panel.add(cancelBtn, "cell 1 9");


        //Sets inc Panels to not visible
        pepsiPanel.setVisible(false);
        dPepsiPanel.setVisible(false);
        orangePanel.setVisible(false);
        dOrangePanel.setVisible(false);
        rBeerPanel.setVisible(false);
        dRBeerPanel.setVisible(false);
        starryPanel.setVisible(false);
        lemonadePanel.setVisible(false);

        pepsiSizePanel.setVisible(false);
        dPepsiSizePanel.setVisible(false);
        orangeSizePanel.setVisible(false);
        dOrangeSizePanel.setVisible(false);
        rBeerSizePanel.setVisible(false);
        dRBeerSizePanel.setVisible(false);
        starrySizePanel.setVisible(false);
        lemonadeSizePanel.setVisible(false);



        // Action Listeners for button elements
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "MenuPage");
            }
        });
        pepsiBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pepsiPanel.setVisible(pepsiBox.isSelected());
                pepsiSizePanel.setVisible(pepsiBox.isSelected());
            }
        });
        dPepsiBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dPepsiPanel.setVisible(dPepsiBox.isSelected());
                dPepsiSizePanel.setVisible(dPepsiBox.isSelected());
            }
        });
        orangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orangePanel.setVisible(orangeBox.isSelected());
                orangeSizePanel.setVisible(orangeBox.isSelected());
            }
        });
        dOrangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dOrangePanel.setVisible(dOrangeBox.isSelected());
                dOrangeSizePanel.setVisible(dOrangeBox.isSelected());
            }
        });
        rBeerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rBeerPanel.setVisible(rBeerBox.isSelected());
                rBeerSizePanel.setVisible(rBeerBox.isSelected());
            }
        });
        dRBeerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dRBeerPanel.setVisible(dRBeerBox.isSelected());
                dRBeerSizePanel.setVisible(dRBeerBox.isSelected());
            }
        });
        starryBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starryPanel.setVisible(starryBox.isSelected());
                starrySizePanel.setVisible(starryBox.isSelected());
            }
        });
        lemonadeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lemonadePanel.setVisible(lemonadeBox.isSelected());
                lemonadeSizePanel.setVisible(lemonadeBox.isSelected());
            }
        });

        return panel;
    }

    public static JPanel buildSidesPanel(){
        JPanel panel = new JPanel(new MigLayout());

        return panel;
    }

    private static void switchPage(String pageName) {

        contentPanel.removeAll();
        contentPanel.add(buildHomePage(), "HomePage");
        contentPanel.add(buildMenuPage(), "MenuPage");
        contentPanel.add(buildLoginPage(), "LoginPage");
        contentPanel.add(buildCartPage(), "CartPage");
        contentPanel.add(buildPizzaPage(), "PizzaPage");
        contentPanel.add(buildDrinksPage(), "DrinksPage");

        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, pageName);
    }
}
