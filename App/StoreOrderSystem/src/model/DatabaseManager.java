package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    ObjectMapper objectMapper = new ObjectMapper();


    // employees never need to change their employee id
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


    // changed from documentation in order to allow customer to change their phone number
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


    public Account validateEmployeeAccount(String employeeID, String password) {
        File file = new File("src/Database/Employees.json");
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

    public Account validateCustomerAccount(String phone, String password) {
        File file = new File("Database/Customers.json");
        Customer customer = null;
        try {
            List<Customer> customers;
            customers = objectMapper.readValue(file, new TypeReference<ArrayList<Customer>>() {});

            for(Customer cust : customers) {
                System.out.println(cust.getPhone());
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