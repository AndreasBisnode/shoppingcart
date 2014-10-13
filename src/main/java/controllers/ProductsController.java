package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import products.Product;
import products.Products;

import java.util.ArrayList;

/**
 * Created by andgra on 2014-10-09.
 */


@RestController
public class ProductsController {
    Products PRODUCTS = Products.getInstance();


   /* @RequestMapping(value = "/products", method= RequestMethod.GET)
    public Product getProduct(@RequestParam(value="id", required=true) Long id){
        return PRODUCTS.getProduct(id);
    }*/
    @RequestMapping(value = "/products", method= RequestMethod.GET)
    public ArrayList<Product> getProducts(){
        return PRODUCTS.getProducts();
    }
    @RequestMapping(value = "/products", method= RequestMethod.POST)
    public String createProducts(@RequestParam(value="id") String id,
                                 @RequestParam(value="name") String name,
                                 @RequestParam(value="priceIncVat") double priceIncVat,
                                 @RequestParam(value="vatPercentage") double vatPercentage,
                                 @RequestParam(value="vatAmount") double vatAmount) {
        return PRODUCTS.createProduct(id, name, priceIncVat, vatPercentage, vatAmount);
    }
    @RequestMapping(value = "/products", method= RequestMethod.DELETE)
    public String deleteProduct(@RequestParam(value="id") Long id) {
        return PRODUCTS.deleteProduct(id);
    }
}
