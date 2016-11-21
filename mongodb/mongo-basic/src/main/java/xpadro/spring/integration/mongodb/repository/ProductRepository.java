package xpadro.spring.integration.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xpadro.spring.integration.mongodb.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
