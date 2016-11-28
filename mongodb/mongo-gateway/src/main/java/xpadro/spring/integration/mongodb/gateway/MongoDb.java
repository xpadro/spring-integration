package xpadro.spring.integration.mongodb.gateway;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author Xavier Padró
 */
public class MongoDb {

    public static MongoDbOutboundGatewaySpec outboundGateway(MongoDbFactory mongoDbFactory) {
        return new MongoDbOutboundGatewaySpec(new MongoDbExecutor(mongoDbFactory));
    }

    public static MongoDbOutboundGatewaySpec outboundGateway(MongoTemplate mongoTemplate) {
        return new MongoDbOutboundGatewaySpec(new MongoDbExecutor(mongoTemplate));
    }
}
