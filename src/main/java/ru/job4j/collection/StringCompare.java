package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1.length() != o2.length()) {
            return o1.compareTo(o2);
        } else {
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) != o2.charAt(i)) {
                    return Character.compare(o1.charAt(i), o2.charAt(i));
                }
            }
        }
        return 0;
    }
}
