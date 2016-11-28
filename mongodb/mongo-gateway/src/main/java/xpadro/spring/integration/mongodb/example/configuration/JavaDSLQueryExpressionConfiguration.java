package xpadro.spring.integration.mongodb.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import xpadro.spring.integration.mongodb.example.domain.Person;
import xpadro.spring.integration.mongodb.example.domain.RequestMessage;
import xpadro.spring.integration.mongodb.example.domain.ResultHandler;
import xpadro.spring.integration.mongodb.gateway.MongoDb;
import xpadro.spring.integration.mongodb.gateway.MongoDbOutboundGatewaySpec;

/**
 * @author Xavier Padró
 */
@ComponentScan("xpadro.spring.integration.mongodb")
@IntegrationComponentScan("xpadro.spring.integration.mongodb")
public class JavaDSLQueryExpressionConfiguration {

    @MessagingGateway
    public interface PersonService {

        @Gateway(requestChannel = "requestPerson.input")
        void send(RequestMessage requestMessage);
    }

    @Bean
    @Autowired
    public IntegrationFlow requestPerson(MongoDbFactory mongo) {
        return f -> f
                .handle(outboundGateway(mongo))
                .handle(resultHandler(), "handle");
    }

    @Bean
    public ResultHandler resultHandler() {
        return new ResultHandler();
    }

    private MongoDbOutboundGatewaySpec outboundGateway(MongoDbFactory mongo) {
        return MongoDb.outboundGateway(mongo)
                .queryExpression("payload.data")
                .collectionNameExpression(new LiteralExpression("person"))
                .expectSingleResult(true)
                .entityClass(Person.class);
    }
}
