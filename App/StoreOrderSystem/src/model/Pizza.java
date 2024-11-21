package model;

import java.util.*;

/**
 * Represents a customizable pizza, including crust details, toppings, sauce preference, and price.
 * <p>
 * The class provides functionality to calculate the price of the pizza based on its size,
 * crust type, and toppings.
 * </p>
 */
public class Pizza{

    private String crustSize;
    private String crustType;
    private ArrayList<Topping> toppingAr = new ArrayList<Topping> ();
    private boolean isSauce;
    private double price;

    /**
     * Default constructor for the {@code Pizza} class.
     * <p>
     * Required for jackson
     * </p>
     */
    public Pizza (){}

    /**
     * Parameterized constructor for creating a {@code Pizza} with specified details.
     *
     * @param crustSize The size of the pizza crust (e.g., "small", "medium", "large").
     * @param crustType The type of the pizza crust (e.g., "thin", "thick").
     * @param toppingAr A list of toppings on the pizza.
     * @param isSauce Whether the pizza includes sauce.
     * @param price The price of the pizza.
     */
    public Pizza (String crustSize, String crustType, ArrayList<Topping> toppingAr, boolean isSauce, double price){
        this.crustSize = crustSize;
        this.crustType = crustType;
        this.toppingAr = toppingAr;
        this.isSauce = isSauce;
        this.price = price;
    }

    /**
     * Calculates the price of the pizza based on its size, crust type, and toppings.
     * <p>
     * The price calculation factors in the base cost of the crust size, a multiplier for toppings,
     * and whether cheese is included as a topping.
     * </p>
     */
    public void calculatePrice(){
        setPrice(0);
        if(!crustSize.isEmpty() && !crustType.isEmpty()){
            double calcPrice = 0;
            double mult = 0;
            switch(crustSize){
                case "small":
                    calcPrice += 5.00;
                    mult = 0.75;
                    break;
                case "medium":
                    calcPrice += 7.00;
                    mult = 1.00;
                    break;
                case "large":
                    calcPrice += 9.00;
                    mult = 1.25;
                    break;
                case "extra":
                    calcPrice += 11.00;
                    mult = 1.5;
                    break;
            }
            boolean cheese = false;
            for(Topping t : toppingAr){
                if(t.getName().equals("Cheese")){
                    cheese = true;
                }
            }
            if(cheese && toppingAr.size() > 1){
                calcPrice += (toppingAr.size()-2) * mult;
            }
            else{
                if(toppingAr.size() >0){
                    calcPrice += (toppingAr.size()-1) * mult;
                }

            }

            setPrice(calcPrice);
        }
    }

    /**
     * Retrieves the size of the pizza crust.
     *
     * @return The crust size as a {@link String}.
     */
    public String getCrustSize(){
        return this.crustSize;
    }

    /**
     * Updates the size of the pizza crust.
     *
     * @param crustSize The new crust size to set (e.g., "small", "medium").
     */
    public void setCrustSize(String crustSize){
        this.crustSize = crustSize;
    }

    /**
     * Retrieves the type of the pizza crust.
     *
     * @return The crust type as a {@link String}.
     */
    public String getCrustType(){
        return this.crustType;
    }

    /**
     * Updates the type of the pizza crust.
     *
     * @param crustType The new crust type to set (e.g., "thin", "thick").
     */
    public void setCrustType(String crustType){
        this.crustType = crustType;
    }

    /**
     * Retrieves the list of toppings on the pizza.
     *
     * @return An {@link ArrayList} of {@link Topping} objects.
     */
    public ArrayList<Topping> getToppingAr(){
        return this.toppingAr;
    }

    /**
     * Updates the list of toppings on the pizza.
     *
     * @param toppingAr The new list of toppings to set.
     */
    public void setToppingAr(ArrayList<Topping> toppingAr){
        this.toppingAr = toppingAr;
    }

    /**
     * Retrieves whether the pizza includes sauce.
     *
     * @return {@code true} if the pizza includes sauce; {@code false} otherwise.
     */
    public boolean getIsSauce(){
        return this.isSauce;
    }

    /**
     * Updates whether the pizza includes sauce.
     *
     * @param isSauce {@code true} to include sauce; {@code false} otherwise.
     */
    public void setIsSauce(boolean isSauce){
        this.isSauce = isSauce;
    }

    /**
     * Retrieves the price of the pizza, calculating it if necessary.
     *
     * @return The price of the pizza as a {@code double}.
     */
    public double getPrice(){
        calculatePrice();
        return price;
    }

    /**
     * Updates the price of the pizza.
     *
     * @param price The new price to set.
     */
    public void setPrice(double price){
        this.price = price;
    }
}