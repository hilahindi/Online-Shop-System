package YuvalAndHila;

import java.util.Locale;

public class Product implements Cloneable { //  implements Cloneable
    protected String name;
    protected double price;
    protected String sellerName;

    protected static int counter;
    protected int serial_num;

    protected enum eCategory{Kids, Electricity,Office, Clothing };
    protected eCategory theCategory;

    public Product(String name, double price, eCategory theCategory, String sellerName) {
        this.name = name;
        this.price = price;
        this.sellerName = sellerName;
        serial_num = ++counter;
        this.theCategory = theCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String getSellerName() {
        return sellerName;
    }

    public static int getNumOfProducts(){
        return counter;
    }

    public eCategory getTheCategory() {
        return theCategory;
    }

    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }

    @Override
    public String toString() {
        return
                " Name:" + name + " |" + " Price:" + price + " |" + " Category:" + theCategory + " |" +
                " Serial Number:" +serial_num +  "\n";
    }
}
