package xpadro.spring.integration.model;

import java.io.Serializable;

public class OrderConfirmation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public OrderConfirmation() {}
	
	public OrderConfirmation(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
