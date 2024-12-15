package Models;

public class Product {

    private double Price;
    private String Name;
    private int Quantity;
    public Product(double price,String name,int quantity)
    {
        Quantity=quantity;
        Price=price;
        Name=name;
    }


    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }


}
