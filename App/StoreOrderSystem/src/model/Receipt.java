package model;

import java.util.*;

/**
 * Represents a receipt for a customer's order at a pizzeria.
 * Contains details about the customer, order items, delivery status, and total price.
 */
public class Receipt {

    private String address;
    private String customerName;
    private String phoneNumber;
    private String payment;
    private Calendar date; // Stores the date and time of the receipt
    private Boolean isDelivery; // Indicates whether the order is for delivery
    private double price; // Total price of the order
    private ArrayList<Pizza> pizzaAr; // List of pizzas in the order
    private ArrayList<MenuItem> menuItemAr; // List of additional menu items in the order
    private String stringTime; // Represents time as a string

    /**
     * Constructs a Receipt with default values.
     * Address, customer name, phone number, and payment method are initialized to empty strings.
     * The date is set to the current date and time.
     * Delivery status is set to false.
     * Price is initialized to 0.0.
     * Pizza and menu item lists are initialized as empty.
     */
    public Receipt() {
        address = "";
        customerName = "";
        phoneNumber = "";
        payment = "";
        stringTime = "";
        date = Calendar.getInstance(); // Default to the current date and time
        isDelivery = false;
        price = 0.0;
        pizzaAr = new ArrayList<>();
        menuItemAr = new ArrayList<>();
    }

    /**
     * Calculates the total price of the order by summing the prices of pizzas and menu items.
     */
    public void calculatePrice() {
        price = 0;
        for (MenuItem item : menuItemAr) {
            price += item.getPrice();
        }
        for (Pizza pizza : pizzaAr) {
            price += pizza.getPrice();
        }
    }

    /**
     * Parses the stringTime field to set the date and time of the receipt.
     * The stringTime is expected to be in the format "HH:MM:AM/PM".
     * If the stringTime is "ASAP", no changes are made.
     */
    public void calculateTime() {
        String[] breakup = stringTime.split(":");

        if (breakup.length == 3 && !breakup[2].equals("ASAP")) {
            String hours = breakup[0];
            String minutes = breakup[1];
            String am_pm = breakup[2];
            Calendar cal = Calendar.getInstance();

            if (am_pm.equals("AM")) {
                cal.set(Calendar.AM_PM, Calendar.AM);
            } else {
                cal.set(Calendar.AM_PM, Calendar.PM);
            }
            int inc = 0;
            if (am_pm.equals("PM")) {
                inc = 12;
            }
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours) + inc);
            cal.set(Calendar.MINUTE, Integer.parseInt(minutes));

            setDateTime(cal);
        }
    }

    // Getters and Setters for all attributes

    /**
     * Gets the address associated with the receipt.
     *
     * @return the address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address for the receipt.
     *
     * @param address the customer's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the customer's name.
     *
     * @return the customer's name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer's name.
     *
     * @param customerName the customer's name.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the customer's phone number.
     *
     * @return the phone number of the customer.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phoneNumber the customer's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the payment method for the receipt.
     *
     * @return the payment method.
     */
    public String getPayment() {
        return payment;
    }

    /**
     * Sets the payment method for the receipt.
     *
     * @param payment the payment method.
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * Gets the delivery status of the order.
     *
     * @return true if the order is for delivery, false otherwise.
     */
    public Boolean getIsDelivery() {
        return isDelivery;
    }

    /**
     * Sets the delivery status of the order.
     *
     * @param isDelivery true if the order is for delivery, false otherwise.
     */
    public void setIsDelivery(Boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    /**
     * Gets the total price of the order.
     *
     * @return the total price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the total price of the order.
     *
     * @param price the total price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the list of pizzas in the order.
     *
     * @return the list of pizzas.
     */
    public ArrayList<Pizza> getPizzaAr() {
        return pizzaAr;
    }

    /**
     * Sets the list of pizzas in the order and recalculates the price.
     *
     * @param pizzaAr the list of pizzas.
     */
    public void setPizzaAr(ArrayList<Pizza> pizzaAr) {
        this.pizzaAr = pizzaAr;
        calculatePrice();
    }

    /**
     * Gets the list of additional menu items in the order.
     *
     * @return the list of menu items.
     */
    public ArrayList<MenuItem> getMenuItemAr() {
        return menuItemAr;
    }

    /**
     * Sets the list of additional menu items in the order and recalculates the price.
     *
     * @param menuItemAr the list of menu items.
     */
    public void setMenuItemAr(ArrayList<MenuItem> menuItemAr) {
        this.menuItemAr = menuItemAr;
        calculatePrice();
    }

    /**
     * Gets the date and time of the receipt as a Calendar object.
     *
     * @return the date and time.
     */
    public Calendar getDateTime() {
        return date;
    }

    /**
     * Sets the date and time of the receipt.
     *
     * @param date the date and time.
     */
    public void setDateTime(Calendar date) {
        this.date = date;
    }

    /**
     * Gets the time of the order as a string.
     *
     * @return the string representation of the time.
     */
    public String getStringTime() {
        return stringTime;
    }

    /**
     * Sets the time of the order as a string and updates the date field.
     *
     * @param stringTime the string representation of the time.
     */
    public void setStringTime(String stringTime) {
        this.stringTime = stringTime;
        calculateTime();
    }
}
