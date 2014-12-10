package xpadro.spring.integration.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PersonService service;
	
	public Message<?> get(Message<String> msg) {
		long id = Long.valueOf(msg.getPayload());
		ServerPerson person = service.getPerson(id);
		
		if (person == null) {
			logger.info("GET|person with id {} not found", id);
			return MessageBuilder.fromMessage(msg)
					.copyHeadersIfAbsent(msg.getHeaders())
					.setHeader(STATUSCODE_HEADER, HttpStatus.NOT_FOUND)
					.build(); 
		}
		
		logger.info("GET|returning person with id {}", id);
		return MessageBuilder.withPayload(person)
				.copyHeadersIfAbsent(msg.getHeaders())
				.setHeader(STATUSCODE_HEADER, HttpStatus.OK)
				.build();
	}
	
}
