package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import products.Product;
import repository.ProductRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-09.
 */


@RestController
@Service
public class ProductsController {
    ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> retrieveProducts() {
        return productRepository.retrieveProducts();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleError(ResourceNotFoundException e) {

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product retrieveProduct(@PathVariable("id") final String id, final HttpServletResponse response) throws ResourceNotFoundException {
        response.setHeader("Location", id);
        Optional<Product> p = productRepository.retrieveProduct(id);
        if (p.isPresent()) {
            return p.get();
        } else {
            throw new ResourceNotFoundException("Unable to find product with id " + id);
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Product getProduct(@PathVariable("id") final String id, @RequestBody String jsonProduct, final HttpServletResponse response) throws ResourceNotFoundException {
        response.setHeader("Location", "");
        return productRepository.saveProduct(jsonProduct);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public Product createProducts(@RequestBody final String jsonProduct, final HttpServletResponse response) {
        Product product = productRepository.saveProduct(jsonProduct);
        String location = "http://localhost:8080/products/" + product.getId();
        response.setHeader("Location", location);
        return product;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Product deleteProduct(@PathVariable("id") String id) throws ResourceNotFoundException {
        Optional<Product> p = productRepository.deleteProduct(id);
        if (p.isPresent()) {
            return p.get();
        } else {
            throw new ResourceNotFoundException("Unable to deleteProduct product with id: " + id);
        }

    }
}
