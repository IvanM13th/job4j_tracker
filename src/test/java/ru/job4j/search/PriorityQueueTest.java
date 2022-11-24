package ru.job4j.search;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPrioritySecond() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDesc()).isEqualTo("urgent");
    }

    @Test
    public void whenHigherPriorityEquals() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 5));
        queue.put(new Task("middle", 5));
        var result = queue.take();
       assertThat(result.getDesc()).isEqualTo("low");
    }

    @Test
    public void whenHigherPriorityThird() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 7));
        queue.put(new Task("middle", 1));
        var result = queue.take();
        assertThat(result.getDesc()).isEqualTo("middle");
    }

    @Test
    public void whenHigherPriorityFourth() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 7));
        queue.put(new Task("middle", 2));
        queue.put(new Task("top", 1));
        var result = queue.take();
        assertThat(result.getDesc()).isEqualTo("top");
    }
}