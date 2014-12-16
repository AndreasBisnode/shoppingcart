package web;

import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import model.Product;
import repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-09.
 */

@RestController
@Service
public class ProductsController extends BaseController{
    ProductRepository productRepository;

    @Autowired
    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> retrieveProducts() {
        return productRepository.retrieve();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product retrieveProduct(@PathVariable("id") final String id, final HttpServletResponse response) throws ResourceNotFoundException {
        response.setHeader("Location", id);
        Optional<Product> p = productRepository.retrieve(id);
        if (p.isPresent()) {
            return p.get();
        } else {
            throw new ResourceNotFoundException("Unable to find product with id " + id);
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Product getProduct(@PathVariable("id") final String id, @RequestBody Product jsonProduct, final HttpServletResponse response) throws ResourceNotFoundException, IOException {
        response.setHeader("Location", "");
        return productRepository.save(jsonProduct);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public Product create(@RequestBody final Product jsonProduct, final HttpServletResponse response, final HttpServletRequest request) throws IOException {
        Product product = productRepository.save(jsonProduct);
        response.setHeader("Location", request.getRequestURL()+"/"+product.getId());
        return product;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Product deleteProduct(@PathVariable("id") String id) throws ResourceNotFoundException {
        Optional<Product> p = productRepository.delete(id);
        if (p.isPresent()) {
            return p.get();
        } else {
            throw new ResourceNotFoundException("Unable to deleteProduct product with id: " + id);
        }

    }
}
