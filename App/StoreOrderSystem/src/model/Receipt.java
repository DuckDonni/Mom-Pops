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
    private Calendar date; // Changed from Date to Calendar
    private Boolean isDelivery;
    private double price;
    private ArrayList<Pizza> pizzaAr;
    private ArrayList<MenuItem> menuItemAr;
    private String stringTime;
    /**
     * Constructs a Receipt with default values.
     * Address, customer name, and delivery status are initialized to default values.
     * Date is set to the current date and time.
     */
    public Receipt() {
        address = "";
        customerName = "";
        phoneNumber = "";
        payment = "";
        stringTime = "";
        date = Calendar.getInstance(); // Default to current time
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
    public void calculateTime(){
        String[] breakup = stringTime.split(":");
        if (breakup.length==3) {
            String hours = breakup[0];
            String minutes = breakup[1];
            String am_pm = breakup[2];
            Calendar cal = Calendar.getInstance();
            Calendar temp = Calendar.getInstance();
            cal.set(Calendar.HOUR, Integer.parseInt(hours));
            cal.set(Calendar.MINUTE, Integer.parseInt(minutes));


            if(am_pm.equals("AM")){
                cal.set(Calendar.AM_PM, Calendar.AM);
            }
            else{
                cal.set(Calendar.AM_PM, Calendar.PM);
            }
            setDateTime(cal);
        }

    }

    // Getters and Setters for all attributes
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Boolean getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(Boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Pizza> getPizzaAr() {
        return pizzaAr;
    }

    public void setPizzaAr(ArrayList<Pizza> pizzaAr) {
        this.pizzaAr = pizzaAr;
        calculatePrice();
    }

    public ArrayList<MenuItem> getMenuItemAr() {
        return menuItemAr;
    }

    public void setMenuItemAr(ArrayList<MenuItem> menuItemAr) {
        this.menuItemAr = menuItemAr;
        calculatePrice();
    }

    // Use Calendar to get and set the receipt date
    public Calendar getDateTime() {
        return date;
    }

    public void setDateTime(Calendar date) {
        this.date = date;
    }

    public String getStringTime(){
        return stringTime;
    }
    public void setStringTime(String stringTime){
        this.stringTime = stringTime;
        calculateTime();
    }

}
