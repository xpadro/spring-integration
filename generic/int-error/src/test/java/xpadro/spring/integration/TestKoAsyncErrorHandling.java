package xpadro.spring.integration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.model.Order;
import xpadro.spring.integration.model.OrderConfirmation;
import xpadro.spring.integration.service.OrderService;

@ContextConfiguration(locations = {"/xpadro/spring/integration/config/int-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestKoAsyncErrorHandling {

	@Autowired
	private OrderService service;
	
	@Test
	public void testCorrectOrder() throws InterruptedException, ExecutionException {
		Future<OrderConfirmation> confirmation = service.sendFutureOrder(new Order(7, "another correct order"));
		OrderConfirmation orderConfirmation = confirmation.get();
		Assert.assertNotNull(orderConfirmation);
		Assert.assertEquals("confirmed", orderConfirmation.getId());
	}
	
	
	/*@Test(expected=MessageHandlingException.class)
	 * This test will fail, since the exception will never reach the caller
	 */
	public void testAsyncErrorHandling() throws InterruptedException, ExecutionException {
		Future<OrderConfirmation> confirmation = service.sendFutureOrder(new Order(6, "another order"));
	}
}
