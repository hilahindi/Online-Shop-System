package YuvalAndHila;

public class Address {
    private String street;
    private String number;
    private String city;
    private String country;

    public Address(String street, String number, String city, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
    }
    public Address(Address other) {
        this.street = other.street;
        this.number = other.number;
        this.city = other.city;
        this.country = other.country;
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return  street + " " + number + "," + city + "," + country  ;
    }
}