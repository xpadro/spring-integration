package xpadro.spring.integration.mongodb.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.EnableIntegration;
import xpadro.spring.integration.mongodb.example.configuration.JavaDSLQueryConfiguration;
import xpadro.spring.integration.mongodb.example.configuration.JavaDSLQueryExpressionConfiguration;
import xpadro.spring.integration.mongodb.example.configuration.JavaDSLQueryExpressionListConfiguration;
import xpadro.spring.integration.mongodb.example.domain.RequestMessage;

/**
 * @author Xavier Padró
 */
@SpringBootApplication
@EnableIntegration
@Import(JavaDSLQueryExpressionListConfiguration.class)
public class JavaDSLQueryExpressionListApplication extends AbstractApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JavaDSLQueryExpressionListApplication.class, args);
		new JavaDSLQueryExpressionListApplication().start(context);
	}

	public void start(ConfigurableApplicationContext context) {
		resetDatabase(context);

		JavaDSLQueryConfiguration.PersonService personService = context.getBean(JavaDSLQueryConfiguration.PersonService.class);
		personService.send(new RequestMessage("{}"));
	}
}
