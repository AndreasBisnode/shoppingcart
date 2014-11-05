package repository;

import products.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-24.
 */
public interface ProductRepository {
    Product saveProduct(String json) throws IOException;
    List<Product> retrieveProducts();
    Optional<Product> retrieveProduct(String id);
    Optional<Product> deleteProduct(String id);
}
