package xpadro.spring.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Order;
import xpadro.spring.jms.service.StoreService;

@Component
public class SimpleListener {
	private final StoreService storeService;
	
	@Autowired
	public SimpleListener(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@JmsListener(destination = "simple.queue")
	public void receiveOrder(Order order) {
		storeService.registerOrder(order);
	}
}
