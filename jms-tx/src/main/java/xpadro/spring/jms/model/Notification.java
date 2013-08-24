package xpadro.spring.jms.model;

import java.io.Serializable;

public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String message;
	
	
	public Notification(int id, String message) {
		this.id = id;
		this.message = message;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
