package xpadro.spring.ws.service.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import xpadro.spring.ws.exception.ClientNotFoundException;
import xpadro.spring.ws.model.OrderConfirmation;
import xpadro.spring.ws.service.OrderService;

@Service
public class StubOrderService implements OrderService {
	private static final String VALID_CLIENT_ID = "123";
	
	@Override
	public OrderConfirmation order(String clientId, String productId, int quantity) {
		if (!VALID_CLIENT_ID.equals(clientId)) {
			throw new ClientNotFoundException("Client ["+clientId+"] not found");
		}
		
		OrderConfirmation response = new OrderConfirmation();
		response.setAmount(55.99f);
		response.setConfirmationId("GHKG34L");
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 9, 26);
		response.setOrderDate(cal.getTime());
		
		return response;
	}
}
