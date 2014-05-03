package xpadro.spring.integration.gateway;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(name = "entryGateway", defaultRequestChannel = "requestChannel")
public interface CourseService {

	public String findCourse(String courseId);
}
