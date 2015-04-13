package xpadro.spring.jms.service;

import xpadro.spring.jms.model.Order;

public interface ClientService {
	
	void addOrder(Order order);
	
	void registerOrder(Order order);
}
