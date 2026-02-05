package YuvalAndHila;

import java.util.Arrays;

public class ProductsList implements Cloneable{
    private Product[] products;
    private int size;

    public ProductsList() {
        this.products = new Product[0];
        this.size = 0;
    }

    public void addProduct(Product product) throws CloneNotSupportedException {
        products =  duplicateCartArraySize(products,size);
        products[size] = product.clone();
        size++;
    }

    //duplicate the arr
    public Product[] duplicateCartArraySize (Product[]arr,int size){
        if (size < arr.length) {
            return arr;
        }
        // Double the size
        int newSize = arr.length * 2;
        newSize = Math.max(2, newSize);
        Product[] temp = new Product[newSize];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        return arr;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getSize() {
        return size;
    }

    public ProductsList clone() throws CloneNotSupportedException{
        return (ProductsList)super.clone();
    }

    }
