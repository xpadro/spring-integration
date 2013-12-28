package xpadro.spring.integration.activator;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.types.ClientDataRequest;
import xpadro.spring.integration.types.ClientDataResponse;

@Component("clientServiceActivator")
public class ClientServiceActivator {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("retryAdapter")
	private AbstractEndpoint retryAdapter;
	

	/**
	 * Receives the result from a succesful service invocation. Flow finishes here.
	 * @param msg
	 */
	public void handleServiceResult(Message<?> msg) {
		logger.info("Service result received: {}", ((ClientDataResponse) msg.getPayload()).getConfirmationId());
		retryAdapter.stop();
	}
	
	/**
	 * Service invocation failed. Activates trigger to start retries
	 * @param msg
	 */
	public void retryFailedInvocation(Message<?> msg) {
		logger.info("Service invocation failed. Activating retry trigger...");
		retryAdapter.start();
	}

	/**
	 * Retries the failed invocation. Sends the request message to the outbound service gateway
	 * @return
	 */
	public ClientDataRequest retryInvocation() {
		ClientDataRequest request = new ClientDataRequest();
		request.setClientId("123");
		request.setProductId("XA-55");
		request.setQuantity(new BigInteger("5"));
		
		logger.info("Retrying service invocation...");
		
		return request;
	}
	
	/**
	 * The service invocation won't succeed. Logs the failed request to the DB and finishes the flow.
	 * @param msg
	 */
	public void handleFailedInvocation(Message<?> msg) {
		//TODO log to DB
		logger.info("Failed to succesfully invoke service. Logging to DB...");
		retryAdapter.stop();
	}
}
