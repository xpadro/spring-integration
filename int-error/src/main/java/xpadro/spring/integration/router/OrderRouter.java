package xpadro.spring.integration.router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.model.Order;

/**
 * Redirects an order to the appropriate channel (sync or async) depending on the order id
 * 
 * @author xpadro
 *
 */
@Component("orderRouter")
public class OrderRouter {
	private static final Logger logger = LoggerFactory.getLogger(OrderRouter.class);
	private static final String ASYNC_CHANNEL = "asyncChannel";
	private static final String SYNC_CHANNEL = "syncChannel";
	

	public String redirectOrder(Order order) {
		if (order.getId() < 5) {
			logger.info("Redirecting to {}", SYNC_CHANNEL);
			return SYNC_CHANNEL;
		}
		else {
			logger.info("Redirecting to {}", ASYNC_CHANNEL);
			return ASYNC_CHANNEL;
		}
	}
}
