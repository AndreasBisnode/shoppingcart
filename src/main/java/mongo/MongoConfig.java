package mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by andgra on 2014-12-04.
 */

@Configuration
public class MongoConfig {

    @Bean
    MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate =
                new MongoTemplate(new MongoClient("127.0.0.1"),"mongoDb");
        return mongoTemplate;

    }
}
