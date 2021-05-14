package com.barbosa.basics.array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Write assertions for arrays")
public class ArrayAssertionTest {
    @Nested
    @DisplayName("When arrays contain integers")
    class WhenArraysContainIntegers {

        final int[] ACTUAL = new int[]{2, 5, 7};
        final int[] EXPECTED = new int[]{2, 5, 7};

        @Test
        @DisplayName("Should contain the same integers")
        void shouldContainSameIntegers() {
            assertThat(ACTUAL).isEqualTo(EXPECTED);
        }
    }
}
