package xpadro.spring.integration.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import xpadro.spring.integration.ws.types.GetCourseRequest;

@Component
public class CourseRequestBuilder {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Transformer(inputChannel="requestChannel", outputChannel="invocationChannel")
	public GetCourseRequest buildRequest(Message<String> msg) {
		logger.info("Building request for course [{}]", msg.getPayload());
		GetCourseRequest request = new GetCourseRequest();
		request.setCourseId(msg.getPayload());
		
		return request;
	}
}
