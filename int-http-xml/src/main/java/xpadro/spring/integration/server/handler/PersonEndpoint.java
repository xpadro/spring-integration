package xpadro.spring.integration.server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.server.model.ServerPerson;
import xpadro.spring.integration.server.service.PersonService;

@Component
public class PersonEndpoint {
	private static final String STATUSCODE_HEADER = "http_statusCode";
	
	@Autowired
	private PersonService service;
	
	public Message<?> get(Message<String> msg) {
		long id = Long.valueOf(msg.getPayload());
		ServerPerson person = service.getPerson(id);
		
		if (person == null) {
			return MessageBuilder.fromMessage(msg)
					.copyHeadersIfAbsent(msg.getHeaders())
					.setHeader(STATUSCODE_HEADER, HttpStatus.NOT_FOUND)
					.build(); 
		}
		
		return MessageBuilder.withPayload(person)
				.copyHeadersIfAbsent(msg.getHeaders())
				.setHeader(STATUSCODE_HEADER, HttpStatus.OK)
				.build();
	}
	
	public void put(Message<ServerPerson> msg) {
		service.updatePerson(msg.getPayload());
	}
	
	public void post(Message<ServerPerson> msg) {
		service.insertPerson(msg.getPayload());
	}
	
	public void delete(Message<String> msg) {
		long id = Long.valueOf(msg.getPayload());
		service.deletePerson(id);
	}
}
