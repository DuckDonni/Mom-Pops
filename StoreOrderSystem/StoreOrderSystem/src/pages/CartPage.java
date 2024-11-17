package pages;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;
public class CartPage {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;

    public CartPage() {
    }

    public static JPanel returnPage() {
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
        JPanel rightPanel = new JPanel(new MigLayout());
        rightPanel.add(editCustBtn, "cell 3 1,wrap, align right");
        rightPanel.add(editOrderTimeBtn,"cell 3 2,wrap, align right");
        rightPanel.add(editPayment, "cell 3 3,wrap, align right");

        // Add buttons and scroll pane to main panel
//        panel.add(editCustBtn, "cell 3 1,wrap, align right");
//        panel.add(editOrderTimeBtn, "cell 3 2, wrap, align right");
//        panel.add(editPayment, "cell 3 3, wrap, align right");
        panel.add(rightPanel, "east");
        panel.add(scrollPane, "west"); // Span scroll pane across multiple cells

        return panel;
    }
}
