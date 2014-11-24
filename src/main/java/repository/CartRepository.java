package repository;

import model.Cart;

import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-29.
 */
public interface CartRepository {
    Cart saveCart(Cart cart);
    List<Cart> retrieveCarts();
    Optional<Cart> retrieveCart(String id);
    Optional<Cart> deleteCart(String id);
    Optional<Cart> addProductInCart(String cartId, String jsonProduct);
}
