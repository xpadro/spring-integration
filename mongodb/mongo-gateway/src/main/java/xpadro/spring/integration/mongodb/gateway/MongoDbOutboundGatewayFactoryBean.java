package xpadro.spring.integration.mongodb.gateway;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.messaging.MessageHandler;

/**
 * Creates instances of {@link MongoDbOutboundGateway}
 *
 * @author Xavier Padró
 */
public class MongoDbOutboundGatewayFactoryBean extends AbstractFactoryBean<MessageHandler> {
    private MongoDbExecutor mongoDbExecutor;

    public void setMongoDbExecutor(MongoDbExecutor mongoDbExecutor) {
        this.mongoDbExecutor = mongoDbExecutor;
    }

    private boolean requiresReply = false;

    public void setRequiresReply(boolean requiresReply) {
        this.requiresReply = requiresReply;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageHandler.class;
    }

    @Override
    protected MessageHandler createInstance() throws Exception {
        MongoDbOutboundGateway mongoDbOutboundGateway = new MongoDbOutboundGateway(this.mongoDbExecutor);

        mongoDbOutboundGateway.setRequiresReply(this.requiresReply);
        mongoDbOutboundGateway.setBeanFactory(this.getBeanFactory());

        mongoDbOutboundGateway.afterPropertiesSet();

        return mongoDbOutboundGateway;
    }
}
