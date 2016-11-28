package xpadro.spring.integration.mongodb.example.domain;

import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Xavier Padró
 */
public class ResultServiceActivator {

    @ServiceActivator(inputChannel = "personOutput")
    public void handle(Person person) {
        System.out.println(String.format("Person retrieved: %s", person));
    }
}
