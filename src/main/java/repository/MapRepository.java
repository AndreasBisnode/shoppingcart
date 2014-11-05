package repository;

import carts.Cart;
import carts.CartRowItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import products.Product;

import java.io.IOException;
import java.util.*;

/**
 * Created by andgra on 2014-11-05.
 */
public class MapRepository implements CartRepository, ProductRepository {
    private Map<String, Product> productHashMap;
    private Map<String, Cart> cartMap = new HashMap<String, Cart>();

    @Override
    public Cart saveCart(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Cart cart = mapper.readValue(json, Cart.class);
        cartMap.put(cart.getId(), cart);

        return cart;
    }

    @Override
    public List<Cart> retrieveCarts() {
        ArrayList<Cart> cartArrayList = new ArrayList<Cart>(cartMap.values());
        return cartArrayList;
    }

    @Override
    public Optional<Cart> retrieveCart(String id) {
        return Optional.ofNullable(cartMap.get(id));
    }

    @Override
    public Optional<Cart> deleteCart(String id) {
        return Optional.ofNullable(cartMap.remove(id));
    }

    @Override
    public Optional<Cart> addProductInCart(String cartId, String jsonProduct) throws IOException {
        Map<String, Object> valueMap = new ObjectMapper().readValue(jsonProduct, new TypeReference<Map<String, Object>>() {});
        Cart cart = Optional.ofNullable(cartMap.get(cartId)).get();
        Product product = retrieveProduct((String) valueMap.get("productId")).get();
        double quantity = Double.parseDouble(valueMap.get("quantity").toString());
        CartRowItem cartRowItem = new CartRowItem(product, quantity);

        cart.addProductInRow(cartRowItem);
        return Optional.ofNullable(cart);
    }
    @Override
    public Product saveProduct(String jsonProduct) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(jsonProduct, Product.class);
        productHashMap.put(product.getId(), product);
        return product;
    }

    @Override
    public ArrayList<Product> retrieveProducts() {
        ArrayList<Product> products = new ArrayList<Product>(productHashMap.values());
        return products;
    }

    @Override
    public Optional<Product> retrieveProduct(String id) {
        return Optional.ofNullable(productHashMap.get(id));
    }

    @Override
    public Optional<Product> deleteProduct(String id) {
        return Optional.ofNullable(productHashMap.remove(id));
    }
}
