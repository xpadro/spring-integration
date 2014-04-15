package xpadro.spring.integration.jdbc;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.jdbc.model.Order;


@ContextConfiguration(locations = {"/xpadro/spring/integration/config/jdbc-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestJdbcConfig {
	private static final Logger logger = LoggerFactory.getLogger(TestJdbcConfig.class);
	
	@Autowired
	MyRepository repository;
	

	@Test
	public void testAccessMySQLDatabase() {
		Order order = repository.getOrder(2);
		Assert.assertEquals("PK2222", order.getDescription());
		logger.info("Order from JDBC query: {}", order.getDescription());
	}
	
	@Test
	public void testCallStoredProcedure() {
		String result = repository.getDescriptionFromStoredProcedure(2);
		Assert.assertEquals("PK2222", result);
		logger.info("Order from JDBC stored procedure: {}", result);
	}
}
