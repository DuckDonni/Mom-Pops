import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CustomerView {
    private static JFrame frame;
    private View view;
    private static Dimension screenSize;
    private static JPanel contentPanel;
    public CustomerView(View view) {

        view = this.view;
        frame = new JFrame("Homepage Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height); // Set frame size to the screen's resolution

        // Establishes layout for miglayout
        frame.setLayout(new MigLayout("fill", "[grow][right]", "[]20[grow]"));

        // Creates a panel to swap pages with Cardlayout
        contentPanel = new JPanel(new CardLayout());

        // Creates the navbar and passes the contentPanel to allow for page swapping
        NavBar navbar = new NavBar(contentPanel);
        frame.add(navbar.displayNavBar(), "cell 1 0, align right, growx");

        // Creates each instance for pages in the contentPanel
        contentPanel.add(buildHomePage(), "HomePage");
        contentPanel.add(buildMenuPage(), "MenuPage");
        contentPanel.add(buildLoginPage(), "LoginPage");
        contentPanel.add(buildCartPage(), "CartPage");
        contentPanel.add(buildPizzaPage(), "PizzaPage");
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
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "PizzaPage");
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
        JRadioButton pepperoniBox = new JRadioButton("Pepperoni");
        JRadioButton sausageBox = new JRadioButton("Sausage");
        JRadioButton hamBox = new JRadioButton("Ham");

        ButtonGroup meatBtnGroup = new ButtonGroup();
        meatBtnGroup.add(pepperoniBox);
        meatBtnGroup.add(sausageBox);
        meatBtnGroup.add(hamBox);


        JLabel nonMeatLabel = new JLabel("Choose Non-Meats");
        JRadioButton gPepperBox = new JRadioButton("Green Pepper");
        JRadioButton onionBox = new JRadioButton("Onion");
        JRadioButton tomatoBox = new JRadioButton("Tomato");
        JRadioButton mushroomBox = new JRadioButton("Mushroom");
        JRadioButton pineappleBox = new JRadioButton("Pineapple");

        ButtonGroup nonMeatBtnGroup = new ButtonGroup();
        nonMeatBtnGroup.add(gPepperBox);
        nonMeatBtnGroup.add(onionBox);
        nonMeatBtnGroup.add(tomatoBox);
        nonMeatBtnGroup.add(mushroomBox);
        nonMeatBtnGroup.add(pineappleBox);



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
        return panel;
    }

    public static JPanel buildDrinksPage(){
        JPanel panel = new JPanel(new MigLayout());
        return panel;
    }

    public static JPanel buildSidesPanel(){
        JPanel panel = new JPanel(new MigLayout());
        return panel;
    }
}
