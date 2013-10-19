package xpadro.spring.integration.client;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.model.Employee;
import xpadro.spring.integration.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:xpadro/spring/integration/test/config/client-gateway-config.xml"})
public class TestRmiGatewayClient {
	@Autowired
	private EmployeeService service;

	@Test
	public void retrieveExistingEmployee() {
		Employee employee = service.retrieveEmployee(2);
		Assert.assertNotNull(employee);
		Assert.assertEquals(2, employee.getId());
		Assert.assertEquals("Bruce Springsteen", employee.getName());
	}
}
