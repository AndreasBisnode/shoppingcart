package controllers;

import carts.Cart;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import repository.CartRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-10.
 */
@RestController
@Service
public class CartsController {
    CartRepository cartRepository;
    Logger logger = (Logger) Logger.getInstance(getClass());

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleError(ResourceNotFoundException e) {
        logger.error(e);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleError(IOException e) {
        e.printStackTrace();
        logger.error(e);
    }

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    public List<Cart> getCarts() {
        return cartRepository.retrieveCarts();
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Cart getCart(@PathVariable("id") String id, final HttpServletResponse response) throws ResourceNotFoundException {
        Optional<Cart> cart = cartRepository.retrieveCart(id);
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
    Cart createProducts(@RequestBody final String jsonProduct, final HttpServletResponse response) throws IOException {
        Cart cart = cartRepository.saveCart(jsonProduct);
        String location = "http://localhost:8080/carts/" + cart.getId();
        response.setHeader("Location", location);
        return cart;
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Cart deleteCart(@PathVariable("id") String id, HttpServletResponse response) throws ResourceNotFoundException {
        Optional<Cart> cart = cartRepository.deleteCart(id);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new ResourceNotFoundException("Unable to delete cart with id" + id);
        }
    }

    @RequestMapping(value = "/carts/{id}", method = RequestMethod.PUT)
    public Cart putCart(@PathVariable("id") String cartId, @RequestBody String jsonProduct, HttpServletResponse response) throws ResourceNotFoundException, IOException {
        response.setHeader("Location", "http://localhost:8080/products/" + cartId);
        Optional<Cart> cart = cartRepository.addProductInCart(cartId, jsonProduct);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new ResourceNotFoundException("Unable to find cart with id " + cartId);
        }
    }
}
