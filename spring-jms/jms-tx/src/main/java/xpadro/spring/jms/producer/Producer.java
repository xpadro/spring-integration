package xpadro.spring.jms.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Notification;

/**
 * 
 * @author xpadro
 *
 */
@Component("producer")
public class Producer {
	private static Logger logger = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void convertAndSendMessage(String destination, Notification notification) {
		jmsTemplate.convertAndSend(destination, notification);
		logger.info("Sending notification | Id: "+notification.getId());
	}
}
