package xpadro.spring.integration.trigger;

import java.util.Date;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

@Component("serviceRetryTrigger")
public class ServiceRetryTrigger implements Trigger {

	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		if (triggerContext.lastCompletionTime() != null) {
			return new Date( triggerContext.lastCompletionTime().getTime() + 5000 );
		}
		return new Date( new Date().getTime() + 5000 );
	}

}
