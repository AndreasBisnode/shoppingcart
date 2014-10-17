package controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import products.Product;
import products.Products;

import java.net.URI;
import java.util.ArrayList;

/**
 * Created by andgra on 2014-10-09.
 */



@RestController
public class ProductsController {
    Products PRODUCTS = Products.getInstance();

                                   //http://spring.io/guides/tutorials/rest/2/
   /* @RequestMapping(value = "/products", method= RequestMethod.GET)
    public Product getProduct(@RequestParam(value="id", required=true) Long id){
        return PRODUCTS.getProduct(id);
    }*/
    @RequestMapping(value = "/products", method= RequestMethod.GET)
    public ArrayList<Product> getProducts(){
        return PRODUCTS.getProducts();
    }
    @RequestMapping(value = "/products/{id}", method= RequestMethod.GET)
    public ResponseEntity<Product> getProduct( @PathVariable("id") String id){
        Product product = PRODUCTS.getProduct(id);
        HttpStatus httpStatus;
        if (product == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }  else {
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<Product>(product, httpStatus) ;
    }
    @RequestMapping(value = "/products/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Product> getProduct( @PathVariable("id") String id, @RequestBody String jsonProduct){

        String product = PRODUCTS.changeProduct(jsonProduct);
        URI location = URI.create("http://localhost:8080/products/" + product);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        HttpStatus httpStatus;
        if (product == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }  else {
            httpStatus = HttpStatus.OK;
        }
        return new ResponseEntity<Product>(responseHeaders, httpStatus) ;
    }

    @RequestMapping(value = "/products", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> createProducts(@RequestBody String jsonProduct){
        String product = PRODUCTS.createProduct(jsonProduct);
        URI location = URI.create("http://localhost:8080/products/" + product);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
       // return "http://localhost/products/search";
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);

        return responseEntity;
                        //http://h30499.www3.hp.com/t5/HP-Software-Developers-Blog/A-Comprehensive-Example-of-a-Spring-MVC-Application-Part-3/ba-p/6135449#.VDwyBvl_t34
       // return PRODUCTS.createProduct(id, name, priceIncVat, vatPercentage, vatAmount);
    }
    @RequestMapping(value = "/products/{id}", method= RequestMethod.DELETE)
    public  @ResponseBody ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        HttpStatus httpStatus = PRODUCTS.deleteProduct(id);

        return new ResponseEntity<String>(httpStatus);

    }
}
