package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemSortTest {
    @Test
    public void whenSortByNameAsc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("pen"));
        items.add(new Item("bicycle"));
        items.add(new Item("phone"));
        items.sort(new ItemAscByName());
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("bicycle"));
        expected.add(new Item("pen"));
        expected.add(new Item("phone"));
        assertThat(items).isEqualTo(expected);
    }

    @Test
    public void whenSortByNameDesc() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("pen"));
        items.add(new Item("bicycle"));
        items.add(new Item("phone"));
        items.sort(new ItemDescByName());
        List<Item> expected = new ArrayList<>();
        expected.add(new Item("phone"));
        expected.add(new Item("pen"));
        expected.add(new Item("bicycle"));
        assertThat(items).isEqualTo(expected);
    }
}