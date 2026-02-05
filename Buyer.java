package YuvalAndHila;

public class Buyer implements Comparable<Buyer>  {
    private String username;
    private String password;
    private Address address;
    private Order buyerOrder;
    private Order[] historyOrders;
    private int ordersSize;

    public Buyer(String username, String password, Address address) {
        setUsername(username);
        setPassword(password);
        this.address = new Address(address);
        this.buyerOrder = new Order();
        this.historyOrders = new Order[0];
        this.ordersSize = 0;
    }

    public Buyer(Buyer other) {
        this.username = other.username;
        this.password = other.password;
        this.address = other.address;
        this.buyerOrder = new Order();
        this.ordersSize = other.ordersSize;
        this.historyOrders = new Order[other.ordersSize];
        for (int i = 0; i < ordersSize; i++) {
            this.historyOrders[i] = new Order(other.historyOrders[i]);
        }
    }

    public void addProductToBuyer(Product product) throws CloneNotSupportedException{
        buyerOrder.getCart().addProduct(product);
    }

    public void addOrderToHistory() {
        historyOrders = duplicateOrdersHistoryArraySize(historyOrders, ordersSize);
        historyOrders[ordersSize] = this.buyerOrder;
        ordersSize++;
        setBuyerOrder();
    }

    //duplicate the arr
    public Order[] duplicateOrdersHistoryArraySize (Order[]arr,int size){
        if (size < arr.length) {
            return arr;
        }
        // Double the size
        int newSize = arr.length * 2;
        newSize = Math.max(2, newSize);
        Order[] temp = new Order[newSize];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        return arr;
    }

    public void printHistoryOrdersByBuyer(){
        for (int i = 0; i < ordersSize; i++) {
            System.out.println(i+1+")" + historyOrders[i]);
        }
    }
        public boolean replaceCurrentCart(int index) throws CloneNotSupportedException {
            for (int i = 0; i < ordersSize; i++) {
            if (i == index-1) {
                buyerOrder.setCart(historyOrders[i].getCart().clone());
                return true;
            }
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public int getOrdersSize() {
        return ordersSize;
    }

    public Order getBuyerOrder() {
        return buyerOrder;
    }

    public void setBuyerOrder() {
        this.buyerOrder = new Order();
    }

    public Order[] getHistoryOrders() {
        return historyOrders;
    }

    @Override
    public String toString() {
        return "Buyer:" +
                "\nUsername:" + username   +
                "\nPassword:" + password  +
                "\nAddress:" + address +
                "\nCurrent cart:\n" + PrintArray.toString(buyerOrder.getCart().getProducts(), buyerOrder.getCart().getSize()) +
                "\nHistory Orders:\n" + PrintArray.toString(historyOrders,ordersSize)+"\n";
    }
    public int compareTo(Buyer o) {
        return username.compareTo(o.username);
    }
}