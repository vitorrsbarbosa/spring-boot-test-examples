package com.barbosa.basics.advancedmatchers;

import com.barbosa.basics.person.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertJTest {
    @Test
    @DisplayName("Using JUnit assert that list has a person with firstName = Jane")
    void listHasPerson() {
        List<Person> people = new ArrayList<>(){{
                add(new Person(1L,"John", "Doe"));
                add(new Person(2L,"Jane", "Doe"));
            }};
        assertTrue(people.stream().anyMatch(p -> p.getFirstName().equals("Jane")));
    }
    @Test
    @DisplayName("Using AssertJ assert that list has a person with firstName = John")
    void listHasBothPerson() {
        List<Person> people = new ArrayList<>() {{
                add(new Person(1L, "John", "Doe"));
                add(new Person(2L,"Jane", "Doe"));
            }};
        assertThat(people).extracting("firstName").contains("John");
    }
}
