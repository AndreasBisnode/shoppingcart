package products;

import util.StatusCodes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andgra on 2014-10-10.
 */
public class Products {
    private static Products ourInstance = new Products();
    private HashMap<String, Product> productHashMap;

    public static Products getInstance() {
        return ourInstance;
    }

    private Products() {
        productHashMap = new HashMap<String, Product>();
    }

    /**
     * PUT
     * @param id
     * @param name
     * @param priceIncVat
     * @param vatPercentage
     * @param vatAmount
     * @return
     */
    public String createProduct(String id, String name, double priceIncVat, double vatPercentage, double vatAmount) {
        Product product = new Product(id, name, priceIncVat, vatPercentage, vatAmount);
        productHashMap.put(id, product);
        return "200";  //CREATED
    }

    /**
     * GET
     * @return
     */
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>(productHashMap.values());
        return products;
    }

    /**
     * GET
     *
     * @param id
     * @return
     */
    public Product getProduct(String id) {
        return productHashMap.get(id);

    }

    /**
     * DELETE
     * @param id
     * @return
     */
    public String deleteProduct(Long id) {
        String statusCode;
        if(productHashMap.containsKey(id)){
            productHashMap.remove(id).toString();
            statusCode = StatusCodes.OK; //Fel kod?
        } else{
            statusCode = StatusCodes.BAD_REQUEST; //Fel kod?
        }
        return statusCode;
    }
}
