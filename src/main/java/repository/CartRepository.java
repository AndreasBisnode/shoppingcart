package repository;

import model.Cart;

import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-29.
 */
public interface CartRepository {
    Cart save(Cart cart);
    List<Cart> retrieve();
    Optional<Cart> retrieve(String id);
    Optional<Cart> delete(String id);
    Optional<Cart> addProductInCart(String cartId, String jsonProduct);
}
