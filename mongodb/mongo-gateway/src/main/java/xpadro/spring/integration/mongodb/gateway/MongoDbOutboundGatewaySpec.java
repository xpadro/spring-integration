package xpadro.spring.integration.mongodb.gateway;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.expression.Expression;
import org.springframework.integration.dsl.core.ComponentsRegistration;
import org.springframework.integration.dsl.core.MessageHandlerSpec;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Xavier Padró
 */
public class MongoDbOutboundGatewaySpec extends MessageHandlerSpec<MongoDbOutboundGatewaySpec, MongoDbOutboundGateway> implements ComponentsRegistration {
    private final MongoDbExecutor mongoDbExecutor;

    private MongoDbOutboundGatewayFactoryBean mongoDbOutboundGatewayFactoryBean = new MongoDbOutboundGatewayFactoryBean();

    public MongoDbOutboundGatewaySpec(MongoDbExecutor mongoDbExecutor) {
        this.mongoDbExecutor = mongoDbExecutor;
        this.mongoDbOutboundGatewayFactoryBean.setMongoDbExecutor(mongoDbExecutor);
        this.mongoDbOutboundGatewayFactoryBean.setRequiresReply(true);
    }

    @Override
    public Collection<Object> getComponentsToRegister() {
        return Collections.singletonList(this.mongoDbExecutor);
    }

    public MongoDbOutboundGatewaySpec expectSingleResult(boolean expectSingleResult) {
        this.mongoDbExecutor.setExpectSingleResult(expectSingleResult);
        return this;
    }

    public MongoDbOutboundGatewaySpec query(String query) {
        this.mongoDbExecutor.setMongoDbQuery(query);
        return this;
    }

    public MongoDbOutboundGatewaySpec queryExpression(String mongoDbQueryExpression) {
        this.mongoDbExecutor.setMongoDbQueryExpression(mongoDbQueryExpression);
        return this;
    }

    public MongoDbOutboundGatewaySpec entityClass(Class<?> entityClass) {
        this.mongoDbExecutor.setEntityClass(entityClass);
        return this;
    }

    public MongoDbOutboundGatewaySpec maxResults(int maxResults) {
        this.mongoDbExecutor.setMaxResults(maxResults);
        return this;
    }

    public MongoDbOutboundGatewaySpec collectionNameExpression(Expression collectionNameExpression) {
        this.mongoDbExecutor.setCollectionNameExpression(collectionNameExpression);
        return this;
    }

    @Override
    protected MongoDbOutboundGateway doGet() {
        this.mongoDbOutboundGatewayFactoryBean.setBeanFactory(new DefaultListableBeanFactory());

        try {
            this.mongoDbOutboundGatewayFactoryBean.afterPropertiesSet();
            return (MongoDbOutboundGateway) this.mongoDbOutboundGatewayFactoryBean.getObject();
        } catch (Exception e) {
            throw new BeanCreationException("Cannot create the MongoDbOutboundGateway", e);
        }
    }
}
