package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] string1 = o1.split("\\.");
        String[] string2 = o2.split("\\.");
        if (!string1.equals(string2)) {
            for (int index = 0; index < string1.length; index++) {
                if (Integer.parseInt(string1[index]) != Integer.parseInt(string2[index])) {
                    return Integer.compare(Integer.parseInt(string1[index]),
                            Integer.parseInt(string2[index]));
                }
            }
        }
        return 0;
    }
}
