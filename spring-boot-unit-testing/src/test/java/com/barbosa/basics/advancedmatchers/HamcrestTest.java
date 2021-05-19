package com.barbosa.basics.advancedmatchers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class HamcrestTest {
    @Test
    @DisplayName("Using JUnit assertThat this list has two items")
    void listHasTwoItem() {
        List<String> list = new ArrayList<>(){{
            add("Hello one");
            add("Bye two");
        }};
        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Using Hamcrast assert that this list has only one item")
    void listHasOneItem() {
        List<String> list = new ArrayList<>(){{
            add("Hello one");
        }};
        assertThat(list, hasSize(1));
    }
}
