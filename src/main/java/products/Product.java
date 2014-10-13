package products;

/**
 * Created by andgra on 2014-10-09.
 */

public class Product {
    private String id;
    private String name;
    private double priceIncVat;
    private double vatPercentage;
    private double vatAmount;

    protected Product(String id, String name, double priceIncVat, double vatPercentage, double vatAmount){
        this.id = id;
        this.name = name;
        this.priceIncVat = priceIncVat;
        this.vatPercentage = vatPercentage;
        this.vatAmount = vatAmount;
    }

    protected String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected double getPriceIncVat() {
        return priceIncVat;
    }

    protected void setPriceIncVat(double priceIncVat) {
        this.priceIncVat = priceIncVat;
    }

    protected double getVatPercentage() {
        return vatPercentage;
    }

    protected void setVatPercentage(double vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    protected double getVatAmount() {
        return vatAmount;
    }

    protected void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }
}
