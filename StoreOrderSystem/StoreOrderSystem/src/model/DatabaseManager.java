package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DatabaseManager {

    ObjectMapper objectMapper = new ObjectMapper();

    // !! Java Docs comments
    public boolean validateCustomerAccount(String phone, String password) {
        // !! Returns true if the customer account exists and the password matches

        Customer customer = new Customer();

        try {

            customer = objectMapper.readValue(new File("Database/Customers.json"), Customer.class);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return customer.getPhone().equals(phone) && customer.getPassword().equals(password);
    }

    public boolean validateEmployeeAccount(String employeeID, String password) {
        // !! Returns true if the customer account exists and the password matches

        Employee employee = new Employee();

        try {

            employee = objectMapper.readValue(new File("Database/Employees.json"), Employee.class);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return employee.getUsername().equals(employeeID) && employee.getPassword().equals(password);
    }
}