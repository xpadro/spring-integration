package xpadro.spring.ws.test;

import javax.xml.transform.Source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

@ContextConfiguration("classpath:xpadro/spring/ws/test/config/test-server-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestWebService {
	@Autowired
	ApplicationContext context;
	
	private MockWebServiceClient mockClient;
	
	@Test
	public void testValidOrderRequest() {
		Source requestPayload = new StringSource(
			      "<clientDataRequest xmlns='http://www.xpadro.spring.samples.com/orders' " +
			        "clientId='123' productId='XA-55' quantity='5'/>");
		
		Source responsePayload = new StringSource(
			      "<clientDataResponse xmlns='http://www.xpadro.spring.samples.com/orders' " +
			        "amount='55.99' confirmationId='GHKG34L' orderDate='2013-10-26+02:00'/>");
		
		RequestCreator creator = RequestCreators.withPayload(requestPayload);
		
		mockClient = MockWebServiceClient.createClient(context);
		mockClient.sendRequest(creator).andExpect(ResponseMatchers.payload(responsePayload));
	}
	
	@Test
	public void testInvalidOrderRequest() {
		Source requestPayload = new StringSource(
			      "<clientDataRequest xmlns='http://www.xpadro.spring.samples.com/orders' " +
			        "clientId='456' productId='XA-55' quantity='5'/>");
		
		Source responsePayload = new StringSource(
				"<SOAP-ENV:Fault xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/'>" +
				"<faultcode>SOAP-ENV:Server</faultcode><faultstring xml:lang='en'>Client [456] not found</faultstring></SOAP-ENV:Fault>");
		
		RequestCreator creator = RequestCreators.withPayload(requestPayload);
		
		mockClient = MockWebServiceClient.createClient(context);
		mockClient.sendRequest(creator).andExpect(ResponseMatchers.payload(responsePayload));
	}
}
