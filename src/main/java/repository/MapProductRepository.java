package repository;

import model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by andgra on 2014-11-21.
 */

@Repository
public class MapProductRepository implements ProductRepository{
    private Map<String, Product> productHashMap = new HashMap<String, Product>();
    @Override
    public Product save(Product product) {
        productHashMap.put(product.getId(), product);
        return product;
    }

    @Override
    public ArrayList<Product> retrieve() {
        ArrayList<Product> products = new ArrayList<Product>(productHashMap.values());
        return products;
    }

    @Override
    public Optional<Product> retrieve(String id) {
        return Optional.ofNullable(productHashMap.get(id));
    }

    @Override
    public Optional<Product> delete(String id) {
        return Optional.ofNullable(productHashMap.remove(id));
    }
}
