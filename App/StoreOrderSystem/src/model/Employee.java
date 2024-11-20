package model;

/**
 * Represents an employee account, extending the {@link Account} class.
 * <p>
 * This class includes employee-specific details such as username and password.
 * It is compatible with JSON deserialization tools like ObjectMapper.
 * </p>
 */
public class Employee extends Account{

    private String username;
    private String password;

    /**
     * Default constructor for the {@code Employee} class.
     * <p>
     * Required for compatibility with JSON deserialization tools like ObjectMapper.
     * </p>
     */
    public Employee() {}

    /**
     * Parameterized constructor for creating an {@code Employee} with specified details.
     *
     * @param username The employee's username.
     * @param password The employee's password.
     */
    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the employee's username.
     *
     * @return The username of the employee as a {@link String}.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Updates the employee's username.
     *
     * @param username The new username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the employee's password.
     *
     * @return The password of the employee as a {@link String}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Updates the employee's password.
     *
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}