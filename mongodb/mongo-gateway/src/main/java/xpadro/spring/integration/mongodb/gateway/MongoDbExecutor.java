package xpadro.spring.integration.mongodb.gateway;

import com.mongodb.DBObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.expression.ExpressionUtils;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Xavier Padró
 */
public class MongoDbExecutor implements InitializingBean, BeanFactoryAware {
    private static final String DEFAULT_COLLECTION_NAME = "data";
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    private volatile BeanFactory beanFactory;

    private volatile EvaluationContext evaluationContext;

    private volatile String mongoDbQuery;

    private volatile Expression mongoDbQueryExpression;

    private volatile Integer maxResults;

    private final MongoOperations mongoTemplate;

    private volatile boolean expectSingleResult = false;

    private volatile Class<?> entityClass = DBObject.class;

    private volatile Expression collectionNameExpression = new LiteralExpression(DEFAULT_COLLECTION_NAME);

    public MongoDbExecutor(MongoDbFactory mongoDbFactory) {
        Assert.notNull(mongoDbFactory, "mongoDbFactory must not be null.");

        this.mongoTemplate = new MongoTemplate(mongoDbFactory);
    }

    public MongoDbExecutor(MongoOperations mongoTemplate) {
        Assert.notNull(mongoTemplate, "mongoTemplate must not be null.");

        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.evaluationContext == null) {
            this.evaluationContext = ExpressionUtils.createStandardEvaluationContext(this.beanFactory);
        }
    }

    public void setMongoDbQuery(String mongoDbQuery) {
        this.mongoDbQuery = mongoDbQuery;
    }

    public void setMongoDbQueryExpression(String mongoDbQueryExpression) {
        this.mongoDbQueryExpression = PARSER.parseExpression(mongoDbQueryExpression);
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setExpectSingleResult(boolean expectSingleResult) {
        this.expectSingleResult = expectSingleResult;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public void setCollectionNameExpression(Expression collectionNameExpression) {
        this.collectionNameExpression = collectionNameExpression;
    }

    public Object execute(final Message<?> requestMessage) {
        Assert.isTrue(this.mongoDbQuery != null || this.mongoDbQueryExpression != null, "no query specified");

        String collectionName = this.collectionNameExpression.getValue(this.evaluationContext, String.class);
        Query query = buildQuery(requestMessage);

        Object result = null;

        if (this.expectSingleResult) {
            result = this.mongoTemplate.findOne(query, this.entityClass, collectionName);
        }
        else {
            if (this.maxResults != null) {
                query.limit(this.maxResults);
            }

            List<?> results = this.mongoTemplate.find(query, this.entityClass, collectionName);

            if (!CollectionUtils.isEmpty(results)) {
                result = results;
            }
        }

        return result;
    }

    private Query buildQuery(final Message<?> requestMessage) {
        String query;

        if (this.mongoDbQuery != null) {
            query = this.mongoDbQuery;
        }
        else {
            query = this.mongoDbQueryExpression.getValue(this.evaluationContext, requestMessage, String.class);
        }

        return new BasicQuery(query);
    }
}
