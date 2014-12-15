package xpadro.spring.integration.server.service;

import xpadro.spring.integration.server.model.ServerPerson;

public interface PersonService {

	ServerPerson getPerson(long id);
	
	void updatePerson(ServerPerson person);
	
	void insertPerson(ServerPerson person);
	
	void deletePerson(long id);
}
