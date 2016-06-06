package xpadro.spring.jms.test;

import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.QueueBrowser;
import javax.jms.Session;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import xpadro.spring.jms.producer.Producer;

/**
 * 
 * @author xpadro
 *
 */
@ContextConfiguration(locations = {"/xpadro/spring/jms/config/app-config.xml"})
@DirtiesContext
public class TestBaseMessaging {
	protected static final String QUEUE_INCOMING = "incoming.queue";
	protected static final String QUEUE_DLQ = "ActiveMQ.DLQ";

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected JmsTemplate jmsTemplate;
	
	@Autowired
	protected Producer producer;
	
	
	@Before
	public void prepareTest() {
		jdbcTemplate.update("delete from Notifications");
	}
	
	/**
	 * Returns total notifications saved to the DB
	 * 
	 * @return
	 */
	protected int getSavedNotifications() {
		return jdbcTemplate.queryForObject("select count(*) from Notifications", Integer.class);
	}
	
	/**
	 * Returns total messages that are still in the specified queue, waiting for retrieval
	 * 
	 * @param queueName
	 * @return
	 */
	protected int getMessagesInQueue(String queueName) {
		return jmsTemplate.browse(queueName, new BrowserCallback<Integer>() {
			@Override
			public Integer doInJms(Session session, QueueBrowser browser) throws JMSException {
				Enumeration<?> messages = browser.getEnumeration();
				int total = 0;
				while (messages.hasMoreElements()) {
					messages.nextElement();
					total++;
				}

				return total;
			}
		});
	}
}
