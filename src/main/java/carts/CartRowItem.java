package carts;

import products.Product;

import java.io.Serializable;

/**
 * Created by andgra on 2014-10-17.
 */
public class CartRowItem implements Serializable {
    private transient Product product;
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

    public String getName() {
        return product.getName();
    }

    public double getPriceIncVat() {
        return product.getPriceIncVat();
    }

    public double getVatPercentage() {
        return product.getVatPercentage();
    }

    public double getVatAmount() {
        return product.getVatAmount();
    }
}
