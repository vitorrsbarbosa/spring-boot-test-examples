package com.barbosa.basics.object;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Writing assertions for objects")
public class NullableObjectTest {
    @Nested
    @DisplayName("When object is null")
    class WhenObjectIsNull{
        @Test
        @DisplayName("Should be null")
        void shouldBeNull() {
            Object object = null;

            assertThat(object).isNull();
        }
    }
    @Nested
    @DisplayName("When object is not null")
    class WhenObjectIsNotNull {
        @Test
        @DisplayName("Should not be null")
        void shoudNotBeNull() {
            assertThat(new Object()).isNotNull();
        }
    }
    @Nested
    @DisplayName("When two objects are equal")
    class WhenTwoObjectsAreEqual {

        @Nested
        @DisplayName("When objects are integers")
        class WhenObjectsAreIntegers {

            private final Integer ACTUAL = 9;
            private final Integer EXPECTED = 9;

            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                assertThat(ACTUAL).isEqualTo(EXPECTED);
            }
        }
        @Nested
        @DisplayName("When objects are not equal")
        class WhenObjectArNotEqual {

            private final Integer ACTUAL = 9;
            private final Integer EXPECTED = 5;

            @Test
            @DisplayName("Should not be equal")
            void shouldNotBeEqual() {
                assertThat(ACTUAL).isNotEqualTo(EXPECTED);
            }
        }
    }
    @Nested
    @DisplayName("When two objects refer to the same object")
    class WhenTwoObjectsReferToSameObject {

        private final Object ACTUAL = new Object();
        private final Object EXPECTED = ACTUAL;

        @Test
        @DisplayName("Should refer to the same object")
        void shouldReferToSameObject() {
            assertThat(ACTUAL).hasSameClassAs(EXPECTED);
        }
    }@Nested
    @DisplayName("When two objects don't refer to the same object")
    class WhenTwoObjectsDoNotReferToSameObject {

        private final Object ACTUAL = new Object();
        private final Object EXPECTED = new Object();

        @Test
        @DisplayName("Should not refer to the same object")
        void shouldNotReferToSameObject() {
            assertThat(ACTUAL).isNotSameAs(EXPECTED);
        }
    }
}
