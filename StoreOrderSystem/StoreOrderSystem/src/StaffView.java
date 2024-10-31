import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

public class StaffView {
    private JFrame frame;
    View view;
    public StaffView(View view) {
        view = this.view;
        frame = new JFrame("Staff View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height); // Set frame size to the screen's resolution

        // Establishes layout for MigLayout
        frame.setLayout(new MigLayout());

        // Add components to the staff view
        frame.add(new JButton("Manage Orders"), "cell 0 0, growx");
        frame.add(new JButton("Inventory"), "cell 1 0, growx");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(false); // Initially hidden
    }

    public JFrame getFrame() {
        return frame;
    }
}
