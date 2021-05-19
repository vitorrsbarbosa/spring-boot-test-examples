package com.barbosa.basics.advancedmatchers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TruthTest {
    @Test
    void listHasItemsInOrder() {
        List<String> fruits = new ArrayList<>();
        fruits.add("Citron");
        fruits.add("Orange");
        fruits.add("Grapefruit");
        assertThat(fruits).containsExactly("Citron", "Grapefruit", "Orange");
    }
}