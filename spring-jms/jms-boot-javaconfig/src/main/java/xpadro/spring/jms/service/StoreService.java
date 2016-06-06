package xpadro.spring.jms.service;

import java.util.Optional;

import xpadro.spring.jms.model.Order;

public interface StoreService {
	
	void registerOrder(Order order);
	
	Optional<Order> getReceivedOrder(String id);
}
