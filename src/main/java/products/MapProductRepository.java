package products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-10.
 */
@Service
public class MapProductRepository implements ProductRepository {
    private Map<String, Product> productHashMap;

    public MapProductRepository() {
        productHashMap = new HashMap<String, Product>();
    }

    @Override
    public Product saveProduct(String jsonProduct) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(jsonProduct, Product.class);
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
