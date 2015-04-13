package xpadro.spring.jms.service;

import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
	private String lastReceivedOrder;

	@Override
	public void registerOrderId(String orderId) {
		this.lastReceivedOrder = orderId;
	}

	@Override
	public String getLastReceivedOrderId() {
		return this.lastReceivedOrder;
	}
}
