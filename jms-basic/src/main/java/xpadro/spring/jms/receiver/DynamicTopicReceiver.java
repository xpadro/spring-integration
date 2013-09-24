package xpadro.spring.jms.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Notification;

@Component
public class DynamicTopicReceiver implements MessageListener {
	private NotificationRegistry registry;
	
	public DynamicTopicReceiver() {}
	
	public DynamicTopicReceiver(NotificationRegistry registry) {
		this.registry = registry;
	}
	
	@Override
	public void onMessage(Message message) {
		try {
			Notification notification = (Notification) ((ObjectMessage) message).getObject();
			registry.registerNotification(notification);
		}catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

}
