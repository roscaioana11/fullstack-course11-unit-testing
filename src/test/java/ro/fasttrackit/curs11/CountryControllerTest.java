package ro.fasttrackit.curs11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ro.fasttrackit.curs11.model.Country;
import ro.fasttrackit.curs11.service.CountryService;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ro.fasttrackit.curs11.CountryControllerTest.TestBeans;

@WebMvcTest
@ContextConfiguration(classes = {Curs11CodeApplication.class, TestBeans.class})
public class CountryControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CountryService countryService;

    @Test
    @DisplayName("GET /countries")
    void getAllCountriesTest() throws Exception {
        doReturn(List.of(
                new Country("abfds","Romania", 111111L),
                new Country("fdsa","Hungary", 111112L)
        )).when(countryService).getAll();

        mvc.perform(get("/countries"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[1].name", is("Hungary")));

        verify(countryService, times(1)).getAll();
    }

    @Configuration
    static class TestBeans {
        @Bean
        CountryService countryService() {
            return mock(CountryService.class);
        }
    }
}
