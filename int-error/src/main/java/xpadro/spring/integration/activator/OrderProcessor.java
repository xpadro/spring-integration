package xpadro.spring.integration.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.model.Order;

@Component("orderProcessor")
public class OrderProcessor {
	private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

	public void processOrder(Order order) {
		logger.info("Processing order {}", order.getId());
	}
}
