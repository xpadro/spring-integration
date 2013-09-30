package xpadro.spring.ws.service;

import xpadro.spring.ws.model.OrderConfirmation;

public interface OrderService {

	public OrderConfirmation order(String clientId, String productId, int quantity);
}
