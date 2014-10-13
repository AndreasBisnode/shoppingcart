package controllers;

import carts.Cart;
import carts.Carts;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by andgra on 2014-10-10.
 */
@RestController
public class CartsController {
    Carts CARTS = Carts.getInstance();

    @RequestMapping(value = "/carts", method= RequestMethod.GET)
    public ArrayList<Cart> getCarts(){
        return CARTS.getCarts();
    }
    @RequestMapping(value = "/carts", method= RequestMethod.POST)
    public String createCart(@RequestParam(value="id") Long id){
        CARTS.createCarts(id);
        return "ej implem,enterad";
    }
}
