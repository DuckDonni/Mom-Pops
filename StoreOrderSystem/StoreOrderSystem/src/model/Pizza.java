package model;

import java.util.*;

public class Pizza{

    private String crustSize;
    private String crustType;
    private ArrayList<Topping> toppingAr = new ArrayList<Topping> ();
    private boolean isSauce;
    private double price;

    public Pizza (){}

    public Pizza (String crustSize, String crustType, ArrayList<Topping> toppingAr, boolean isSauce, double price){
        this.crustSize = crustSize;
        this.crustType = crustType;
        this.toppingAr = toppingAr;
        this.isSauce = isSauce;
        this.price = price;
    }

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
            calcPrice += (toppingAr.size()-1) * mult;
            setPrice(calcPrice);
        }
    }
    public String getCrustSize(){
        return this.crustSize;
    }

    public void setCrustSize(String crustSize){
        this.crustSize = crustSize;
    }

    public String getCrustType(){
        return this.crustType;
    }

    public void setCrustType(String crustType){
        this.crustType = crustType;
    }

    public ArrayList<Topping> getToppingAr(){
        return this.toppingAr;
    }

    public void setToppingAr(ArrayList<Topping> toppingAr){
        this.toppingAr = toppingAr;
    }

    public boolean getIsSauce(){
        return this.isSauce;
    }

    public void setIsSauce(boolean isSauce){
        this.isSauce = isSauce;
    }

    public double getPrice(){
        calculatePrice();
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }
}