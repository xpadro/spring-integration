package xpadro.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration
@ComponentScan("xpadro.spring.integration.endpoint")	//@Component
@IntegrationComponentScan("xpadro.spring.integration.gateway")	//@MessagingGateway
@EnableIntegration
@Import({MongoDBConfiguration.class, WebServiceConfiguration.class})
public class InfrastructureConfiguration {

	@Bean
	@Description("Entry to the messaging system through the gateway.")
	public MessageChannel requestChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@Description("Sends request messages to the web service outbound gateway")
	public MessageChannel invocationChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@Description("Sends web service responses to both the client and a database")
	public MessageChannel responseChannel() {
		return new PublishSubscribeChannel();
	}
	
	@Bean
	@Description("Stores non filtered messages to the database")
	public MessageChannel storeChannel() {
		return new DirectChannel();
	}
	
}
