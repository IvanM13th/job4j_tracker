package ru.job4j.collection;

import java.util.*;

public class Departments {
    public static List<String> fillGaps(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String first = "";
            String[] start = value.split("/", 0);
            for (String el : value.split("/")) {
                String elToAdd = el.equals(start[0]) ? first : ("/" + el);
                tmp.add(start[0] + elToAdd);
            }
        }
        return  new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        orgs.sort(Comparator.naturalOrder());
    }

    public static void sortDesc(List<String> orgs) {
        orgs.sort(new DepDescComp());
    }
}
