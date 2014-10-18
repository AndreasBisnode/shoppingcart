package controllers;

import carts.Cart;
import carts.CartRowItem;
import carts.Carts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import products.Product;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by andgra on 2014-10-10.
 */
@RestController
public class CartsController {
    Carts CARTS = Carts.getInstance();

    @RequestMapping(value = "/test", method= RequestMethod.GET)
    public Cart test(){
        return new Cart("id", new ArrayList<CartRowItem>( ),1,1);

    }
    @RequestMapping(value = "/test2", method= RequestMethod.GET)
    public Cart test2(){
        ArrayList<CartRowItem> array = new ArrayList<CartRowItem>();
        Product product = new Product("d","d",0,0,0);
        array.add(new CartRowItem(product, 2));
        return new Cart("id", new ArrayList<CartRowItem>(),2,2);

    }
    @RequestMapping(value = "/test3", method= RequestMethod.GET)
    public ArrayList<Cart> test3(){
        ArrayList<CartRowItem> array = new ArrayList<CartRowItem>();
        Product product = new Product("d","d",0,0,0);
        array.add(new CartRowItem(product, 2));
        Cart cart = new Cart("id", new ArrayList<CartRowItem>(),3,3);
        cart.addProductInRow(new CartRowItem(product, 2));

        ArrayList<Cart> as = new ArrayList<Cart>();
        as.add(cart);
        return as;

    }
    @RequestMapping(value = "/test4", method= RequestMethod.GET)
    public String test4(){
        return "d";

    }

    @RequestMapping(value = "/carts", method= RequestMethod.GET)
    public @ResponseBody ArrayList<Cart> getCarts(){
        return CARTS.getCarts();
    }
    @RequestMapping(value = "/carts/{id}", method= RequestMethod.GET)
    public @ResponseBody ResponseEntity<Cart> getCart( @PathVariable("id") String id){
        Cart cart = CARTS.getCart(id);
        HttpStatus httpStatus;
        if (cart == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }  else {
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<Cart>(cart, httpStatus) ;
    }
    @RequestMapping(value = "/carts", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> createProducts(@RequestBody String jsonProduct){
        String cart = CARTS.createCart(jsonProduct);
        URI location = URI.create("http://localhost:8080/carts/" + cart);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        // return "http://localhost/products/search";
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);

        return responseEntity;
        //http://h30499.www3.hp.com/t5/HP-Software-Developers-Blog/A-Comprehensive-Example-of-a-Spring-MVC-Application-Part-3/ba-p/6135449#.VDwyBvl_t34
        // return PRODUCTS.createProduct(id, name, priceIncVat, vatPercentage, vatAmount);
    }
    @RequestMapping(value = "/carts/{id}", method= RequestMethod.DELETE)
    public  @ResponseBody ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        HttpStatus httpStatus = CARTS.deleteCart(id);

        return new ResponseEntity<String>(httpStatus);

    }
    @RequestMapping(value = "/carts/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Cart> getCart( @PathVariable("id") String cartId, @RequestBody String jsonProduct){

        String cart = CARTS.changeCart(cartId ,jsonProduct);
        URI location = URI.create("http://localhost:8080/products/" + cart);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        HttpStatus httpStatus;
        if (cart == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }  else {
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<Cart>(responseHeaders, httpStatus) ;
    }
}
