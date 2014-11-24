package repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by andgra on 2014-11-21.
 */
@Service
public class MapProductRepository implements ProductRepository{
    private Map<String, Product> productHashMap = new HashMap<String, Product>();
    @Override
    public Product saveProduct(Product product) {
        productHashMap.put(product.getId(), product);
        return product;
    }

    @Override
    public ArrayList<Product> retrieveProducts() {
        ArrayList<Product> products = new ArrayList<Product>(productHashMap.values());
        return products;
    }

    @Override
    public Optional<Product> retrieveProduct(String id) {
        return Optional.ofNullable(productHashMap.get(id));
    }

    @Override
    public Optional<Product> deleteProduct(String id) {
        return Optional.ofNullable(productHashMap.remove(id));
    }
}
