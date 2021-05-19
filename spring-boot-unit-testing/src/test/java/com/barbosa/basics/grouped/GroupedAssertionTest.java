package com.barbosa.basics.grouped;

import com.barbosa.basics.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GroupedAssertionTest {
    @Test
    void firstAndLastNameMatches() {
        var firstPerson = new Person(1L,"Vitor", "Barbosa");
        var secondPerson = new Person(2L, "Dayany", "Vasconcelos");
        assertAll("firstPerson",
                () -> assertThat(firstPerson.getId()).isEqualTo(1L),
                () -> assertThat(firstPerson.getFirstName()).isEqualTo("Vitor"),
                () -> assertThat(firstPerson.getLastName()).isEqualTo("Barbosa"));
        assertAll("secondPerson",
                () -> assertThat(secondPerson.getId()).isEqualTo(2L),
                () -> assertThat(secondPerson.getFirstName()).isEqualTo("Dayany"),
                () -> assertThat(secondPerson.getLastName()).isEqualTo("Vasconcelos"));

    }
}
