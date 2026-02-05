package YuvalAndHila;

import java.time.LocalDate;

public class Order {
    private LocalDate orderDate;
    private double totalPrice;
    private ProductsList cart;

    public Order() {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.cart = new ProductsList();
    }
    public Order(Order other) {
        this.orderDate = other.orderDate;
        this.totalPrice = other.totalPrice;
        this.cart = new ProductsList();
    }

    public double calculateTotalPrice() {
        for (int i = 0; i < cart.getSize(); i++) {
            totalPrice += cart.getProducts()[i].getPrice();
            }
        return totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ProductsList getCart() {
        return cart;
    }

    public void setCart(ProductsList cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return " Date:" + orderDate + " |" + " Total price:" + totalPrice+
                "\n   Shopping cart:\n" + PrintArray.toString(cart.getProducts(),cart.getSize()) + "\n";
    }
}
