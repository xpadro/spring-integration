package xpadro.spring.integration.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.exception.InvalidOrderException;
import xpadro.spring.integration.model.Order;
import xpadro.spring.integration.model.OrderConfirmation;

/**
 * Processor that receives an order and returns an order confirmation
 * 
 * @author xpadro
 *
 */
@Component("orderProcessor")
public class OrderProcessor {
	private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);
	private static final String ERROR_INVALID_ID = "Order ID is invalid";

	public OrderConfirmation processOrder(Order order) {
		logger.info("Processing order {}", order.getId());
		
		if (isInvalidOrder(order)) {
			logger.info("Error while processing order [{}]", ERROR_INVALID_ID);
			throw new InvalidOrderException(ERROR_INVALID_ID);
		}
		
		return new OrderConfirmation("confirmed");
	}
	
	private boolean isInvalidOrder(Order order) {
		if (order.getId() == 1 || order.getId() == 6) {
			return true;
		}
		return false;
	}
}
