package xpadro.spring.integration.client;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:xpadro/spring/integration/test/config/client-config.xml"})
public class TestRmiClient {
	@Autowired
	MessageChannel localChannel;
	
	@Autowired
	MessagingTemplate template;

	@Test
	public void retrieveExistingEmployee() {
		Employee employee = (Employee) template.convertSendAndReceive(localChannel, 2);

		Assert.assertNotNull(employee);
		Assert.assertEquals(2, employee.getId());
		Assert.assertEquals("Bruce Springsteen", employee.getName());
	}
}
