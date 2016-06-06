package xpadro.spring.integration.gateway;

import xpadro.spring.integration.types.ClientDataRequest;

public interface ClientService {

	/**
	 * Entry to the messaging system. All invocations to this method will be intercepted and sent to the SI "system entry" gateway
	 * 
	 * @param request
	 */
	public void invoke(ClientDataRequest request);
}
