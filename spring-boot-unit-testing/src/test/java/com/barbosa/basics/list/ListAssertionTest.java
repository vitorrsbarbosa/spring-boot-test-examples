package com.barbosa.basics.list;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Writing assertions for lists")
class ListAssertionTest {

    @Nested
    @DisplayName("When we write assertions for elements")
    class WhenWeWriteAssertionsForElements {

        private Object first;
        private Object second;
        private Object third;

        private List<Object> list;

        @BeforeEach
        void createAndInitializeList() {
            first = new Object();
            second = new Object();
            third = new Object();

            list = Arrays.asList(first, second, third);
        }
        @Test
        @DisplayName("Should contain a correct element")
        void shouldContainCorrectElement() {
            assertThat(list).contains(first);
            assertThat(list).contains(second);
            assertThat(list).contains(third);
        }

        @Test
        @DisplayName("Should not contain an incorrect element")
        void shouldNotContainIncorrectElement() {
            assertThat(list).doesNotContain(new Object());
        }

        @Test
        @DisplayName("Should contain three elements")
        void shouldContainTwoElements() {
            assertThat(list).hasSize(3);
        }

        @Test
        @DisplayName("Should contain the correct elements in the given order")
        void shouldContainCorrectElementsInGivenOrder() {
            assertThat(list).containsSequence(first, second, third);
        }

        @Test
        @DisplayName("Should contain the correct elements in the given order")
        void shouldContainWrongElementsInGivenOrder() {
            assertThat(list).doesNotContainSequence(third, second, first);
            assertThat(list).doesNotContainSequence(third, first, second);
            assertThat(list).doesNotContainSequence(second, first, third);
            assertThat(list).doesNotContainSequence(second, third, first);
            assertThat(list).doesNotContainSequence(first, third, second);
        }
    }
    @Nested
    @DisplayName("When we compare two lists")
    class WhenWeCompareTwoLists {

        private final List<Integer> ACTUAL = Arrays.asList(1, 2, 3);
        private final List<Integer> EXPECTED = Arrays.asList(1, 2, 3);
        private final List<String> ACTUAL_LIST = Arrays.asList("bca", "cba", "abc");
        private final List<String> EXPECTED_LIST = Arrays.asList("abc", "bca","cba");

        @Test
        @DisplayName("Should contain the same elements")
        void shouldContainSameElements() {
            assertThat(ACTUAL).isEqualTo(EXPECTED);
        }

        @Test
        @DisplayName("Should sort the list")
        void shouldSortTheList() {
            Collections.sort(ACTUAL_LIST);
            assertThat(ACTUAL_LIST).isEqualTo(EXPECTED_LIST);
        }
    }
}
