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
    @RequestMapping(value = "/products/search/", method= RequestMethod.GET)
    public Product getProduct( @RequestParam(value="id") String id){
        return PRODUCTS.getProduct(id);
    }
  //  @RequestMapping(value = "/products", method= RequestMethod.POST)
   /* public @ResponseBody String createProducts(@RequestParam(value="id") String id,
                                 @RequestParam(value="name") String name,
                                 @RequestParam(value="priceIncVat") double priceIncVat,
                                 @RequestParam(value="vatPercentage") double vatPercentage,
                                 @RequestParam(value="vatAmount") double vatAmount) {
        return PRODUCTS.createProduct(id, name, priceIncVat, vatPercentage, vatAmount);
    }*/
    @RequestMapping(value = "/products", method= RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> createProducts(@RequestBody String jsonProduct){
        URI location = URI.create("http://localhost/products/search");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);
        Product product = PRODUCTS.createProduct(jsonProduct);
       // return "http://localhost/products/search";
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("/products/search/",responseHeaders, HttpStatus.CREATED);

        return responseEntity;
                        //http://h30499.www3.hp.com/t5/HP-Software-Developers-Blog/A-Comprehensive-Example-of-a-Spring-MVC-Application-Part-3/ba-p/6135449#.VDwyBvl_t34
       // return PRODUCTS.createProduct(id, name, priceIncVat, vatPercentage, vatAmount);
    }
    @RequestMapping(value = "/products", method= RequestMethod.DELETE)
    public  @ResponseBody String deleteProduct(@RequestParam(value="id") String id) {
        return PRODUCTS.deleteProduct(id);
    }
}
