package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        String[] first = o1.split("/");
        String[] second = o2.split("/");
        return first[0].equals(second[0]) ? o1.compareTo(o2) : o2.compareTo(o1);
    }
}
