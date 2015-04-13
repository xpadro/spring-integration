package xpadro.spring.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import xpadro.spring.jms.service.RegisterService;

@Component
public class OutListener {
	private final RegisterService registerService;
	
	@Autowired
	public OutListener(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@JmsListener(destination = "out.queue")
	public void receiveOrder(String orderId) {
		registerService.registerOrderId(orderId);
	}

}
