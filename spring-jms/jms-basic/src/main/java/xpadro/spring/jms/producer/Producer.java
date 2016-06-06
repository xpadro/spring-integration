package xpadro.spring.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Notification;

@Component("producer")
public class Producer {

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;
	

	public void convertAndSendMessage(Notification notification) {
		jmsTemplate.convertAndSend(notification);
	}
	
	public void convertAndSendMessage(String destination, Notification notification) {
		jmsTemplate.convertAndSend(destination, notification);
	}

	public void convertAndSendTopic(Notification notification) {
		jmsTopicTemplate.convertAndSend("test.topic", notification);
	}
}
