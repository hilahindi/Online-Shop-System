//Developed by Hila & Yuval
//Keren's class
package YuvalAndHila;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);

    // option1 - add seller
    public static void option1(SystemManager systemManager) {
        String username, password;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Enter seller username");
            username = s.next();
            while (systemManager.isSellerNameExist(username)) {
                System.out.println("This username is already exist! \nEnter another username, For back to Manu enter 0:");
                username = s.next();
                if (username.equals("0")) {
                    return;
                }
            }
            System.out.println("Enter a password");
            password = s.next();

            Seller newSeller = new Seller(username, password);

            boolean res = systemManager.addSeller(newSeller);
            if (res) {
                isValidInput = true;
                System.out.println("Seller added successfully ! ");
            }
        }
    }

    // option2 - add buyer
    public static void option2(SystemManager systemManager) {
        String username , password;
        String country, city, street, number;
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Enter buyer username");
            username = s.next();

            while (systemManager.isBuyerNameExist(username)) {
                System.out.println("This username is already exist!! \nEnter another username, For back to Manu enter 0:");
                username = s.next();
                if (username.equals("0")) {
                    return;
                }
            }
            System.out.println("Enter a password");
            password = s.next();

            System.out.println("Enter your address:");
            System.out.println("Enter your country name");
            country = s.next();
            System.out.println("Enter your city name");
            city = s.next();
            System.out.println("Enter your street name");
            street = s.next();
            System.out.println("Enter your building's number");
            number = s.next();

            Address address = new Address(street, number, city, country);
            Buyer newBuyer = new Buyer(username, password, address);
            boolean res = systemManager.addBuyer(newBuyer);
            if (res) {
                isValidInput = true;
                System.out.println("Buyer added successfully ! ");
            }
        }
    }

    // option3 - add product to seller
    public static void option3(SystemManager systemManager) throws CloneNotSupportedException{
        boolean isValidInput = false;
        if (systemManager.getSellersSize() == 0)
        {
            System.out.println("There are no sellers in the system, please add sellers before");
            return;
        }
        while (!isValidInput) {
            System.out.println("Choose seller username for adding product");
            String sellerUsername = s.next();

            while (!systemManager.isSellerNameExist(sellerUsername)) {
                System.out.println("This seller does not exist! \nEnter another seller, For back to Manu enter 0:");
                sellerUsername = s.next();
                if (sellerUsername.equals("0")) {
                    return;
                }
            }
            System.out.println("Enter name of product to add ");
            String nameProduct = s.next();
            double price = 0;
            boolean validPrice = false;
            while(!validPrice) {
                try {
                    System.out.println("Enter price of the product ");
                    price = s.nextDouble();
                    validPrice = true;
                }
                catch (InputMismatchException e){
                    System.out.println("The price must be numbers ");
                    s.next();
                }
                catch(Exception e)
                {
                    System.out.println("General exception: " +e.getMessage());
                }
            }
            Product.eCategory category = null;
            boolean validCategoty = false;
            while(!validCategoty) {
                try {
                    System.out.println("Enter the category of the product ");
                    systemManager.printCategories();
                    category = Product.eCategory.valueOf(s.next());
                    validCategoty = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("This category doesn't found. please enter the category as it written ");
                }
                catch(Exception e)
                {
                    System.out.println("General exception: " +e.getMessage());
                }
            }
            Product product = new Product(nameProduct,price,category,sellerUsername);

            char specialPackage;
            boolean validChar = false;
            while(!validChar) {
                    System.out.println("Do you want to add option for special package for this products? y/n");
                    specialPackage = s.next().charAt(0);
                if (specialPackage == 'y'){
                    double priceForSpecialPackage = 0;
                    boolean validPriceForSpecialPackage = false;
                    while(!validPriceForSpecialPackage)
                        try {
                            System.out.println("What is your price for special package?");
                            priceForSpecialPackage = s.nextDouble();
                            validPriceForSpecialPackage = true;
                        }
                        catch (InputMismatchException e){
                            System.out.println("The price must be numbers ");
                            s.next();
                        }
                        catch(Exception e)
                        {
                            System.out.println("General exception: " +e.getMessage());
                        }
                    product = new PackagedProduct(nameProduct,price,category,sellerUsername, priceForSpecialPackage);
                    validChar = true;
                }
                else if(specialPackage == 'n')
                {
                    validChar = true;
                }
                else{
                    System.out.println("The options are only 'y' for YES and 'n' for NO ");
                }
            }

            boolean res = systemManager.addProductToSeller(product);
            if (res) {
                isValidInput = true;
                System.out.println("Product added successfully ! ");
            }
        }
    }

    // option4 - add product to buyer
    public static void option4(SystemManager systemManager) throws CloneNotSupportedException {
        if (systemManager.getBuyersSize() == 0)
        {
            System.out.println("There are no buyers in the system, please add buyer before");
            return;
        }
        if (systemManager.getSellersSize() == 0)
        {
            System.out.println("There are no sellers to choose, please add sellers before");
            return;
        }
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Choose buyer username for adding product");
            String buyerUsername = s.next();
            while (!systemManager.isBuyerNameExist(buyerUsername)) {
                System.out.println("This buyer does not exist! \nEnter another buyer, For back to Manu enter 0:");
                buyerUsername = s.next();
                if (buyerUsername.equals("0")) {
                    return;
                }
            }
            System.out.println("Choose seller username from the list for adding product - by his index");
            systemManager.printSellersNames();// display all sellers
            int sellerIndex = 0;
            boolean isValidIndexSeller = false;
            while (!isValidIndexSeller){
                try {
                    sellerIndex = s.nextInt();
                    while (!systemManager.isSellerIndexExist(sellerIndex)) {
                        System.out.println("seller does not exist! \nEnter another seller, For back to Manu enter 0 :");
                        sellerIndex = s.nextInt();
                        if (sellerIndex ==0) {
                            return;
                        }
                    }
                    if (systemManager.printProductList(sellerIndex)){
                        System.out.println("Choose product from the list - by his index");
                    }
                    else {
                        System.out.println("This seller have no products yet");
                        return;
                    }
                    isValidIndexSeller = true;
                }
                catch (InputMismatchException e) {
                    System.out.println("The index must be number ");
                    s.next();
                } catch (Exception e) {
                    System.out.println("General exception: " + e.getMessage());
                }
            }


            int chosenProduct = 0;
           boolean isValidIndexProduct = false;
            while (!isValidIndexProduct) {
                try {
                     chosenProduct = s.nextInt();
                    while (!systemManager.isProductExist(chosenProduct, sellerIndex)) {
                        System.out.println("This product does not exist! \nEnter another product, For back to Manu enter 0:");
                        chosenProduct = s.nextInt();
                        if (chosenProduct == 0) {
                            return;
                        }
                    }
                    isValidIndexProduct = true;
                } catch (InputMismatchException e) {
                    System.out.println("The index must be number ");
                    s.next();
                } catch (Exception e) {
                    System.out.println("General exception: " + e.getMessage());
                }
            }
            boolean res = systemManager.addProductToBuyer(chosenProduct, buyerUsername, sellerIndex);
            if (res) {
                isValidInput = true;
                System.out.println("Product added successfully ! ");
            }
        }
    }

    // option5 - payment
    public static void option5(SystemManager systemManager)  {
        if (systemManager.getBuyersSize() == 0)
        {
            System.out.println("There are no buyers in the system, please add buyer before");
            return;
        }
        boolean isValidInput = false;
        while (!isValidInput) {
            System.out.println("Choose buyer username for payment, For back to Manu enter 0:");
            String buyerUsername = s.next();
            while (!systemManager.isBuyerNameExist(buyerUsername)) {
                if (buyerUsername.equals("0")) {
                    return;
                }
                System.out.println("This username does not exist! \nEnter another username, For back to Manu enter 0:");
                buyerUsername = s.next();
            }
           boolean res = false;
                for (int i = 0; i < systemManager.getBuyersSize(); i++) {
                    if (systemManager.getBuyers()[i].getUsername().equals(buyerUsername) && !systemManager.cartIsEmpty(buyerUsername)) {
                            System.out.println("Your total price is: ");
                            System.out.println(systemManager.getBuyers()[i].getBuyerOrder().calculateTotalPrice());

                    }
                }
            try {
                res = systemManager.payment(buyerUsername);
            }
            catch (EmptyCartExeption e) {
                System.out.println(e.getMessage());
                return;
            }
            catch(Exception e)
            {
                System.out.println("General exception: " +e.getMessage());
            }
            if (res) {
                isValidInput = true;
                System.out.println("The order paid successfully ! ");
            }
        }
    }
    // option6
    public static void option6(SystemManager systemManager) {
        if(!systemManager.printBuyersInformation()){
            System.out.println("There are no buyers to display, add buyers before");
        }
    }

    // option7
    public static void option7(SystemManager systemManager) {
        if(!systemManager.printSellersInformation())
        {
            System.out.println("There are no sellers to display, add sellers before");
        }
    }

    // option8
    public static void option8(SystemManager systemManager) {
        Product.eCategory category = null;
        boolean validCategoty = false;
        while (!validCategoty) {
            try {
                System.out.println("Choose one of the following category -");
                systemManager.printCategories();
                category = Product.eCategory.valueOf(s.next());
                validCategoty = true;

            } catch (IllegalArgumentException e) {
                System.out.println("This category not found. please enter the category as it written ");
            }
            catch(Exception e)
            {
                System.out.println("General exception: " +e.getMessage());
            }
        }
        System.out.println("All the product from this category are : ");
        boolean res = systemManager.printProductsFromCategory(category);
        if (!res) {
            System.out.println("There are no products from this category ");
        }
    }
    // option9
    public static void option9(SystemManager systemManager) {
        if (systemManager.getBuyersSize() == 0) {
            System.out.println("There are no buyers in the system, please add buyer before");
            return;
        }
            System.out.println("Choose buyer username:");
            String buyerUsername = s.next();
            while (!systemManager.isBuyerNameExist(buyerUsername)) {
                System.out.println("This buyer does not exist! \nEnter another buyer, For back to Manu enter 0:");
                buyerUsername = s.next();
                if (buyerUsername.equals("0")) {
                    return;
                }
            }
            boolean res = systemManager.cartIsEmpty(buyerUsername);
            if (!res) {
                boolean validChar = false;
                while (!validChar) {
                    System.out.println("You have products in your current cart. Are you sure you want to replace it? y/n");
                    char replace = s.next().charAt(0);
                    if (replace == 'y') {
                        replaceCart(systemManager, buyerUsername);
                        validChar = true;
                    } else if (replace == 'n') {
                        validChar = true;
                    } else {
                        System.out.println("The options are only 'y' for YES and 'n' for NO ");
                    }
                }
            }
            else {
                replaceCart(systemManager,buyerUsername);
            }
    }
    public static void replaceCart(SystemManager systemManager, String buyerUsername) {
        boolean run = false;
        while (!run) {
            if(!systemManager.printHistoryOrders(buyerUsername)) {
                System.out.println("There are no orders in history orders to replace with");
                return;
            }
            System.out.println("Choose the cart you want to replace with - enter the number in the list(index) :");
            int index = s.nextInt();
            boolean index_num_exist = systemManager.isIndexExist(buyerUsername, index);
                if (!index_num_exist) {
                    System.out.println("this buyer dont have this order, please try again");
                } else {
                    System.out.println("Your current cart changed successfully ! ");
                    run = true;
                }
            }
        }
    //Menu
    public static void menu(SystemManager systemManager) throws CloneNotSupportedException {
    int answer = 0;
        do
        {
        boolean validAnswer = false;
        while (!validAnswer) {
            try {
                System.out.println();
                System.out.println("Choose one of the following options:");
                System.out.println("1) Add seller name");
                System.out.println("2) Add buyer name");
                System.out.println("3) Add product to seller");
                System.out.println("4) Add product to buyer");
                System.out.println("5) Shopping cart payment");
                System.out.println("6) Show all buyers");
                System.out.println("7) Show all sellers");
                System.out.println("8) Show all the products from certain category");
                System.out.println("9) Create new cart from history orders");
                System.out.println("For Exit - choose 0 ");
                System.out.println("Enter your choice --> ");

                answer = s.nextInt();
                s.nextLine();
                validAnswer = true;
            } catch (InputMismatchException e) {
                System.out.println("The chosen option must be number ");
                s.next();
            }
            catch(Exception e)
            {
                System.out.println("General exception: " +e.getMessage());
            }
        }
        switch (answer) {
            case 1:
                    option1(systemManager);
                break;
            case 2:
                option2(systemManager);
                break;
            case 3:
                    option3(systemManager);
                break;
            case 4:
                option4(systemManager);
                break;
            case 5:
                option5(systemManager);
                break;
            case 6:
                option6(systemManager);
                break;
            case 7:
                option7(systemManager);
                break;
            case 8:
                option8(systemManager);
                break;
            case 9:
                option9(systemManager);
                break;
            case 0:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid option - please enter only one of the option above");
                break;
        }
    } while(answer !=0);
}
    public static void test(SystemManager systemManager) throws CloneNotSupportedException{
        //sellers
        systemManager.addSeller(new Seller("hila","123"));
        systemManager.addSeller(new Seller("yuval","123"));
        systemManager.addSeller(new Seller("keren","123"));
        systemManager.addSeller(new Seller("vered","123"));
        systemManager.addSeller(new Seller("shelly","123"));
        //buyers
        systemManager.addBuyer(new Buyer("amit","123", new Address("habonim", "3", "hadera","Israel")));
        systemManager.addBuyer(new Buyer("liron","123", new Address("ezel", "6", "pt","Israel")));
        systemManager.addBuyer(new Buyer("ran","123", new Address("harav-gold", "3", "natanya","Israel")));
        systemManager.addBuyer(new Buyer("ofir","123", new Address("al kanfei nesharim", "3", "tel-aviv","Israel")));
        systemManager.addBuyer(new Buyer("dana","123", new Address("hadolev", "3", "haifa","Israel")));
        //products to seller
        systemManager.addProductToSeller(new Product("tv", 2300, Product.eCategory.valueOf("Electricity"),"hila"));
        systemManager.addProductToSeller(new Product("t-shirt", 50, Product.eCategory.valueOf("Kids"),"hila"));
        systemManager.addProductToSeller(new Product("pen", 10, Product.eCategory.valueOf("Office"),"hila"));
        systemManager.addProductToSeller(new Product("tv", 2300, Product.eCategory.valueOf("Electricity"),"hila"));
        systemManager.addProductToSeller(new Product("tv", 2300, Product.eCategory.valueOf("Electricity"),"hila"));
        systemManager.addProductToSeller(new Product("tv", 2300, Product.eCategory.valueOf("Electricity"),"hila"));


        systemManager.addProductToSeller(new Product("phone", 4000, Product.eCategory.valueOf("Electricity"),"yuval"));
        systemManager.addProductToSeller(new Product("ipad", 2500, Product.eCategory.valueOf("Electricity"),"yuval"));
        systemManager.addProductToSeller(new Product("toy", 35, Product.eCategory.valueOf("Kids"),"yuval"));

        systemManager.addProductToSeller(new Product("shoes", 500, Product.eCategory.valueOf("Clothing"),"vered"));
        systemManager.addProductToSeller(new Product("dress", 100, Product.eCategory.valueOf("Kids"),"vered"));
        systemManager.addProductToSeller(new Product("paper", 100, Product.eCategory.valueOf("Office"),"vered"));
        systemManager.addProductToSeller(new Product("paper", 100, Product.eCategory.valueOf("Office"),"vered"));
        systemManager.addProductToSeller(new Product("paper", 100, Product.eCategory.valueOf("Office"),"vered"));
        systemManager.addProductToSeller(new Product("paper", 100, Product.eCategory.valueOf("Office"),"vered"));

        systemManager.addProductToSeller(new Product("doll", 30, Product.eCategory.valueOf("Kids"),"keren"));
        systemManager.addProductToSeller(new Product("body-cream", 43, Product.eCategory.valueOf("Kids"),"keren"));
        systemManager.addProductToSeller(new Product("computer", 3500, Product.eCategory.valueOf("Electricity"),"keren"));

        systemManager.addProductToSeller(new Product("chair", 450, Product.eCategory.valueOf("Office"),"shelly"));
        systemManager.addProductToSeller(new Product("pencil", 5, Product.eCategory.valueOf("Office"),"shelly"));
        systemManager.addProductToSeller(new Product("table", 1420, Product.eCategory.valueOf("Office"),"shelly"));
        systemManager.addProductToSeller(new Product("table", 1420, Product.eCategory.valueOf("Office"),"shelly"));

        systemManager.addProductToBuyer(1, "liron",1);
        systemManager.addProductToBuyer(4, "liron",1);


    }

    public static void main(String[] args) throws CloneNotSupportedException{
        SystemManager systemManager = new SystemManager();
        System.out.println("Welcome to our interface ! ");
//        test(systemManager);
        menu(systemManager);
    }


}

