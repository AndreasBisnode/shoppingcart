package mongo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Cart;
import model.CartRowItem;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import repository.CartRepository;
import repository.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by andgra on 2014-12-16.
 */
@Repository
public class MongoCartRepository implements CartRepository {

    private final MongoOperations mongoOperations;
    private final ProductRepository productRepository;

    @Autowired
    MongoCartRepository(MongoOperations mongoOperations, ProductRepository productRepository){
        this.mongoOperations = mongoOperations;
        this.productRepository = productRepository;
    }

    @Override
    public Cart save(Cart cart) {
        mongoOperations.save(cart);
        return cart;
    }

    @Override
    public List<Cart> retrieve() {
        return mongoOperations.findAll(Cart.class);
    }

    @Override
    public Optional<Cart> retrieve(String id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoOperations.findOne(query, Cart.class));
    }

    @Override
    public Optional<Cart> delete(String id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoOperations.findAndRemove(query, Cart.class));
    }

    @Override
    public Optional<Cart> addProductInCart(String cartId, String jsonProductIdAndQuantity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Cart cart = retrieve(cartId).get();
        JsonNode jsonValues = mapper.readTree(jsonProductIdAndQuantity);
        Product product = productRepository.retrieve(jsonValues.path("productId").textValue()).get();
        double quantity = Double.parseDouble(jsonValues.path("quantity").toString());
        cart.addProductInRow(new CartRowItem(product, quantity));
        save(cart);
        return Optional.ofNullable(cart);
    }
}
