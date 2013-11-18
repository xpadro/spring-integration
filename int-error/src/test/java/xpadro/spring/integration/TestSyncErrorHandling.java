package xpadro.spring.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.model.Order;
import xpadro.spring.integration.service.OrderService;

@ContextConfiguration(locations = {"/xpadro/spring/integration/config/int-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSyncErrorHandling {

	@Autowired
	private OrderService service;
	
	
	@Test
	public void testSyncErrorHandling() {
		service.sendOrder(new Order(1, "an order"));
	}
}
