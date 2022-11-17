package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LexSortTest {
    @Test
    public void sortNum1and2and10() {
        String[] input = {
                "10. Task.",
                "1. Task.",
                "2. Task."
        };
        String[] out = {
                "1. Task.",
                "2. Task.",
                "10. Task."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input).containsExactly(out);
    }

    @Test
    public void sortDecimalNumbers() {
        String[] input = {
                "10.2.3 Task4.",
                "10.4.5 Task2.",
                "10.1.4 Task5."

        };
        String[] out = {
                "10.1.4 Task5.",
                "10.2.3 Task4.",
                "10.4.5 Task2."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input).containsExactly(out);
    }

    @Test
    public void sortSomeNumbers() {
        String[] input = {
                "10.2.3 Task4.",
                "10.4.5 Task2.",
                "10.1.4 Task5.",
                "6. Task1."

        };
        String[] out = {
                "6. Task1.",
                "10.1.4 Task5.",
                "10.2.3 Task4.",
                "10.4.5 Task2."
        };
        Arrays.sort(input, new LexSort());
        assertThat(input).containsExactly(out);
    }
}