package xpadro.spring.integration.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.SocketTimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.WebServiceIOException;

import xpadro.spring.integration.ws.gateway.CourseService;
import xpadro.spring.integration.ws.types.GetCourseRequest;
import xpadro.spring.integration.ws.types.GetCourseResponse;

@ContextConfiguration(locations = {"/xpadro/spring/integration/ws/config/int-course-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestIntegrationApp {

	@Autowired
	private CourseService service;
	
	@Test
	public void invokeNormalOperation() {
		GetCourseRequest request = new GetCourseRequest();
		request.setCourseId("BC-45");
		
		GetCourseResponse response = service.getCourse(request);
		assertNotNull(response);
		assertEquals("Introduction to Java", response.getName());
	}
	
	@Test
	public void invokeTimeoutOperation() {
		try {
			GetCourseRequest request = new GetCourseRequest();
			request.setCourseId("DF-21");
			
			GetCourseResponse response = service.getCourse(request);
			assertNull(response);
		} catch (WebServiceIOException e) {
			assertTrue(e.getCause() instanceof SocketTimeoutException);
		}
	}
}
