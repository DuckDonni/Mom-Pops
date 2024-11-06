package ModelClasses;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOError;
import java.io.IOException;


public class Model {

    public static void main(String[] args) throws JsonProcessingException {

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        Person person = new Person("Robert", 20);

        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);

        json = "{\"name\":\"Will\", \"age\":22}";
        person = objectMapper.readValue(json, Person.class); // must have default constructor
        System.out.println(person.getName());

        // writing to file example
        try {
            objectMapper.writeValue(new File("ModelClasses/person.json"), person);
            System.out.println("Success");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Person {
    String name;
    int age;

    public Person() {}
    public Person(String name, int age) {this.name = name; this.age = age;}

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
}

