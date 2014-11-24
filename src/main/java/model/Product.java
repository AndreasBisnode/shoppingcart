package model;

/**
 * Created by andgra on 2014-10-09.
 */

public class Product {
    private String id;
    private String name;
    private double priceIncVat;
    private double vatPercentage;
    private double vatAmount;

    public Product(){

    }
    public Product(String id, String name, double priceIncVat, double vatPercentage, double vatAmount){
        this.id = id;
        this.name = name;
        this.priceIncVat = priceIncVat;
        this.vatPercentage = vatPercentage;
        this.vatAmount = vatAmount;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceIncVat() {
        return priceIncVat;
    }

    public void setPriceIncVat(double priceIncVat) {
        this.priceIncVat = priceIncVat;
    }

    public double getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(double vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }
}
