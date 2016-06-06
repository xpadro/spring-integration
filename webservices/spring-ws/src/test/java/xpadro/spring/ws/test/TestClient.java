package xpadro.spring.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import xpadro.spring.ws.types.ClientDataRequest;
import xpadro.spring.ws.types.ClientDataResponse;

@ContextConfiguration("classpath:xpadro/spring/ws/test/config/client-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestClient {
	@Autowired 
	WebServiceTemplate wsTemplate;
	
	@Test
	public void invokeOrderService() throws Exception {
		ClientDataRequest request = new ClientDataRequest();
		request.setClientId("123");
		request.setProductId("XA-55");
		request.setQuantity(new BigInteger("5", 10));

		ClientDataResponse response = (ClientDataResponse) wsTemplate.marshalSendAndReceive(request);
				
		assertNotNull(response);
		assertEquals(new BigDecimal("55.99"), response.getAmount());
		assertEquals("GHKG34L", response.getConfirmationId());
	}
}
