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


	public String handleServiceError(Message<?> msg) {
		lock.lock();
		try {
			logger.info("Handling service failure");
			if (maxRetries > 0) {
				currentRetries++;
				if (currentRetries > maxRetries) {
					logger.info("Max retries reached");
					return FAIL_CHANNEL;
				}
			}
			
			logger.info("Retry number {}", currentRetries);
			return RETRY_CHANNEL;
		} finally {
			lock.unlock();
		}
	}
}
