package xpadro.spring.integration.router;

import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

@Component("resultRouter")
public class ServiceResultRouter {
	private final ReentrantLock lock = new ReentrantLock();
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private int maxRetries = 3;
	private int currentRetries;
	
	private static final String FAIL_CHANNEL = "failedChannel";
	private static final String RETRY_CHANNEL = "retryChannel";


	/**
	 * The service invocation failed. If maxRetries aren't reached, it will send the request to the retry channel. Otherwise, it will send the failed request
	 * to the failed channel in order to log the request and finish the flow.
	 * @param msg
	 * @return
	 */
	public String handleServiceError(Message<?> msg) {
		lock.lock();
		try {
			logger.info("Handling service failure");
			if (maxRetries > 0) {
				currentRetries++;
				if (currentRetries > maxRetries) {
					logger.info("Max retries [{}] reached", maxRetries);
					return FAIL_CHANNEL; 
				}
			}
			
			logger.info("Retry number {} of {}", currentRetries, maxRetries);
			return RETRY_CHANNEL;
		} finally {
			lock.unlock();
		}
	}
}
