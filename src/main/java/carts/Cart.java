package carts;

import java.util.ArrayList;

/**
 * Created by andgra on 2014-10-10.
 */
public class Cart {
    private String id;
    private ArrayList<CartRowItem> rows;
    private double totalPriceIncVatAmount;
    private double totalVatAmount;

    public Cart(String id, ArrayList<CartRowItem> rows, double totalPriceIncVatAmount, double totalVatAmount){
        this.id = id;
        this.rows = rows;
        this.totalPriceIncVatAmount = totalPriceIncVatAmount;
        this.totalVatAmount = totalVatAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<CartRowItem> getRows() {
        return rows;
    }

    public void setRows(ArrayList<CartRowItem> rows) {
        this.rows = rows;
    }

    public double getTotalPriceIncVatAmount() {
        return totalPriceIncVatAmount;
    }

    public void setTotalPriceIncVatAmount(double totalPriceIncVatAmount) {
        this.totalPriceIncVatAmount = totalPriceIncVatAmount;
    }

    public double getTotalVatAmount() {
        return totalVatAmount;
    }

    public void setTotalVatAmount(double totalVatAmount) {
        this.totalVatAmount = totalVatAmount;
    }

    public double calculateTotalPriceIncVatAmount(){
        double totalPriceIncVat = 0;
        for (CartRowItem cartRowItem: rows){
            totalPriceIncVat = totalPriceIncVat + cartRowItem.getProduct().getPriceIncVat();

        }
        return totalPriceIncVat;
    }
    public double calculateTotalVatAmount(){
        double totalVatAmount = 0;
        for (CartRowItem cartRowItem: rows){
            totalVatAmount = totalVatAmount + cartRowItem.getProduct().getVatAmount();

        }
        return totalVatAmount;
    }

    public void addProductInRow(CartRowItem cartRowItem) {
        this.rows.add(cartRowItem);
    }
}
