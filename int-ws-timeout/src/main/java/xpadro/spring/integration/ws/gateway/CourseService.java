package xpadro.spring.integration.ws.gateway;

import org.springframework.integration.annotation.Gateway;

import xpadro.spring.integration.ws.types.GetCourseRequest;
import xpadro.spring.integration.ws.types.GetCourseResponse;

public interface CourseService {

	@Gateway
	GetCourseResponse getCourse(GetCourseRequest request);
}
