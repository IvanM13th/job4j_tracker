package ru.job4j.collection;

import java.util.ArrayList;
import java.util.LinkedList;

public class UsageArrayList {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Petr");
        names.add("Ivan");
        names.add("Stepan");

        LinkedList<String> l = new LinkedList<>();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
