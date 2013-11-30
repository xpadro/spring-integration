package xpadro.spring.integration.activator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHandlingException;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.jdbc.core.JdbcTemplate;

import xpadro.spring.integration.model.Order;

/**
 * Handler subscribed to the global error channel. It will store the error to the database
 * 
 * @author xpadro
 *
 */
public class OrderErrorHandler {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@ServiceActivator
	public void handleFailedOrder(Message<MessageHandlingException> message) {
		Order requestedOrder = (Order) message.getPayload().getFailedMessage().getPayload();
		saveToBD(requestedOrder.getId(), message.getPayload().getMessage());
	}
	
	private void saveToBD(int orderId, String errorMessage) {
		String query = "insert into errors(orderid, message) values (?,?)";
		jdbcTemplate.update(query, orderId, errorMessage);
	}
}
