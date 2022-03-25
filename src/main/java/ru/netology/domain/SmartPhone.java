package ru.netology.domain;

public class SmartPhone extends Product{
    private String manufacturer;

    public SmartPhone(String manufacturer) {
        super();
    }

    public SmartPhone(int id, String name, int price, String manufacturer) {
        super(id, name, price);
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
