package xpadro.spring.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ws.MarshallingWebServiceOutboundGateway;
import org.springframework.messaging.MessageHandler;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WebServiceConfiguration {

	@Bean
	@ServiceActivator(inputChannel = "invocationChannel")
	public MessageHandler wsOutboundGateway() {
		MarshallingWebServiceOutboundGateway gw = new MarshallingWebServiceOutboundGateway("http://localhost:8080/spring-ws-courses/courses", jaxb2Marshaller());
		gw.setOutputChannelName("responseChannel");
		
		return gw;
	}
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    marshaller.setContextPath("xpadro.spring.integration.ws.types");

	    return marshaller;
	}
}
