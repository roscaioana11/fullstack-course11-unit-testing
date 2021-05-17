package ro.fasttrackit.curs11;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ro.fasttrackit.curs11.model.Country;
import ro.fasttrackit.curs11.repository.CountryRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CountryRepositoryTest {
    @Autowired
    private CountryRepository repository;

    @Test
    @DisplayName("WHEN findByName is called THEN all countries are returned")
    void findByNameTest() {
        repository.saveAll(List.of(
                new Country(null, "Romania", 1111111L),
                new Country(null, "Moldova", 111112L),
                new Country(null, "Bulgaria", 1111131L),
                new Country(null, "Macedonia", 1111141L)
        ));

        Optional<Country> result = repository.findByName("Moldova");
        assertThat(result).isNotEmpty();
        assertThat(result.get()).extracting("name", "population")
                .containsExactly("Moldova", 111112L);
    }

    @Test
    @DisplayName("WHEN findByName is called on no countries THEN empty is returned")
    void noCountriesTest() {
        List<Country> all = repository.findAll();
        System.out.println(all);
        Optional<Country> result = repository.findByName("Moldova");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("WHEN findByName is called for non-existing THEN empty is returned")
    void findByMissingNameTest() {
        repository.saveAll(List.of(
                new Country(null, "Romania", 1111111L),
                new Country(null, "Moldova", 111112L),
                new Country(null, "Bulgaria", 1111131L),
                new Country(null, "Macedonia", 1111141L)
        ));

        Optional<Country> result = repository.findByName("missing");
        assertThat(result).isEmpty();
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }
}
