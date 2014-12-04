package model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by andgra on 2014-10-09.
 */

public class Product {

    private final String id;
    private final String name;
    private final double priceIncVat;
    private final double vatPercentage;
    private final double vatAmount;


    @JsonCreator
    public Product(@JsonProperty("id") String id,@JsonProperty("name") String name, @JsonProperty("priceIncVat") double priceIncVat, @JsonProperty("vatPercentage") double vatPercentage, @JsonProperty("vatAmount") double vatAmount) {
        this.id = id;
        this.name = name;
        this.priceIncVat = priceIncVat;
        this.vatPercentage = vatPercentage;
        this.vatAmount = vatAmount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public double getPriceIncVat() {
        return priceIncVat;
    }


    public double getVatPercentage() {
        return vatPercentage;
    }


    public double getVatAmount() {
        return vatAmount;
    }

}
