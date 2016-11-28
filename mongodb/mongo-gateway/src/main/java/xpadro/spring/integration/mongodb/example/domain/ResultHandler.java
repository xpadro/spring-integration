package xpadro.spring.integration.mongodb.example.domain;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xavier Padró
 */
public class ResultHandler {

    public void handle(Person person) {
        System.out.println(String.format("Person retrieved: %s", person));
    }

    public void handle(List<Person> persons) {
        String names = persons.stream().map(Person::getName).collect(Collectors.joining(", "));
        System.out.println(String.format("Persons retrieved: %s", names));
    }
}
