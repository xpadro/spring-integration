package xpadro.spring.integration.trigger;

import java.util.Date;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

public class ServiceRetryTrigger implements Trigger {
	private long rate;
	
	
	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	
	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		if (triggerContext.lastScheduledExecutionTime() == null) {
			return new Date(System.currentTimeMillis());
		}
		
		return new Date(triggerContext.lastScheduledExecutionTime().getTime() + getRate());
	}

}
