package controllers;

import carts.Cart;
import carts.Carts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    @RequestMapping(value = "/carts/{id}", method= RequestMethod.GET)
    public @ResponseBody ResponseEntity<Cart> getCart( @PathVariable("id") String id){
        Cart product = CARTS.getCart(id);
        HttpStatus httpStatus;
        if (product == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }  else {
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<Cart>(product, httpStatus) ;
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
