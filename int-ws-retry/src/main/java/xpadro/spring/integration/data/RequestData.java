package xpadro.spring.integration.data;

import org.springframework.stereotype.Component;

import xpadro.spring.integration.types.ClientDataRequest;

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
