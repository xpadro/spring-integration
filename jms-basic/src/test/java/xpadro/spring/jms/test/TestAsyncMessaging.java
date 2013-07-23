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
			"/xpadro/spring/jms/config/jms-config.xml", 
			"/xpadro/spring/jms/config/app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAsyncMessaging {
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private NotificationRegistry registry;
	
	@Before
	public void prepareTest() {
		registry.clear();
	}
	
	@Test
	public void testAsynchronizedReceiving() throws InterruptedException {
		Notification notification = new Notification("2", "this is another message");
		producer.convertAndSendMessage("test.async.queue", notification);
		
		Thread.sleep(2000);
		
		assertEquals(1, registry.getReceivedNotifications().size());
		assertEquals("this is another message", registry.getReceivedNotifications().get(0).getMessage());
	}
}
