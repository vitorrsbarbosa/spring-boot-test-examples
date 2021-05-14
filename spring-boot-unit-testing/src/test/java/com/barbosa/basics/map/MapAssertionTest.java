package com.barbosa.basics.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Writing assertions for maps")
class MapAssertionTest {

    private static final String KEY = "key";
    private static final String INCORRECT_KEY = "incorrectKey";
    private static final String VALUE = "value";
    private static final String INCORRECT_VALUE = "incorrectValue";

    private Map<String, String> map;

    @BeforeEach
    void createAndInitializeMap() {
        map = new HashMap<>();
        map.put(KEY, VALUE);
    }

    @Nested
    @DisplayName("When we check if the map contains the given key")
    class WhenWeCheckIfMapContainsGivenKey {

        @Test
        @DisplayName("Should contain the correct key")
        void shouldContainCorrectKey() {
            assertThat(map).containsKey(KEY);
        }
        @Test
        @DisplayName("Should not contain the incorrect key")
        void shouldNotContainIncorrectKey() {
            assertThat(map).doesNotContainKey(INCORRECT_KEY);
        }
        @Test
        @DisplayName("Should contain the correct value")
        void shouldContainTheCorrectValue() {
            assertThat(map).containsEntry(KEY,VALUE);
        }
        @Test
        @DisplayName("Should contain the incorrect value")
        void shouldContainTheIncorrectValue() {
            assertThat(map).doesNotContainEntry(KEY,INCORRECT_VALUE);
        }
    }
}