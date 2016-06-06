package xpadro.spring.jms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import xpadro.spring.jms.model.Order;

@Service
public class ClientServiceImpl implements ClientService {
	private static final String SIMPLE_QUEUE = "simple.queue";
	private static final String IN_QUEUE = "in.queue";
	
	private final JmsTemplate jmsTemplate;
	
	@Autowired
	public ClientServiceImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void addOrder(Order order) {
		jmsTemplate.convertAndSend(SIMPLE_QUEUE, order);
	}
	
	@Override
	public void registerOrder(Order order) {
		jmsTemplate.convertAndSend(IN_QUEUE, order);
	}
}
