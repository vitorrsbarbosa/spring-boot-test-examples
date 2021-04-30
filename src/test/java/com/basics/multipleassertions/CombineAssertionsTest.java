package com.basics.multipleassertions;

import com.basics.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Combine multiple assertions")
public class CombineAssertionsTest {
    private static final String FIRST_NAME = "Vitor";
    private static final String LAST_NAME = "Barbosa";

    private Person person;

    @BeforeEach
    void createPerson() {
        person = new Person(FIRST_NAME,LAST_NAME);
    }
    @Test
    @DisplayName("Should have correct name")
    void shouldHaveCorrectName() {
        assertThat(person)
                .hasFieldOrPropertyWithValue("firstName",FIRST_NAME)
                .hasFieldOrPropertyWithValue("lastName", LAST_NAME);
    }
}
