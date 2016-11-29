package xpadro.spring.integration.mongodb.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import xpadro.spring.integration.mongodb.example.domain.Person;
import xpadro.spring.integration.mongodb.example.domain.RequestMessage;
import xpadro.spring.integration.mongodb.example.domain.ResultServiceActivator;
import xpadro.spring.integration.mongodb.gateway.MongoDbExecutor;
import xpadro.spring.integration.mongodb.gateway.MongoDbOutboundGateway;

/**
 * @author Xavier Padró
 */
@ComponentScan("xpadro.spring.integration.mongodb")
@IntegrationComponentScan("xpadro.spring.integration.mongodb")
public class JavaConfigQueryConfiguration {

    @MessagingGateway
    public interface PersonService {

        @Gateway(requestChannel = "personInput")
        void send(RequestMessage requestMessage);
    }

    @Bean
    public ResultServiceActivator resultHandler() {
        return new ResultServiceActivator();
    }

    @Bean
    @ServiceActivator(inputChannel = "personInput")
    public MessageHandler mongodbOutbound(MongoDbFactory mongo) {
        MongoDbExecutor mongoDbExecutor = new MongoDbExecutor(mongo);
        mongoDbExecutor.setCollectionNameExpression(new LiteralExpression("person"));
        mongoDbExecutor.setMongoDbQueryExpression("payload.data");
        mongoDbExecutor.setExpectSingleResult(true);
        mongoDbExecutor.setEntityClass(Person.class);

        MongoDbOutboundGateway gateway = new MongoDbOutboundGateway(mongoDbExecutor);
        gateway.setOutputChannelName("personOutput");

        return gateway;
    }
}
