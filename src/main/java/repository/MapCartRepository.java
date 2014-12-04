package repository;

import com.fasterxml.jackson.databind.JsonNode;
import model.Cart;
import model.CartRowItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by andgra on 2014-11-21.
 */

@Repository
public class MapCartRepository implements CartRepository {
    private ProductRepository productRepository;
    private Map<String, Cart> cartMap = new HashMap<String, Cart>();

    @Autowired
    public MapCartRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Cart save(Cart cart)  {
        cartMap.put(cart.getId(), cart);
        return cart;
    }

    @Override
    public List<Cart> retrieve() {
        ArrayList<Cart> cartArrayList = new ArrayList<Cart>(cartMap.values());
        return cartArrayList;
    }

    @Override
    public Optional<Cart> retrieve(String id) {
        return Optional.ofNullable(cartMap.get(id));
    }

    @Override
    public Optional<Cart> delete(String id) {
        return Optional.ofNullable(cartMap.remove(id));
    }

    @Override
    public Optional<Cart> addProductInCart(String cartId, String jsonProduct) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonProduct);

        Cart cart = Optional.ofNullable(cartMap.get(cartId)).get();
        Product product = productRepository.retrieve(node.path("productId").textValue()).get();
        double quantity = Double.parseDouble(node.path("quantity").toString());
        CartRowItem cartRowItem = new CartRowItem(product, quantity);

        cart.addProductInRow(cartRowItem);
        return Optional.ofNullable(cart);
    }
}
