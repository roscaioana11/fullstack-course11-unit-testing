package ro.fasttrackit.curs11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ro.fasttrackit.curs11.model.Country;
import ro.fasttrackit.curs11.repository.CountryRepository;
import ro.fasttrackit.curs11.service.CountryApi;
import ro.fasttrackit.curs11.service.CountryService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CountryServiceTest {
    private static final List<String> NOT_COUNTRIES = List.of("Wakanda", "Mordor");
    private CountryService countryService;
    private CountryApi countryApiMock;

    @BeforeEach
    void setup() {
        countryApiMock = mock(CountryApi.class);
        countryService = new CountryService(countryApiMock, mock(CountryRepository.class));
    }

    @Test
    @DisplayName("WHEN the country name is null or empty THEN the result is false")
    void nullCountry() {
        assertThat(countryService.isCountry(null)).isFalse();
        assertThat(countryService.isCountry("")).isFalse();
        assertThat(countryService.isCountry("   ")).isFalse();
    }

    @Test
    @DisplayName("WHEN country is Romania THEN result is true")
    void romaniaCountry() {
        doReturn(true).when(countryApiMock).exists(eq("Romania"));
        assertThat(countryService.isCountry("Romania")).isTrue();
    }

    @Test
    @DisplayName("WHEN country is Wakanda THEN result is false")
    void wakandaCountry() {
        doAnswer(invocationOnMock -> {
            System.out.println("Answer...");
            String country = invocationOnMock.getArgument(0).toString();
            return !NOT_COUNTRIES.contains(country);
        }).when(countryApiMock).exists(anyString());

        assertThat(countryService.isCountry("Wakanda")).isFalse();

        ArgumentCaptor<Country> countryCaptor = ArgumentCaptor.forClass(Country.class);
        verify(countryApiMock, times(1)).exists(eq("Wakanda"));
        verify(countryApiMock, times(0)).exists("Romania");
        verify(countryApiMock, atLeastOnce()).info(countryCaptor.capture());
        verifyNoMoreInteractions(countryApiMock);

        Country calledCountry = countryCaptor.getValue();
        assertThat(calledCountry.getName()).isEqualTo("Wakanda");
        assertThat(calledCountry)
                .extracting("name", "population")
                .containsExactly("Wakanda", 700L);
    }

    @Test
    @DisplayName("WHEN multiple countries THEN result is correct")
    void multipleCountries() {
        when(countryApiMock.exists(anyString()))
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(true);

        System.out.println(countryApiMock.exists("a"));
        System.out.println(countryApiMock.exists("a"));
        System.out.println(countryApiMock.exists("a"));
        System.out.println(countryApiMock.exists("a"));
        System.out.println(countryApiMock.exists("a"));
    }
}
