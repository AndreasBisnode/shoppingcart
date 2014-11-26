package model;



import java.util.ArrayList;

/**
 * Created by andgra on 2014-10-10.
 */
public class Cart {

    private String id;
    private ArrayList<CartRowItem> rows;
    private double totalPriceIncVatAmount;
    private double totalVatAmount;

    public Cart(){

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
        setTotalPriceIncVatAmount(calculateTotalPriceIncVatAmount());
        setTotalVatAmount(calculateTotalVatAmount());
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
