package xpadro.spring.jms.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Notification;

@Component("asyncTopicBarReceiver")
public class AsyncTopicBarReceiver {
	@Autowired
	private NotificationRegistry registry;

	public void receive(Notification notification) {
		registry.registerNotification(notification);
	}
}
