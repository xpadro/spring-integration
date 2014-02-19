package xpadro.spring.integration;

import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import junit.framework.Assert;

import static org.hamcrest.Matchers.containsString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.model.Order;
import xpadro.spring.integration.model.OrderConfirmation;
import xpadro.spring.integration.service.OrderService;



@ContextConfiguration(locations = {"/xpadro/spring/integration/config/int-async-config.xml",
		"/xpadro/spring/integration/config/db-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestOkAsyncErrorHandling {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private OrderService service;
	
	@Before
	public void prepareTest() {
		jdbcTemplate.update("delete from errors");
	}
	
	@Test
	public void testCorrectOrder() throws InterruptedException, ExecutionException {
		Future<OrderConfirmation> confirmation = service.sendFutureOrder(new Order(7, "another correct order"));
		OrderConfirmation orderConfirmation = confirmation.get();
		Assert.assertNotNull(orderConfirmation);
		Assert.assertEquals("confirmed", orderConfirmation.getId());
	}
	
	@Test
	public void testAsyncErrorHandling() throws InterruptedException, ExecutionException {
		Future<OrderConfirmation> confirmation = service.sendFutureOrder(new Order(6, "another order"));
		Thread.sleep(2000);
		Assert.assertEquals(1, getSavedErrors());
		validateSavedError(6);
	}
	
	private int getSavedErrors() {
		return jdbcTemplate.queryForObject("select count(*) from errors", Integer.class);
	}
	
	private void validateSavedError(int orderId) {
		String query = "select * from errors where orderid=?";
		Map<String, Object> result = jdbcTemplate.queryForMap(query, orderId);
		Assert.assertEquals(orderId, result.get("orderid"));
		assertThat((String)result.get("message"), containsString("Order ID is invalid"));
	}
}
