package ru.job4j.function;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionCalculatorTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenSecondLinearFunctionThenLinearResults() {
        FunctionCalculator function = new FunctionCalculator();
        List<Double> result = function.diapason(4, 7, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(9D, 11D, 13D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenSquareFunction() {
        FunctionCalculator function = new FunctionCalculator();
        double a = 2;
        double b = 3;
        double c = 1;
        List<Double> result = function.diapason(1, 4, x -> a * x * x + b * x + c);
        List<Double> expected = Arrays.asList(6D, 15D, 28D);
        assertThat(result).containsAll(expected);
    }

    @Test
    public void whenAnotherFunction() {
        FunctionCalculator function = new FunctionCalculator();
        double a = 2;
        List<Double> result = function.diapason(2, 5, x -> Math.pow(a, x));
        List<Double> expected = Arrays.asList(4D, 9D, 16D);
    }
}