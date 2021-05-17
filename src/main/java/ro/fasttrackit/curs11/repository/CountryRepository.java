package ro.fasttrackit.curs11.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs11.model.Country;

import java.util.Optional;

public interface CountryRepository extends MongoRepository<Country, String> {
    Optional<Country> findByName(String name);
}
