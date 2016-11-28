package xpadro.spring.integration.mongodb.example.domain;

/**
 * @author Xavier Padró
 */
public class ResultHandler {

    public void handle(Person person) {
        System.out.println(String.format("Person retrieved: %s", person));
    }
}
