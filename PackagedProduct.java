package YuvalAndHila;

public class PackagedProduct extends Product{
    double specialPackagePrice;

    public PackagedProduct(String name, double price, eCategory theCategory, String sellerName, double specialPackagePrice) {
        super(name, price+specialPackagePrice, theCategory, sellerName);
        this.specialPackagePrice = specialPackagePrice;
    }

    public double getSpecialPackagePrice() {
        return specialPackagePrice;
    }

    public PackagedProduct clone() throws CloneNotSupportedException {
        return (PackagedProduct) super.clone();
    }

    @Override
    public String toString() {
        return super.toString()+ "this product have special package included in the price (" + specialPackagePrice + ")}";
    }
}
