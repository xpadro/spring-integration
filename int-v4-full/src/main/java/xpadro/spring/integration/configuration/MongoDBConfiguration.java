package xpadro.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mongodb.outbound.MongoDbStoringMessageHandler;
import org.springframework.messaging.MessageHandler;

import com.mongodb.MongoClient;

@Configuration
public class MongoDBConfiguration {

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "si4Db");
	}

	@Bean
	@ServiceActivator(inputChannel = "storeChannel")
	public MessageHandler mongodbAdapter() throws Exception {
		MongoDbStoringMessageHandler adapter = new MongoDbStoringMessageHandler(mongoDbFactory());
		adapter.setCollectionNameExpression(new LiteralExpression("courses"));
		
		return adapter;
	}
}
