package xpadro.spring.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JmsJavaconfigApplication {
	private static final String JMS_BROKER_URL = "vm://embedded?broker.persistent=false,useShutdownHook=false";
	
	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(JMS_BROKER_URL);
	}

    public static void main(String[] args) {
    	SpringApplication.run(JmsJavaconfigApplication.class, args);
    }
}