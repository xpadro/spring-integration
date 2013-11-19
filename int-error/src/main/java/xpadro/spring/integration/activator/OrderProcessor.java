package xpadro.spring.integration.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.exception.InvalidOrderException;
import xpadro.spring.integration.model.Order;

@Component("orderProcessor")
public class OrderProcessor {
	private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
	private static final String ERROR_INVALID_ID = "Order ID is invalid";

	public void processOrder(Order order) {
		logger.info("Processing order {}", order.getId());
		
		if (order.getId() == 1 || order.getId() == 2) {
			logger.info("Error while processing order [{}]", ERROR_INVALID_ID);
			throw new InvalidOrderException(ERROR_INVALID_ID);
		}
	}
}
