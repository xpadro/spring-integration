package xpadro.spring.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Notification;

@Component("asyncTopicFooReceiver")
public class AsyncTopicFooReceiver {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NotificationRegistry registry;

	public void receive(Notification notification) {
		registry.registerNotification(notification);
	}
	
	
	public void receiveIteration(Notification notification) {
		registry.registerNotification(notification);
		logger.info("Receiving notification {}", notification.getId());
	}
}
