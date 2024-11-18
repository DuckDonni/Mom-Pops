package view;

import controller.*;
public class View {
    private CustomerView customerView;
    private StaffView staffView;
    public static Controller controller;
    public View(Controller controller) {
        this.controller = controller;
        customerView = new CustomerView(this);
        staffView = new StaffView();
    }

    // Method to swap views based on an integer
    public void swapView(int option) {

        if (option == 0) {
            staffView.getFrame().setVisible(false); // Hide staff view
            customerView.getFrame().setVisible(true); // Show customer view
        } else if (option == 1) {
            customerView.getFrame().setVisible(false); // Hide customer view
            staffView.getFrame().setVisible(true); // Show staff view
        }
    }

//    public static void main(String[] args) {
//        View view = new View(new Controller());
//        view.swapView(0); // Initially shows view.CustomerView (you can toggle this)
//    }
}
