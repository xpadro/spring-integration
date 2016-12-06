package xpadro.spring.integration.mongodb.gateway;

import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;

/**
 * Makes outbound operations to query a MongoDb database using a {@link MongoDbExecutor}
 *
 * @author Xavier Padró
 */
public class MongoDbOutboundGateway extends AbstractReplyProducingMessageHandler {
    private final MongoDbExecutor mongoDbExecutor;

    private volatile boolean initialized = false;

    public MongoDbOutboundGateway(MongoDbExecutor mongoDbExecutor) {
        Assert.notNull(mongoDbExecutor, "mongoDbExecutor must not be null.");

        this.mongoDbExecutor = mongoDbExecutor;
    }

    @Override
    protected void doInit() {
        this.mongoDbExecutor.setBeanFactory(this.getBeanFactory());
        this.initialized = true;
    }

    @Override
    protected Object handleRequestMessage(Message<?> requestMessage) {
        Assert.isTrue(this.initialized, "This class is not yet initialized. Invoke its afterPropertiesSet() method");

        Object result = this.mongoDbExecutor.execute(requestMessage);

        return this.getMessageBuilderFactory().withPayload(result).copyHeaders(requestMessage.getHeaders()).build();
    }
}
