package xpadro.spring.jms.service;

public interface RegisterService {
	void registerOrderId(String orderId);
	
	String getLastReceivedOrderId();
}
