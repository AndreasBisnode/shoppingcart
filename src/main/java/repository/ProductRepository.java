package repository;

import model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-10-24.
 */
public interface ProductRepository  {
    Product save(Product product);
    List<Product> retrieve();
    Optional<Product> retrieve(String id);
    Optional<Product> delete(String id);
}
