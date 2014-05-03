package xpadro.spring.integration.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.ws.types.GetCourseResponse;

@Component
public class StoredCoursesFilter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Filter(inputChannel="responseChannel", outputChannel="storeChannel")
	public boolean filterCourse(Message<GetCourseResponse> msg) {
		if (!msg.getPayload().getCourseId().startsWith("BC-")) {
			logger.info("Course [{}] filtered. Not a BF course", msg.getPayload().getCourseId());
			return false;
		}
		
		logger.info("Course [{}] validated. Storing to database", msg.getPayload().getCourseId());
		return true;
	}
}
