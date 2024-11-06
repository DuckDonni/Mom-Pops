package ModelClasses;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Model {

    public static void main(String[] args) {
        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the JSON array and map it to a List of Person objects
            List<Person> people = objectMapper.readValue(new File("src/ModelClasses/Database.json"), new TypeReference<List<Person>>(){});

            // Print out the people
            for (Person person : people) {
                System.out.println("Name: " + person.getName());
                System.out.println("Age: " + person.getAge());
                System.out.println("Email: " + person.getEmail());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Person {
    private String name;
    private int age;
    private String email;

    // Default constructor (required for Jackson)
    public Person() {}

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


