import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class View {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Homepage Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new MigLayout());

        NavBar navbar = new NavBar(0);
        frame.add(navbar.displayNavBar(), "cell 1 0, growx");
        frame.add(getHomePage(), "cell 0 1, span, growx"); // Makes elements automatically span across all columns

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static JPanel getHomePage() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        panel.add(new JButton("Delivery"), "cell 0 0, growx");
        panel.add(new JButton("Carryout"), "cell 1 0, growx");

        return panel;
    }
}
