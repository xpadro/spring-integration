package xpadro.spring.jms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import xpadro.spring.jms.model.Order;

@Service
public class StoreServiceImpl implements StoreService {
	private final List<Order> receivedOrders = new ArrayList<>();

	@Override
	public void registerOrder(Order order) {
		this.receivedOrders.add(order);
	}
	
	@Override
	public Optional<Order> getReceivedOrder(String id) {
		return receivedOrders.stream().filter(o -> o.getId().equals(id)).findFirst();
	}
}