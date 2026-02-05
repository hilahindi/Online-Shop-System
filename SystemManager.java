package YuvalAndHila;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class SystemManager {
    private static Scanner s = new Scanner(System.in);

    private Buyer[] buyers;
    private int buyersSize = 0;

    private Seller[] sellers;
    private int sellersSize = 0;


    public SystemManager() {
        buyers = new Buyer[0];
        sellers = new Seller[0];
    }

    //case1
    public boolean addSeller(Seller seller) {
        if (!isSellerNameExist(seller.getUsername())) {
            sellers = duplicateSellersArraySize(sellers, sellersSize);
            sellers[sellersSize] = new Seller(seller);
            sellersSize++;
            return true;
        }
        return false;
    }

    //case2
    public boolean addBuyer(Buyer buyer) {
        if (!isBuyerNameExist(buyer.getUsername())) {
            buyers = duplicateBuyersArraySize(buyers, buyersSize);
            buyers[buyersSize] = new Buyer(buyer);
            ;
            buyersSize++;
            return true;
        }
        return false;
    }

    //case3
    public void printCategories() {
        Product.eCategory[] categories = Product.eCategory.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println(i + 1 + "." + categories[i].name());
        }
    }

    public boolean addProductToSeller(Product product) throws CloneNotSupportedException {
        if (sellersSize == 0) {
            return false;
        }
        for (int i = 0; i < sellersSize; i++) {
            if (sellers[i].getUsername().equals(product.getSellerName()) && sellersSize != 0) {
                sellers[i].getSellerProducts().addProduct(product);
                return true;
            }
        }
        return false;
    }

    //case4
    public void printSellersNames() {
        for (int i = 0; i < sellersSize; i++) {
            System.out.println(i + 1 + "." + sellers[i].getUsername());
        }
    }

    public boolean printProductList(int sellerIndex) {
        for (int i = 0; i < sellersSize; i++) {
            if (i == sellerIndex-1 && sellers[i].getSellerProducts().getSize() != 0) {
               System.out.println(PrintArray.toString(sellers[i].getSellerProducts().getProducts(),sellers[i].getSellerProducts().getSize()));
                return true;
            }
        }
        return false;
    }

public boolean addProductToBuyer(int productIndex, String buyerName, int sellerIndex) throws CloneNotSupportedException {
    if (buyersSize == 0) {
        return false;
    }
    // get product by index
    ProductsList productsList = null;
    int listSize = 0;
    Product productInTheList = null;

    for (int i = 0; i < sellersSize; i++) {
        if (i == sellerIndex-1) {
            productsList = sellers[i].getSellerProducts();
            listSize = sellers[i].getSellerProducts().getSize();
        }
    }

    for (int i = 0; i < listSize; i++) {
        if (i == productIndex-1) {
            productInTheList = productsList.getProducts()[i];
        }
    }
    if (productInTheList == null) {
        return false;
    }
    for (int i = 0; i < buyersSize; i++) {
        if (buyers[i].getUsername().equals(buyerName)) {
            buyers[i].addProductToBuyer(productInTheList);
            return true;
        }
    }
    return false;
}

    //case5
    public boolean payment(String buyerName)throws EmptyCartExeption {
        for (int i = 0; i < buyersSize; i++) {
            if (buyers[i].getUsername().equals(buyerName)) {
                if (buyers[i].getBuyerOrder().getCart().getSize() == 0) {
                    throw new EmptyCartExeption();
                }
                buyers[i].getBuyerOrder().setOrderDate(LocalDate.now());
                buyers[i].addOrderToHistory();
                return true;
            }
        }
        return false;
    }

    //case6
    public boolean printBuyersInformation() {
        if(buyersSize ==0){
            return false;
        }
        Buyer [] buyers_without_null= new Buyer[buyersSize];
        for (int i = 0; i < sellersSize; i++) { //Arrays.copyOf
            buyers_without_null[i] = buyers[i];
        }
        Arrays.sort(buyers_without_null);
        for (int i = 0; i < buyersSize; i++) {
            System.out.println(i+1+")"+buyers_without_null[i]); // print buyer information
        }
        return true;
    }

    //case7
    public boolean printSellersInformation() {
        if(sellersSize ==0){
            return false;
        }
        Seller [] sellers_without_null= new Seller[sellersSize];
        for (int i = 0; i < sellersSize; i++) { //Arrays.copyOf
            sellers_without_null[i] = sellers[i];
        }
        Arrays.sort(sellers_without_null);
        for (int i = 0; i < sellersSize; i++) {
            System.out.println(i+1+")"+sellers_without_null[i]);// print seller information
        }
        return true;
    }

    //case8
    public boolean printProductsFromCategory(Product.eCategory theCategory) {
        int counter = 0; //if there are no products from this category
        for (int i = 0; i < sellersSize; i++) {
            sellers[i].printProductsCategory(theCategory);
            counter++;
        }
        if (counter == 0) {
            return false;
        }
        return true;
    }

    //case9
    public boolean cartIsEmpty(String buyerName) {
        for (int i = 0; i < buyersSize; i++) {
            if (buyers[i].getUsername().equals(buyerName)) {
                if(buyers[i].getBuyerOrder().getCart().getSize()==0){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean printHistoryOrders(String buyerName) {
        for (int i = 0; i < buyersSize; i++) {
            if (buyers[i].getUsername().equals(buyerName)) {
                if (buyers[i].getOrdersSize() == 0) {
                    return false;
                }
                else {
                    buyers[i].printHistoryOrdersByBuyer();
                    return true;
                }
            }
        }
        return false;
    }

        public boolean isIndexExist(String buyerName , int index) {
            for (int i = 0; i < buyersSize; i++) {
            if (buyers[i].getUsername().equals(buyerName)) {
                try {
                    if (buyers[i].replaceCurrentCart(index)) {
                        return true;
                    }
                }
                catch(Exception e)
                {
                    System.out.println("General exception: " +e.getMessage());
                }
            }
        }
        return false;
    }

    //duplicate the arr
    public Seller[] duplicateSellersArraySize (Seller[]arr,int size){
        if (size < arr.length) {
            return arr;
        }
        // Double the size
        int newSize = arr.length * 2;
        newSize = Math.max(2, newSize);
        Seller[] temp = new Seller[newSize];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        return arr;
    }

    public Buyer[] duplicateBuyersArraySize (Buyer[]arr,int size){
        if (size < arr.length) {
            return arr;
        }
        // Double the size
        int newSize = arr.length * 2;
        newSize = Math.max(2, newSize);
        Buyer[] temp = new Buyer[newSize];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        return arr;
    }

    //Is Exist
    public Boolean isSellerNameExist(String username){
        for (int i = 0; i < sellersSize; i++) {
            if (sellers[i] != null && sellers[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public Boolean isSellerIndexExist(int index){
        for (int i = 0; i < sellersSize; i++) {
            if (i == index-1) {
                return true;
            }
        }
        return false;
    }

    public Boolean isBuyerNameExist (String username){
        for (int i = 0; i < buyersSize; i++) {
            if (buyers[i] != null && buyers[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isProductExist (int productIndex , int sellerIndex){
        for (int i = 0; i < sellersSize ; i++) {
            if (sellers[i] != null && i == sellerIndex-1) {
                for (int j = 0; j < sellers[i].getSellerProducts().getSize(); j++){
                    if(productIndex-1 == j )
                    {
                        return true;
                    }
                }
            }
        }
        return false;
  }

    public int getBuyersSize() {
        return buyersSize;
    }

    public int getSellersSize() {
        return sellersSize;
    }

    public Buyer[] getBuyers() {
        return buyers;
    }

    public Seller[] getSellers() {
        return sellers;
    }


}

