package com.barbosa.basics.bool;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Write assertions for booleans")
public class BooleanAssertionTest {
    @Nested
    @DisplayName("When boolean is true")
    class WhenBooleanIsTrue{
        @Test
        @DisplayName("Should be true")
        void shouldBeTrue() {
            assertThat(true).isTrue();
        }
    }
    @Nested
    @DisplayName("When boolean is false")
    class WhenBooleanIsFalse {

        @Test
        @DisplayName("Should be false")
        void shouldBeFalse() {
            assertThat(false).isFalse();
        }
    }
}
