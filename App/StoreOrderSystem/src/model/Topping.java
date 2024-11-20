package model;

/**
 * Represents a topping for a pizza, including its name and location.
 * <p>
 * The class provides getters and setters to manage the topping's details,
 * making it suitable for use in pizza customization.
 * </p>
 */
public class Topping{

    private String name;
    private String location;

    /**
     * Default constructor for the {@code Topping} class.
     * <p>
     * Required for Jackson
     * </p>
     */
    public Topping(){}

    /**
     * Parameterized constructor for creating a {@code Topping} with specified details.
     *
     * @param name The name of the topping (e.g., "Cheese", "Pepperoni").
     * @param location The location of the topping on the pizza (e.g., "top", "side").
     */
    public Topping(String name, String location){
        this.name = name;
        this.location = location;
    }

    /**
     * Retrieves the name of the topping.
     *
     * @return The name of the topping as a {@link String}.
     */
    public String getName(){
        return name;
    }

    /**
     * Updates the name of the topping.
     *
     * @param name The new name of the topping to set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Retrieves the location of the topping on the pizza.
     *
     * @return The location of the topping as a {@link String}.
     */
    public String getLocation(){
        return location;
    }

    /**
     * Updates the location of the topping on the pizza.
     *
     * @param location The new location of the topping to set.
     */
    public void setLocation(String location){
        this.location = location;
    }
}