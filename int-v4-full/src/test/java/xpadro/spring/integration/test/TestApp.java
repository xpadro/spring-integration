package xpadro.spring.integration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xpadro.spring.integration.configuration.InfrastructureConfiguration;
import xpadro.spring.integration.gateway.CourseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={InfrastructureConfiguration.class})
public class TestApp {
	@Autowired
	CourseService service;
	
	@Test
	public void testFlow() {
		String courseName = service.findCourse("BC-45");
		assertNotNull(courseName);
		assertEquals("Introduction to Java", courseName);
		
		courseName = service.findCourse("DF-21");
		assertNotNull(courseName);
		assertEquals("Functional Programming Principles in Scala", courseName);
	}
}
