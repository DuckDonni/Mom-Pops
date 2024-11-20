package model;

/**
 * Represents a customer account, extending the {@link Account} class.
 * <p>
 * This class encapsulates customer-specific details such as their name,
 * phone number, address, and password.
 * </p>
 */
public class Customer extends Account {
    private String address;
    private String phone;
    private String password;
    private String name;

    /**
     * Default constructor for the {@code Customer} class.
     * <p>
     * Exists as a requirment for the Jackson json library.
     * </p>
     */
    public Customer() {}

    /**
     * Parameterized constructor for creating a {@code Customer} with specified details.
     *
     * @param phone The customer's phone number.
     * @param password The customer's password.
     * @param name The customer's full name.
     * @param address The customer's address.
     */
    public Customer(String phone, String password, String name, String address) {
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    /**
     * Retrieves the customer's phone number.
     *
     * @return The customer's phone number as a {@link String}.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Updates the customer's phone number.
     *
     * @param phone The new phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the customer's password.
     *
     * @return The customer's password as a {@link String}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the customer's password.
     *
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the customer's full name.
     *
     * @return The customer's name as a {@link String}.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the customer's name.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the customer's address.
     *
     * @return The customer's address as a {@link String}.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Updates the customer's address.
     *
     * @param address The new address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }


}