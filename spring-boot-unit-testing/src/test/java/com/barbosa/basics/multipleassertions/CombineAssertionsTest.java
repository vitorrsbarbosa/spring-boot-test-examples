package com.barbosa.basics.multipleassertions;

import com.barbosa.basics.person.Person;
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
        person = new Person(1L, FIRST_NAME,LAST_NAME);
    }
    @Test
    @DisplayName("Should have correct name")
    void shouldHaveCorrectName() {
        assertThat(person)
                .hasFieldOrPropertyWithValue("firstName",FIRST_NAME)
                .hasFieldOrPropertyWithValue("lastName", LAST_NAME);
    }
    @Test
    @DisplayName("First name should start with \"V\"")
    void firstNameShouldStartWithV() {
        assertThat(person.getFirstName().startsWith("V")).isTrue();
        assertThat(person.getLastName().startsWith("B")).isTrue();
    }

    @Test
    @DisplayName("Assert that diferents persons with same name are not equals")
    void personsAreNotTheSame() {
        var first = new Person(1L, FIRST_NAME, LAST_NAME);
        var last = new Person(2L, FIRST_NAME, LAST_NAME);

        assertThat(first).isNotSameAs(last);
    }
}
