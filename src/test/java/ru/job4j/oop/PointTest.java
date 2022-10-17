package ru.job4j.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class PointTest {

    @Test
    public void when000and400Then4() {
        Point a = new Point(0, 0, 0);
        Point b = new Point(4, 0, 0);
        double rsl = a.distance3d(b);
        double expected = 4;
        assertThat(rsl).isCloseTo(expected, offset(0.001));
    }

    @Test
    public void when279and143Then6point782() {
        Point a = new Point(2, 7, 9);
        Point b = new Point(1, 4, 3);
        double rsl = a.distance3d(b);
        double expected = 6.782;
        assertThat(rsl).isCloseTo(expected, offset(0.001));
    }

    @Test
    public void when352and789Then8point602() {
        Point a = new Point(3, 5, 2);
        Point b = new Point(7, 8, 9);
        double rsl = a.distance3d(b);
        double expected = 8.602;
        assertThat(rsl).isCloseTo(expected, offset(0.001));
    }
}