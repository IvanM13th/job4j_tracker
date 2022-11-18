package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] string1 = o1.split("\\.");
        String[] string2 = o2.split("\\.");
        for (int i = 0; i < Math.min(string1.length, string2.length); i++) {
            if (Integer.parseInt(string1[i]) != Integer.parseInt(string2[i])) {
                return Integer.compare(Integer.parseInt(string1[i]), Integer.parseInt(string2[i]));
            }
        }
        return 0;
    }
}

