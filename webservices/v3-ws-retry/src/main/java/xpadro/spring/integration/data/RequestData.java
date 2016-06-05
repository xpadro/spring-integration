package xpadro.spring.integration.data;

import org.springframework.stereotype.Component;

import xpadro.spring.integration.types.ClientDataRequest;

/**
 * Stores the request that will be retrieved by the service activator when retrying the service invocation
 *
 */
@Component("requestData")
public class RequestData {

	private ClientDataRequest request;

	public ClientDataRequest getRequest() {
		return request;
	}

	public void setRequest(ClientDataRequest request) {
		this.request = request;
	}
	
}
