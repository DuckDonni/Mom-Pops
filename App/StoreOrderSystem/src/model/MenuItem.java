package model;

public class MenuItem {

    private int quantity;
    private String name;
    private double price;

    public MenuItem (){}

    public MenuItem (int quantity, String name, double price){

        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }
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

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        calculatePrice();
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

}