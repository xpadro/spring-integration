package xpadro.spring.integration.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.config.EnableIntegration;
import xpadro.spring.integration.mongodb.configuration.InfrastructureConfiguration;
import xpadro.spring.integration.mongodb.entity.Order;
import xpadro.spring.integration.mongodb.repository.ProductRepository;

@SpringBootApplication
@EnableIntegration
public class MongodbBasicApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MongodbBasicApplication.class, args);
		new MongodbBasicApplication().start(context);
	}

	public void start(ConfigurableApplicationContext context) {
		resetDatabase(context);

		Order order1 = new Order("1", true);
		Order order2 = new Order("2", false);
		Order order3 = new Order("3", true);

		InfrastructureConfiguration.OrderService orderService = context.getBean(InfrastructureConfiguration.OrderService.class);

		orderService.order(order1);
		orderService.order(order2);
		orderService.order(order3);
	}

	private void resetDatabase(ConfigurableApplicationContext context) {
		ProductRepository productRepository = context.getBean(ProductRepository.class);
		productRepository.deleteAll();
	}
}
