package xpadro.spring.integration.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerLauncher {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("xpadro/spring/integration/config/server-config.xml");
	}
}
