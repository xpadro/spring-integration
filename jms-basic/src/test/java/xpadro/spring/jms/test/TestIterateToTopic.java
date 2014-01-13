package xpadro.spring.jms.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.jms.model.Notification;
import xpadro.spring.jms.producer.Producer;
import xpadro.spring.jms.receiver.NotificationRegistry;

@ContextConfiguration(locations = {
			"/xpadro/spring/jms/config/jms-iter-config.xml", 
			"/xpadro/spring/jms/config/app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestIterateToTopic {
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private NotificationRegistry registry;
	
	@Before
	public void prepareTest() {
		registry.clear();
	}
	
	@Test
	public void testTopicSending() throws InterruptedException {
		Notification notification = null;
		
		for (int i=1;i<11;i++) {
			notification = new Notification(String.valueOf(i), "this is a topic");
			producer.convertAndSendTopic(notification);
		}
		
		Thread.sleep(2000);
		assertEquals(10, registry.getReceivedNotifications().size());
	}
}
