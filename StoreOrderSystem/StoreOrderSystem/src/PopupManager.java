import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class PopupManager {

    private JFrame frame;
    public PopupManager() {
        frame = new JFrame();
        frame.setSize(350,400);
        frame.setLocationRelativeTo(null);
    }

    // Address - (123 Street, City, State, Zip) or (123 Street, bld apt, City, State, Zip)
    public JFrame buildEditCustInfo(){
        frame.setTitle("Edit Profile Information");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        JLabel fNamePrompt = new JLabel("First Name:");
        JTextField fNameField = new JTextField(10);

        JLabel lNamePrompt = new JLabel("Last Name:");
        JTextField lNameField = new JTextField(10);

        JLabel addressPrompt = new JLabel("Address");
        JTextField addressField = new JTextField(10);

        JLabel bldRoomPrompt = new JLabel("Bld/Room #");
        JTextField bldRoomField = new JTextField(10);

        JLabel zipPrompt = new JLabel("Zip Code");
        JTextField zipField = new JTextField(10);

        JLabel statePrompt = new JLabel("State");
        JTextField stateField = new JTextField(10);

        JLabel phoneNumberPrompt = new JLabel("Phone Number");
        JTextField phoneNumberField = new JTextField(10);

        panel.add(fNamePrompt, "cell 0 0, growx");
        panel.add(fNameField, "cell 0 1, growx");

        panel.add(lNamePrompt, "cell 1 0, growx");
        panel.add(lNameField, "cell 1 1, growx");

        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }
    public JFrame buildEditCustInfo(String name, String address){
        frame.setTitle("Edit Profile Information");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());

        return frame;
    }
}
