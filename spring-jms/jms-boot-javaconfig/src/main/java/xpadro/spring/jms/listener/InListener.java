package xpadro.spring.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.model.Order;
import xpadro.spring.jms.service.StoreService;

@Component
public class InListener {
	private final StoreService storeService;
	
	@Autowired
	public InListener(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@JmsListener(destination = "in.queue")
	@SendTo("out.queue")
	public String receiveOrder(Order order) {
		storeService.registerOrder(order);
		return order.getId();
	}
}
