package model;

/**
 * Represents a menu item, including details like name, quantity, and price.
 * <p>
 * This class allows for the management of a menu item in a menu, calculating
 * the price based on the quantity and item type.
 * </p>
 */
public class MenuItem {

    private int quantity;
    private String name;
    private double price;

    /**
     * Default constructor for the {@code MenuItem} class.
     * <p>
     * Required for Jackson
     * </p>
     */
    public MenuItem (){}

    /**
     * Parameterized constructor for creating a {@code MenuItem} with specified details.
     *
     * @param quantity The quantity of the menu item.
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     */
    public MenuItem (int quantity, String name, double price){

        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    /**
     * Calculates the price of the menu item based on its quantity and name.
     * <p>
     * The price is calculated according to predefined rates for specific items.
     * </p>
     */
    public void calculatePrice(){
        String item = getName();
        switch(item){
            case "Bread Sticks":
                setPrice(quantity * 4);
                break;
            case "Bread Stick Bites":
                setPrice(quantity * 2);
                break;
            case "Big Chocolate Chip Cookie":
                setPrice(quantity * 4);
                break;
            default:
                setPrice(quantity * 1.75);
        }
    }

    /**
     * Retrieves the quantity of the menu item.
     *
     * @return The quantity of the menu item as an {@code int}.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Updates the quantity of the menu item.
     *
     * @param quantity The new quantity to set.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Retrieves the name of the menu item.
     *
     * @return The name of the menu item as a {@link String}.
     */
    public String getName(){
        return name;
    }

    /**
     * Updates the name of the menu item.
     *
     * @param name The new name to set.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Retrieves the price of the menu item, calculating it if necessary.
     *
     * @return The price of the menu item as a {@code double}.
     */
    public double getPrice(){
        calculatePrice();
        return price;
    }

    /**
     * Updates the price of the menu item.
     *
     * @param price The new price to set.
     */
    public void setPrice(double price){
        this.price = price;
    }

}