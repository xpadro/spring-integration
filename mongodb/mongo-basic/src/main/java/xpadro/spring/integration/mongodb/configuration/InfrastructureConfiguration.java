package xpadro.spring.integration.mongodb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.RouterSpec;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.mongodb.inbound.MongoDbMessageSource;
import org.springframework.integration.mongodb.outbound.MongoDbStoringMessageHandler;
import org.springframework.integration.router.MethodInvokingRouter;
import org.springframework.messaging.MessageHandler;
import xpadro.spring.integration.mongodb.endpoint.OrderToProductConverter;
import xpadro.spring.integration.mongodb.endpoint.ProductProcessor;
import xpadro.spring.integration.mongodb.entity.Order;
import xpadro.spring.integration.mongodb.entity.Product;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan("xpadro.spring.integration.endpoint")
@IntegrationComponentScan("xpadro.spring.integration.mongodb")
public class InfrastructureConfiguration {

    @MessagingGateway
    public interface OrderService {

        @Gateway(requestChannel = "sendOrder.input")
        void order(Order order);
    }

    @Bean
    @Autowired
    public IntegrationFlow sendOrder(MongoDbFactory mongo) {
        return f -> f
                .transform(Transformers.converter(orderToProductConverter()))
                .handle(mongoOutboundAdapter(mongo));
    }

    @Bean
    public Converter<Order, Product> orderToProductConverter() {
        return new OrderToProductConverter();
    }

    @Bean
    @Autowired
    public MessageHandler mongoOutboundAdapter(MongoDbFactory mongo) {
        MongoDbStoringMessageHandler mongoHandler = new MongoDbStoringMessageHandler(mongo);
        mongoHandler.setCollectionNameExpression(new LiteralExpression("product"));
        return mongoHandler;
    }

    @Bean
    @Autowired
    public IntegrationFlow processProduct(MongoDbFactory mongo) {
        return IntegrationFlows.from(mongoMessageSource(mongo), c -> c.poller(Pollers.fixedDelay(3, TimeUnit.SECONDS)))
                .route(Product::isPremium, this::routeProducts)
                .handle(mongoOutboundAdapter(mongo))
                .get();
    }

    private RouterSpec<Boolean, MethodInvokingRouter> routeProducts(RouterSpec<Boolean, MethodInvokingRouter> mapping) {
        return mapping
                .subFlowMapping(true, sf -> sf.handle(productProcessor(), "fastProcess"))
                .subFlowMapping(false, sf -> sf.handle(productProcessor(), "process"));
    }

    @Bean
    public ProductProcessor productProcessor() {
        return new ProductProcessor();
    }

    @Bean
    @Autowired
    public MessageSource<Object> mongoMessageSource(MongoDbFactory mongo) {
        MongoDbMessageSource messageSource = new MongoDbMessageSource(mongo, new LiteralExpression("{'processed' : false}"));
        messageSource.setExpectSingleResult(true);
        messageSource.setEntityClass(Product.class);
        messageSource.setCollectionNameExpression(new LiteralExpression("product"));

        return messageSource;
    }
}
