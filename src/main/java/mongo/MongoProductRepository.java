package mongo;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-12-04.
 */
@Repository
public class MongoProductRepository implements ProductRepository {

    MongoOperations mongoOperations;

    @Autowired
    MongoProductRepository(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }


    @Override
    public Product save(Product product) {
        mongoOperations.save(product);
        return product;
    }

    @Override
    public List<Product> retrieve() {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").all());
        return mongoOperations.find(query, Product.class);
    }

    @Override
    public Optional<Product> retrieve(String id) {
        return null;
    }

    @Override
    public Optional<Product> delete(String id) {

        return null;
    }


}
