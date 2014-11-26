package mongoDb;

import com.mongodb.Mongo;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Repository
public class MongoProductRepository  implements ProductRepository{
    private MongoOperations mongoOps;
    private Mongo mongo;
    private static final String PRODUCT_COLLECTION = "Product";

    @Autowired
    public MongoProductRepository(MongoOperations mongoOps){
        this.mongoOps=mongoOps;
        this.mongo=mongo;

    }

    @Override
    public Product save(Product product) {
        this.mongoOps.save(product);
        return product;
    }

    @Override
    public List<Product> retrieve() {
        try {
            this.mongoOps.findAll(Product.class);
            return this.mongoOps.findAll(Product.class);
        }catch (Exception e){
            return new ArrayList<Product>();
        }
    }

    @Override
    public Optional<Product> retrieve(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Product product = (Product) this.mongoOps.find(query, Product.class);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> delete(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        this.mongoOps.remove(query, Product.class);
        return null;
    }
}
