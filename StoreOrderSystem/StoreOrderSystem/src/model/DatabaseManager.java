package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A manager class responsible for handling operations related to
 * employee and customer accounts stored in JSON files.
 */
public class DatabaseManager {

    ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Updates or creates an employee account in the database.
     *
     * @param employeeID The unique ID of the employee.
     * @param password The password to associate with the employee account.
     * @param update Whether to update an existing account (true) or create a new one (false).
     * @return {@code true} if the operation succeeded, {@code false} if the account already exists and {@code update} is {@code false}.
     * @throws IOException If an I/O error occurs while reading or writing the database file.
     */
    public boolean updateEmployeeAccount(String employeeID, String password, boolean update) throws IOException {
        File file = new File("Database/Employees.json");

        List<Employee> employees;
        employees = objectMapper.readValue(file, new TypeReference<ArrayList<Employee>>() {});

        Employee employee = new Employee();
        employee.setUsername(employeeID);
        employee.setPassword(password);

        Employee objective = null;
        for(Employee emp : employees) { // search employees for an account with this username
            if(emp.getUsername().equals(employeeID)) {
                objective = emp;
            }
        }

        // if update false and account exists return false
        // append account make changes if exists

        if(!update && objective != null) {
            return false;
        } else if (update && objective != null) {
            objective.setPassword(password);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employees);
            return true;
        } else {
            employees.add(employee);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employees);
            return true;
        }
    }


    /**
     * Updates or creates a customer account in the database.
     *
     * @param first The customer's first name.
     * @param last The customer's last name.
     * @param oldPhone The customer's old phone number (used for identification).
     * @param newPhone The customer's new phone number.
     * @param address The customer's address.
     * @param password The customer's password.
     * @param update Whether to update an existing account (true) or create a new one (false).
     * @return {@code true} if the operation succeeded, {@code false} if the account already exists and {@code update} is {@code false}.
     * @throws IOException If an I/O error occurs while reading or writing the database file.
     */
    public boolean updateCustomerAccount(String first, String last, String oldPhone, String newPhone, String address,
                                      String password, boolean update) throws IOException {
        File file = new File("Database/Customers.json");

        List<Customer> customers;
        customers = objectMapper.readValue(file, new TypeReference<ArrayList<Customer>>() {});

        Customer customer = new Customer();
        customer.setPhone(newPhone);
        customer.setName(first + " " + last);
        customer.setAddress(address);
        customer.setPassword(password);

        Customer objective = null;
        for(Customer cust : customers) { // search employees for an account with this username
            if(cust.getPhone().equals(oldPhone)) {
                objective = cust;
            }
        }

        // if update false and account exists return false
        // append account make changes if exists

        if(!update && objective != null) {
            return false;
        } else if (update && objective != null) {
            objective.setPhone(newPhone);
            objective.setName(first + " " + last);
            objective.setAddress(address);
            objective.setPassword(password);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
            return true;
        } else {
            customers.add(customer);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
            return true;
        }
    }


    /**
     * Validates an employee's credentials.
     *
     * @param employeeID The unique ID of the employee.
     * @param password The password associated with the employee account.
     * @return The {@link Employee} object if validation is successful, or {@code null} if the credentials are invalid.
     */
    public Account validateEmployeeAccount(String employeeID, String password) {
        File file = new File("Database/Employees.json");
        System.out.println("File Found");
        Employee employee = null;
        try {
            List<Employee> employees;
            employees = objectMapper.readValue(file, new TypeReference<ArrayList<Employee>>() {});

            for(Employee emp : employees) {
                if(emp.getUsername().equals(employeeID) && emp.getPassword().equals(password)) {
                    employee = emp;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return employee;
    }


    /**
     * Validates a customer's credentials.
     *
     * @param phone The phone number associated with the customer's account.
     * @param password The password associated with the customer's account.
     * @return The {@link Customer} object if validation is successful, or {@code null} if the credentials are invalid.
     */
    public Account validateCustomerAccount(String phone, String password) {

        File file = new File("Database/Customers.json");
        System.out.println("File Found");
        Customer customer = null;
        try {
            List<Customer> customers;
            customers = objectMapper.readValue(file, new TypeReference<ArrayList<Customer>>() {});

            for(Customer cust : customers) {
                if(cust.getPhone().equals(phone) && cust.getPassword().equals(password)) {
                    customer = cust;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return customer;

    }

}