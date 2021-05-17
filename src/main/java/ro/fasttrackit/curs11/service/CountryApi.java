package ro.fasttrackit.curs11.service;

import org.springframework.stereotype.Component;
import ro.fasttrackit.curs11.model.Country;

@Component
public class CountryApi {
    public boolean exists(String country) {
        System.out.println("Calling Http Client");
        if (true) {
            throw new RuntimeException("The service is not accessible");
        }
        return country.equals("Romania");
    }

    public String info(Country country) {
        return country.getName() + " : " + country.getPopulation();
    }
}
