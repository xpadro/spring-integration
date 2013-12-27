package xpadro.spring.integration.activator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.types.ClientDataResponse;

@Component("clientServiceActivator")
public class ClientServiceActivator {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Receives the result from service invocation
	 * @param msg
	 */
	public void handleServiceResult(Message<?> msg) {
		logger.info("Service result received: {}", ((ClientDataResponse) msg.getPayload()).getConfirmationId());
	}

}
