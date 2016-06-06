package xpadro.spring.integration.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.MessagingException;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.types.ClientDataResponse;

@Component("clientServiceActivator")
public class ClientServiceActivator {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	/**
	 * Receives the result from a succesful service invocation. Flow finishes here.
	 * @param msg
	 */
	public void handleServiceResult(Message<?> msg) {
		logger.info("Service result received: {}", ((ClientDataResponse) msg.getPayload()).getConfirmationId());
	}
	
	/**
	 * The service invocation won't succeed. Logs the failed request to the DB and finishes the flow.
	 * @param exception
	 */
	public Message<?> handleFailedInvocation(MessagingException exception) {
		logger.info("Failed to receive response. Request stored to DB...");
		return exception.getFailedMessage();
	}
}
