package xpadro.spring.integration.mongodb.example.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xavier Padró
 */
@Repository
public interface PersonRepository extends MongoRepository<Person, Integer> {

}
