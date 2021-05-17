package ro.fasttrackit.curs11;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ro.fasttrackit.curs11.model.Country;
import ro.fasttrackit.curs11.repository.CountryRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryAppTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CountryRepository repository;

    @SneakyThrows
    @Test
    @DisplayName("GET /countries")
    void getAllCountriesTest() {
        repository.saveAll(List.of(
                new Country(null, "Romania", 1111111L),
                new Country(null, "Moldova", 111112L),
                new Country(null, "Bulgaria", 1111131L),
                new Country(null, "Macedonia", 1111141L)
        ));

        mvc.perform(get("/countries"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[1].name", is("Moldova")));
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }
}
