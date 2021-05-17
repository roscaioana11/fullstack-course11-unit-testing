package ro.fasttrackit.curs11.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs11.model.Country;
import ro.fasttrackit.curs11.repository.CountryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryApi countryApi;
    private final CountryRepository repository;

    public boolean isCountry(String country) {
        if (country != null && !country.trim().isEmpty()) {
            boolean result = countryApi.exists(country);
            String info = countryApi.info(new Country("abc", country, country.length() * 100));
            return result;
        } else {
            return false;
        }
    }

    public List<Country> getAll() {
        return repository.findAll();
    }
}
