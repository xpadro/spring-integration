package xpadro.spring.integration.server.model;

import java.io.Serializable;

public class ServerPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private Integer age;
	
	public ServerPerson() {}
	
	public ServerPerson(long id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
}
