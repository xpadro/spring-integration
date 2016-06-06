package xpadro.spring.integration.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

@Component("clientInterceptor")
public class ServiceClientInterceptor implements ClientInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean handleFault(MessageContext context) throws WebServiceClientException {
		logger.info("handle fault");
		return true;
	}

	@Override
	public boolean handleRequest(MessageContext context) throws WebServiceClientException {
		logger.info("invoking service...");
		
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext context) throws WebServiceClientException {
		logger.info("returning from service...");
		
		return true;
	}

}
