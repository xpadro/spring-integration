package xpadro.spring.integration.mongodb.example;

import org.springframework.context.ConfigurableApplicationContext;
import xpadro.spring.integration.mongodb.example.domain.Person;
import xpadro.spring.integration.mongodb.example.domain.PersonRepository;

/**
 * @author Xavier Padró
 */
public class AbstractApplication {

    protected void resetDatabase(ConfigurableApplicationContext context) {
        PersonRepository personRepository = context.getBean(PersonRepository.class);
        personRepository.deleteAll();

        personRepository.save(new Person(1, "John"));
        personRepository.save(new Person(2, "Anna"));
        personRepository.save(new Person(3, "Xavi"));
    }
}
