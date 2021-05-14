package com.barbosa.basics.timeout;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@DisplayName("Assertion of timelapse tests")
public class TimeoutTest {
    @Test
//    @Timeout(50)
    void returnValueBeforeTimeoutExceeded() {
        final String message = assertTimeout(Duration.ofMillis(50), () -> {
            Thread.sleep(10);
            return "a message";
        });
        assertThat("a message").isEqualTo(message);
    }

    @Test
    void abortWhenTimeoutExceeded() {
        final String message = assertTimeoutPreemptively(Duration.of(100, TimeUnit.MILLISECONDS.toChronoUnit()), () -> {
            Thread.sleep(50);
            return "sleep message";
        });
        assertThat("sleep message").isEqualTo(message);
    }
}
