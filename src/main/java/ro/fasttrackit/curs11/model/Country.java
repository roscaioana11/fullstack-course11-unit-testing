package ro.fasttrackit.curs11.model;

import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document
public class Country {
    @Id
    String id;
    String name;
    long population;
}
