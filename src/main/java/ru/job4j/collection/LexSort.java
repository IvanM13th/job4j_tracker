package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] string1 = o1.split("\\.");
        String[] string2 = o2.split("\\.");
        return Integer.compare(Integer.parseInt(string1[0]), Integer.parseInt(string2[0]));
    }
}


