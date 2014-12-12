package xpadro.spring.integration.test.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private int myId;
	private String name;
	
	public ClientPerson() {}
	
	public ClientPerson(int id, String name) {
		this.myId = id;
		this.name = name;
	}
	
	public void setMyId(int id) {
		this.myId = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMyId() {
		return this.myId;
	}
	
	public String getName() {
		return this.name;
	}

}
