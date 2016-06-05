package xpadro.spring.integration;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.gateway.ClientService;
import xpadro.spring.integration.types.ClientDataRequest;

@ContextConfiguration({"classpath:xpadro/spring/integration/config/int-config.xml",
	"classpath:xpadro/spring/integration/config/mongodb-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestInvocation {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClientService service;
	
	@Test
	public void testInvocation() throws InterruptedException, ExecutionException {
		logger.info("Initiating service request...");
		
		ClientDataRequest request = new ClientDataRequest();
		request.setClientId("123");
		request.setProductId("XA-55");
		request.setQuantity(new BigInteger("5"));
		
		service.invoke(request);
		logger.info("Doing other stuff...");
		
		Thread.sleep(60000);
	}
}
