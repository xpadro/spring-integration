package xpadro.spring.integration.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.ws.types.GetCourseResponse;

@Component
public class CourseResponseHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ServiceActivator(inputChannel="responseChannel")
	public String getResponse(Message<GetCourseResponse> msg) {
		GetCourseResponse course = msg.getPayload();
		logger.info("Course with ID [{}] received: {}", course.getCourseId(), course.getName());
		
		return course.getName();
	}
}
