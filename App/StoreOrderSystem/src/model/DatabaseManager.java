/**
 * The DatabaseManager class is responsible for managing and updating the user data stored in JSON files.
 * It provides methods for updating and validating employee and customer accounts.
 * This class uses Jackson's ObjectMapper for handling JSON serialization and deserialization.
 */
package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    // ObjectMapper instance for JSON processing
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Updates or adds an employee account in the database.
     *
     * @param employeeID the unique ID of the employee.
     * @param password   the password for the employee account.
     * @param update     if true, updates the existing employee account; if false, creates a new one if it doesn't exist.
     * @return true if the operation was successful, false otherwise.
     * @throws IOException if there is an error reading or writing to the database file.
     */
    public boolean updateEmployeeAccount(String employeeID, String password, boolean update) throws IOException {
        File file = new File("Database/Employees.json");
        List<Employee> employees = objectMapper.readValue(file, new TypeReference<ArrayList<Employee>>() {});

        Employee employee = new Employee();
        employee.setUsername(employeeID);
        employee.setPassword(password);

        Employee existingEmployee = null;
        for (Employee emp : employees) {
            if (emp.getUsername().equals(employeeID)) {
                existingEmployee = emp;
            }
        }

        if (!update && existingEmployee != null) {
            return false;
        } else if (update && existingEmployee != null) {
            existingEmployee.setPassword(password);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employees);
            return true;
        } else {
            employees.add(employee);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employees);
            return true;
        }
    }

    /**
     * Updates or adds a customer account in the database.
     *
     * @param first      the first name of the customer.
     * @param last       the last name of the customer.
     * @param oldPhone   the current phone number of the customer.
     * @param newPhone   the new phone number of the customer.
     * @param address    the address of the customer.
     * @param password   the password for the customer account.
     * @param update     if true, updates the existing customer account; if false, creates a new one if it doesn't exist.
     * @return true if the operation was successful, false otherwise.
     * @throws IOException if there is an error reading or writing to the database file.
     */
    public boolean updateCustomerAccount(String first, String last, String oldPhone, String newPhone, String address,
                                         String password, boolean update) throws IOException {
        File file = new File("Database/Customers.json");
        List<Customer> customers = objectMapper.readValue(file, new TypeReference<ArrayList<Customer>>() {});

        Customer customer = new Customer();
        customer.setPhone(newPhone);
        customer.setName(first + " " + last);
        customer.setAddress(address);
        customer.setPassword(password);

        Customer existingCustomer = null;
        for (Customer cust : customers) {
            if (cust.getPhone().equals(oldPhone)) {
                existingCustomer = cust;
            }
        }

        if (!update && existingCustomer != null) {
            return false;
        } else if (update && existingCustomer != null) {
            existingCustomer.setPhone(newPhone);
            existingCustomer.setName(first + " " + last);
            existingCustomer.setAddress(address);
            existingCustomer.setPassword(password);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
            return true;
        } else {
            customers.add(customer);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
            return true;
        }
    }

    /**
     * Validates an employee account using the provided ID and password.
     *
     * @param employeeID the unique ID of the employee.
     * @param password   the password for the employee account.
     * @return the Employee account if validation is successful, null otherwise.
     */
    public Account validateEmployeeAccount(String employeeID, String password) {
        File file = new File("Database/Employees.json");
        Employee employee = null;
        try {
            List<Employee> employees = objectMapper.readValue(file, new TypeReference<ArrayList<Employee>>() {});
            for (Employee emp : employees) {
                if (emp.getUsername().equals(employeeID) && emp.getPassword().equals(password)) {
                    employee = emp;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }

    /**
     * Validates a customer account using the provided phone number and password.
     *
     * @param phone    the phone number of the customer.
     * @param password the password for the customer account.
     * @return the Customer account if validation is successful, null otherwise.
     */
    public Account validateCustomerAccount(String phone, String password) {
        File file = new File("Database/Customers.json");
        Customer customer = null;
        try {
            List<Customer> customers = objectMapper.readValue(file, new TypeReference<ArrayList<Customer>>() {});
            for (Customer cust : customers) {
                if (cust.getPhone().equals(phone) && cust.getPassword().equals(password)) {
                    customer = cust;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }
}
