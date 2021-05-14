package com.barbosa.basics.calculator;

import com.barbosa.basics.calculator.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Write basic assertions to verify test results")
public class BasicAssertionsTest {
    @Test
    @DisplayName("Should use a calculator to add a pair of numbers")
    void addNumbers() {
        Calculator calculator = new Calculator();
        assertThat(3).isEqualTo(calculator.add(1, 2));
    }
    @Test
    @DisplayName("Should not let a division by zero")
    void divideByZeroThrowsArithmeticException() {
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }
}
