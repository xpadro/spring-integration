package xpadro.spring.integration.server.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import xpadro.spring.integration.server.model.ServerPerson;

@Service
public class PersonServiceImpl implements PersonService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Map<Long, ServerPerson> persons = new HashMap<Long, ServerPerson>();
	
	public PersonServiceImpl() {
		persons.put(1l, new ServerPerson(1, "John", 25));
		persons.put(2l, new ServerPerson(2, "Steve", 19));
		persons.put(3l, new ServerPerson(3, "Mike", 38));
		persons.put(4l, new ServerPerson(4, "Julia", 41));
	}

	@Override
	public ServerPerson getPerson(long id) {
		logger.info("retrieving person with id {}", id);
		return persons.get(id);
	}

	@Override
	public void updatePerson(ServerPerson person) {
		if (persons.get(person.getId()) != null) {
			logger.info("updating person with id {}", person.getId());
			persons.put(person.getId(), person);
		}
	}

	@Override
	public void insertPerson(ServerPerson person) {
		if (persons.get(person.getId()) == null) {
			logger.info("inserting new person with id {}", person.getId());
			persons.put(person.getId(), person);
		}
	}

	@Override
	public void deletePerson(long id) {
		logger.info("deleting person with id {}", id);
		persons.remove(id);
	}

}
