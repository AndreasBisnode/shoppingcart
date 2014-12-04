package model;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by andgra on 2014-10-10.
 */
public class Cart {

    private final String id;
    private ArrayList<CartRowItem> rows;
    private double totalPriceIncVatAmount;
    private double totalVatAmount;

    @JsonCreator
    public Cart(@JsonProperty("id") String id, @JsonProperty("rows")ArrayList<CartRowItem> rows , @JsonProperty("totalVatAmount")double totalVatAmount, @JsonProperty("totalPriceIncVatAmount")double totalPriceIncVatAmount ){
        this.id = id;
        this.rows = rows;
        this.totalPriceIncVatAmount = totalPriceIncVatAmount;
        this.totalVatAmount = totalVatAmount;
    }

    public String getId() {
        return id;
    }

    public ArrayList<CartRowItem> getRows() {
        return rows;
    }

    public void setRows(ArrayList<CartRowItem> rows) {
        this.rows = rows;
        setTotalPriceIncVatAmount(calculateTotalPriceIncVatAmount());
        setTotalVatAmount(calculateTotalVatAmount());
    }

    public double getTotalPriceIncVatAmount() {
        return totalPriceIncVatAmount;
    }

    private void setTotalPriceIncVatAmount(double totalPriceIncVatAmount) {
        this.totalPriceIncVatAmount = totalPriceIncVatAmount;
    }

    public double getTotalVatAmount() {
        return totalVatAmount;
    }

    private void setTotalVatAmount(double totalVatAmount) {
        this.totalVatAmount = totalVatAmount;
    }

    private double calculateTotalPriceIncVatAmount(){
        double totalPriceIncVat = 0;
        for (CartRowItem cartRowItem: rows){
            totalPriceIncVat = totalPriceIncVat + cartRowItem.getProduct().getPriceIncVat();

        }
        return totalPriceIncVat;
    }
    private double calculateTotalVatAmount(){
        double totalVatAmount = 0;
        for (CartRowItem cartRowItem: rows){
            totalVatAmount = totalVatAmount + cartRowItem.getProduct().getVatAmount();

        }
        return totalVatAmount;
    }

    public void addProductInRow(CartRowItem cartRowItem) {
        this.rows.add(cartRowItem);
        setTotalPriceIncVatAmount(calculateTotalPriceIncVatAmount());
        setTotalVatAmount(calculateTotalVatAmount());
    }
}
