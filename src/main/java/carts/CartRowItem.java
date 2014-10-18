package carts;

import products.Product;

import java.io.Serializable;

/**
 * Created by andgra on 2014-10-17.
 */
public class CartRowItem implements Serializable {
    private transient Product product;
    private String id;
    private String name;
    private double priceIncVat;
    private double vatPercentage;
    private double vatAmount;
    private double quantity;


    public CartRowItem(Product product, double quantity){
        this.product = product;
        this.quantity = quantity;

    }

    protected Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public String getId() {
        return product.getId();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceIncVat() {
        return product.getPriceIncVat();
    }

    public void setPriceIncVat(double priceIncVat) {
        this.priceIncVat = priceIncVat;
    }

    public double getVatPercentage() {
        return product.getVatPercentage();
    }

    public void setVatPercentage(double vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public double getVatAmount() {
        return product.getVatAmount();
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }
}
