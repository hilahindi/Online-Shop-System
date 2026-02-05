package YuvalAndHila;

public class Seller implements Comparable<Seller>{
    private String username;
    private String password;
    private ProductsList sellerProducts;

    public Seller(String username, String password) {
        setUsername(username);
        setPassword(password);
        this.sellerProducts = new ProductsList();
    }

    public Seller(Seller other) {
        this.username = other.username;
        this.password = other.password;
        this.sellerProducts = new ProductsList();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
            this.username = username;
        }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
            this.password = password;

    }

    public ProductsList getSellerProducts() {
        return sellerProducts;
    }


    public void printProductsCategory(Product.eCategory theCategory){
        for (int i = 0;i < sellerProducts.getSize(); i++) {
            if (sellerProducts.getProducts()[i].getTheCategory().equals(theCategory)) {
                System.out.print(" Seller:" + username +" |"+ sellerProducts.getProducts()[i]);// print product information
            }
        }
    }
        @Override
    public String toString() {
        return "Seller:" +
                "\nUsername:" + username   +
                "\nPassword:" + password  +
                "\nSeller products:\n" + PrintArray.toString(sellerProducts.getProducts(),sellerProducts.getSize());
    }

    @Override
    public int compareTo(Seller o) {
        if(sellerProducts.getSize()< o.sellerProducts.getSize()){
            return -1;
        }
        else if(sellerProducts.getSize()> o.sellerProducts.getSize()){
            return 1;
        }
        else {
            return 0;
        }
    }

}
