package xpadro.spring.jms.receiver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Notification;

@Component("notificationRegistry")
public class NotificationRegistry {

	private List<Notification> receivedNotifications = new ArrayList<Notification>();
	
	public List<Notification> getReceivedNotifications() {
		return receivedNotifications;
	}
	
	public void registerNotification(Notification notification) {
		receivedNotifications.add(notification);
	}
}
