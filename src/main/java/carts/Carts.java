package carts;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;
import products.Product;
import products.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andgra on 2014-10-10.
 */
public class Carts {
    private static Carts ourInstance = new Carts();
    private HashMap<String, Cart> cartsHashMap;

    public static Carts getInstance() {
        return ourInstance;
    }

    private Carts() {
        cartsHashMap = new HashMap<String, Cart>();
    }

    public ArrayList<Cart> getCarts() {
        ArrayList<Cart> cartArrayList = new ArrayList<Cart>(cartsHashMap.values());
        return cartArrayList;
    }
    private Cart createCart(String id, ArrayList<CartRowItem> rows, double totalPriceIncVatAmount, double totalVatAmount) {
        Cart cart = new Cart(id, rows, totalPriceIncVatAmount, totalVatAmount);
        cartsHashMap.put(id, cart);
        return cart;
    }

    public String createCart(String jsonProduct) {
        Object obj= JSONValue.parse(jsonProduct);

        JSONObject jobj = new JSONObject((Map) obj);
        ArrayList<CartRowItem> rows = (ArrayList<CartRowItem>)jobj.get("rows");
        Cart cart = createCart((String)jobj.get("id"),rows,
                (Double)jobj.get("totalPriceIncVatAmount"),(Double)jobj.get("totalVatAmount"));
        return cart.getId();  //CREATED

    }
    public HttpStatus deleteCart(String id) {
        HttpStatus statusCode;
        if(cartsHashMap.containsKey(id)){

            cartsHashMap.remove(id);
            statusCode = HttpStatus.OK; //Fel kod?
        } else{
            statusCode = HttpStatus.NOT_FOUND; //Fel kod?
        }
        return statusCode;
    }
    public Cart getCart(String id) {
        return cartsHashMap.get(id);

    }
    public String changeCart(String cartId, String jsonProduct) {
        Object obj= JSONValue.parse(jsonProduct);
        JSONObject jobj = new JSONObject((Map) obj);
        String id = (String)jobj.get("id");

        Cart cart = cartsHashMap.get(cartId);

        String productId = (String)jobj.get("productId");
        Product product = Products.getInstance().getProduct(productId);
        CartRowItem cartRowItem = new CartRowItem(product, ((Double)jobj.get("quantity")));
        cart.addProductInRow(cartRowItem);



       /* cart.s((Double)jobj.get("quantity"));
        product.setPriceIncVat((Double)jobj.get("priceIncVat"));
        product.setVatPercentage((Double)jobj.get("vatPercentage"));
        product.setVatAmount((Double)jobj.get("vatAmount"));*/

        return "YAAAD";  //CREATED

    }
}
