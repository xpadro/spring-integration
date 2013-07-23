package xpadro.spring.jms.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Notification;

@Component("asyncTopicFooReceiver")
public class AsyncTopicFooReceiver {
	@Autowired
	private NotificationRegistry registry;

	public void receive(Notification notification) {
		registry.registerNotification(notification);
	}
}
