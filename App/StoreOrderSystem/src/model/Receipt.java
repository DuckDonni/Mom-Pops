package model;

import java.awt.*;
import java.util.*;
import java.util.ArrayList;

/**
 * Represents a receipt for a customer's order at a pizzeria.
 * Contains details about the customer, order items, delivery status, and total price.
 */
public class Receipt {
    private String address;
    private String customerName;
    private String phoneNumber;
    private String payment;
    private Date date;
    private Boolean isDelivery;
    private double price;
    private ArrayList<Pizza> pizzaAr;
    private ArrayList<MenuItem> menuItemAr;

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
        date = new Date();
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
     * Gets the customer's address.
     * @return the address of the customer.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer's address.
     * @param address the address of the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the customer's name.
     * @return the name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the customer's name.
     * @param customerName the name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Gets the date and time of the receipt.
     * @return the date of the receipt.
     */
    public Date getDateTime() {
        return date;
    }

    /**
     * Sets the date and time of the receipt.
     * @param date the date to set.
     */
    public void setDateTime(Date date) {
        this.date = date;
    }

    /**
     * Gets the delivery status of the order.
     * @return true if the order is for delivery, false otherwise.
     */
    public Boolean getIsDelivery() {
        return isDelivery;
    }

    /**
     * Sets the delivery status of the order.
     * @param isDelivery true if the order is for delivery, false otherwise.
     */
    public void setIsDelivery(Boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    /**
     * Gets the total price of the order.
     * @return the total price of the order.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the total price of the order.
     * @param price the price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the list of pizzas in the order.
     * @return an ArrayList of Pizza objects.
     */
    public ArrayList<Pizza> getPizzaAr() {
        return pizzaAr;
    }

    /**
     * Sets the list of pizzas in the order.
     * Also recalculates the total price.
     * @param pizzaAr an ArrayList of Pizza objects.
     */
    public void setPizzaAr(ArrayList<Pizza> pizzaAr) {
        this.pizzaAr = pizzaAr;
        calculatePrice();
    }

    /**
     * Gets the list of menu items in the order.
     * @return an ArrayList of MenuItem objects.
     */
    public ArrayList<MenuItem> getMenuItemAr() {
        return menuItemAr;
    }

    /**
     * Sets the list of menu items in the order.
     * Also recalculates the total price.
     * @param menuItemAr an ArrayList of MenuItem objects.
     */
    public void setMenuItemAr(ArrayList<MenuItem> menuItemAr) {
        this.menuItemAr = menuItemAr;
        calculatePrice();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPayment(){
        return payment;
    }
    public void setPayment(String payment){
        this.payment = payment;
    }

}