package products;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Product createProduct(String id, String name, double priceIncVat, double vatPercentage, double vatAmount) {
        Product product = new Product(id, name, priceIncVat, vatPercentage, vatAmount);
        productHashMap.put(id, product);
        return product;
    }

    public String createProduct(String jsonProduct) {
        Object obj= JSONValue.parse(jsonProduct);
        JSONObject jobj = new JSONObject((Map) obj);


        Product product = createProduct((String)jobj.get("id"),(String)jobj.get("name"),
                (Double)jobj.get("priceIncVat"),(Double)jobj.get("vatPercentage"),(Double)jobj.get("vatAmount"));
        return product.getId();  //CREATED

    }
    public String changeProduct(String jsonProduct) {
        Object obj= JSONValue.parse(jsonProduct);
        JSONObject jobj = new JSONObject((Map) obj);
        String id = (String)jobj.get("id");

        Product product = productHashMap.get(id);
        product.setName((String)jobj.get("name"));
        product.setPriceIncVat((Double)jobj.get("priceIncVat"));
        product.setVatPercentage((Double)jobj.get("vatPercentage"));
        product.setVatAmount((Double)jobj.get("vatAmount"));

        return product.getId();  //CREATED

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
    public HttpStatus deleteProduct(String id) {
        HttpStatus statusCode;
        if(productHashMap.containsKey(id)){

            productHashMap.remove(id);
            statusCode = HttpStatus.OK; //Fel kod?
        } else{
            statusCode = HttpStatus.NOT_FOUND; //Fel kod?
        }
        return statusCode;
    }
}
