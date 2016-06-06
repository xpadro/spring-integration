package xpadro.spring.integration.jdbc.model;

public class Order {

	private final int orderId;
	private final String description;
	
	public Order(int orderId, String description) {
		this.orderId = orderId;
		this.description = description;
	}
	
	public int getOrderId() {
		return this.orderId;
	}
	
	public String getDescription() {
		return this.description;
	}
}
