package YuvalAndHila;

public class EmptyCartExeption extends Exception{
    public static final String errorMessage = "The cart is empty - add products before";
    public EmptyCartExeption() {
        super(errorMessage);
    }

}
