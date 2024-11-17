package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    ObjectMapper objectMapper = new ObjectMapper();


    public boolean validateCustomerAccount(String phone, String password) {
        Customer customer = new Customer();
        try {

            customer = objectMapper.readValue(new File("Database/Customers.json"), Customer.class);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return customer.getPhone().equals(phone) && customer.getPassword().equals(password);
    }

    public boolean validateEmployeeAccount(String employeeID, String password) {
        Employee employee = new Employee();
        try {

            employee = objectMapper.readValue(new File("Database/Employees.json"), Employee.class);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return employee.getUsername().equals(employeeID) && employee.getPassword().equals(password);
    }

    // employees never need to change their employee id
    public void updateEmployeeAccount(String employeeID, String password) throws IOException {
        File file = new File("Database/Employees.json");

        List<Employee> employees;
        employees = objectMapper.readValue(file, new TypeReference<ArrayList<Employee>>() {});

        Employee employee = new Employee();
        employee.setUsername(employeeID);
        employee.setPassword(password);

        for(Employee emp : employees) { // search employees for an account with this username
            if(emp.getUsername().equals(employeeID)) {
                emp.setPassword(password);
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employees);
                return;
            }
        }

        employees.add(employee);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employees);
    }


    // changed from documentation in order to allow customer to change their phone number
    public void updateCustomerAccount(String first, String last, String oldPhone, String newPhone, String address, String password) throws IOException {
        File file = new File("Database/Customers.json");

        List<Customer> customers;
        customers = objectMapper.readValue(file, new TypeReference<ArrayList<Customer>>() {});

        Customer customer = new Customer();
        customer.setPhone(newPhone);
        customer.setName(first + " " + last);
        customer.setAddress(address);
        customer.setPassword(password);

        for(Customer cust : customers) { // search employees for an account with this username
            if(cust.getPhone().equals(oldPhone)) {
                cust.setPhone(newPhone);
                cust.setName(first + " " + last);
                cust.setAddress(address);
                cust.setPassword(password);
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
                return;
            }
        }

        customers.add(customer);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, customers);
    }
}