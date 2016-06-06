package xpadro.spring.jms.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import xpadro.spring.jms.model.Notification;

/**
 * Listener that receives notifications and saves them to the DB
 * 
 * @author xpadro
 *
 */
@Component("notificationProcessor")
public class NotificationProcessor implements MessageListener {
	private static Logger logger = LoggerFactory.getLogger(NotificationProcessor.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void onMessage(Message message) {
		try {
			Notification notification = (Notification) ((ObjectMessage) message).getObject();
			logger.info("Received notification | Id: "+notification.getId()+" | Redelivery: "+getDeliveryNumber(message));

			checkPreprocessException(notification);
			saveToBD(notification);
			checkPostprocessException(message, notification);
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}
	
	/**
	 * Execution failure after receiving the message and before saving it to the DB
	 * 
	 * @param notification
	 */
	private void checkPreprocessException(Notification notification) {
		if (notification.getId() == 1) {
			throw new RuntimeException("error after receiving message");
		}
	}
	
	/**
	 * Execution failure after saving the message to the DB
	 * 
	 * @param message
	 * @param notification
	 * @throws JMSException
	 */
	private void checkPostprocessException(Message message, Notification notification) throws JMSException {
		if (notification.getId() == 2 && getDeliveryNumber(message) < 2) {
			throw new RuntimeException("error after processing message");
		}
	}
	
	/**
	 * Returns how many times the message has been delivered
	 * 
	 * @param message
	 * @return
	 * @throws JMSException
	 */
	private int getDeliveryNumber(Message message) throws JMSException {
		return message.getIntProperty("JMSXDeliveryCount");
	}
	
	@Transactional
	private void saveToBD(Notification notification) {
		String query = "insert into NOTIFICATIONS(id, message) values (?,?)";
		jdbcTemplate.update(query, notification.getId(), notification.getMessage());
	}
}
