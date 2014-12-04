package web;

import model.Cart;
import exception.ResourceNotFoundException;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import repository.CartRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-10.
 */
@RestController
@Service
public class CartsController extends BaseController {
    CartRepository cartRepository;

    @Autowired
    public CartsController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public List<Cart> getCarts() {
        return cartRepository.retrieve();
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Cart getCart(@PathVariable("id") String id, final HttpServletResponse response) throws ResourceNotFoundException {
        Optional<Cart> cart = cartRepository.retrieve(id);
        response.setHeader("Location", id);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new ResourceNotFoundException("Unable to find cart with id " + id);
        }
    }

    @RequestMapping(value = "/carts", method = RequestMethod.POST)
    public
    @ResponseBody
    Cart createCart(@RequestBody final Cart cart, final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        cartRepository.save(cart);
        response.setHeader("Location", request.getRequestURL()+"/"+cart.getId());
        return cart;
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Cart deleteCart(@PathVariable("id") String id, HttpServletResponse response) throws ResourceNotFoundException {
        Optional<Cart> cart = cartRepository.delete(id);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new ResourceNotFoundException("Unable to delete cart with id" + id);
        }
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.PUT)
    public Cart putCart(@PathVariable("id") String cartId, @RequestBody String jsonProduct, HttpServletResponse response, final HttpServletRequest request) throws ResourceNotFoundException, IOException {
        response.setHeader("Location", request.getRequestURL()+"/"+ cartId);
        Optional<Cart> cart = cartRepository.addProductInCart(cartId, jsonProduct);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new ResourceNotFoundException("Unable to find cart with id " + cartId);
        }
    }
}
