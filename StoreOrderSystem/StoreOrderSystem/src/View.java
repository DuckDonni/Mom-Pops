import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
public class View {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Homepage Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(400, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height); // Set frame size to the screen's resolution
        frame.setLayout(new MigLayout("fill", "[grow][right]", "[]20[grow]"));

        NavBar navbar = new NavBar(0);
        frame.add(navbar.displayNavBar(), "cell 1 0, align right, growx");
        frame.add(getHomePage(), "cell 0 1, span, center"); // Makes elements automatically span across all columns

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
