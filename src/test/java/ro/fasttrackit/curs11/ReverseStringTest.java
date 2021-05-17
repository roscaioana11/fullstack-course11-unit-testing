package ro.fasttrackit.curs11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReverseStringTest {
    private ReverseString reverse;

    @BeforeEach
    void setup() {
        this.reverse = new ReverseString();
    }

    @Test
    @DisplayName("WHEN the string is empty THEN the result is empty")
    void emptyString() {
        String result = reverse.reverse("");

        assertThat(result).isEqualTo("");
    }

    @Test
    @DisplayName("WHEN the string is null THEN the result is null")
    void nullString() {
        assertThat(reverse.reverse(null)).isNull();
    }

    @Test
    @DisplayName("WHEN the word is 'salut' THEN the result is 'tulas'")
    void salutTest() {
        assertThat(reverse.reverse("salut")).isEqualTo("tulas");
    }

    @Test
    @DisplayName("WHEN the word is 'verylongnamewithnumbers123456' THEN the result is 'tulas'")
    void longString() {
        assertThat(reverse.reverse("verylongnamewithnumbers123456")).isEqualTo("654321srebmunhtiwemangnolyrev");
    }
}
